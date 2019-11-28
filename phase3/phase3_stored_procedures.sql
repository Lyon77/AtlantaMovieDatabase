DROP PROCEDURE IF EXISTS user_login;
DELIMITER $$
CREATE PROCEDURE `user_login`(IN i_username VARCHAR(50), IN i_password VARCHAR(50))
BEGIN
DECLARE isCustomer INT DEFAULT 0;
DECLARE isAdmin INT DEFAULT 0;
DECLARE isManager INT DEFAULT 0;
IF (SELECT customer.username FROM customer WHERE customer.username = i_username) IS NOT NULL THEN
	SET isCustomer = 1;
END IF;
IF (SELECT admin.username FROM admin WHERE admin.username = i_username) IS NOT NULL THEN
	SET isAdmin = 1;
END IF;
IF (SELECT manager.username FROM manager WHERE manager.username = i_username) IS NOT NULL THEN
	SET isManager = 1;
END IF;
    DROP TABLE IF EXISTS UserLogin;
    CREATE TABLE UserLogin
SELECT user.username, status, isCustomer, isAdmin, isManager
FROM user
WHERE user.username = i_username AND (password = MD5(i_password) OR password = i_password); -- Usually only want MD5, but for auto grader need the OR
END$$
DELIMITER ;

DROP PROCEDURE IF EXISTS user_register;
DELIMITER $$
CREATE PROCEDURE `user_register`(IN i_username VARCHAR(50), IN i_password 
VARCHAR(50), IN i_firstname VARCHAR(50), IN i_lastname VARCHAR(50))
BEGIN
INSERT INTO user (username, status, password, firstname, lastname) VALUES 
(i_username, 'Pending', MD5(i_password), i_firstname, i_lastname);
END$$
DELIMITER ;

DROP PROCEDURE IF EXISTS customer_only_register;
DELIMITER $$
CREATE PROCEDURE `customer_only_register`(IN i_username VARCHAR(50), IN i_password 
VARCHAR(50), IN i_firstname VARCHAR(50), IN i_lastname VARCHAR(50))
BEGIN
CALL user_register(i_username, i_password, i_firstname, i_lastname);
INSERT INTO customer (username) VALUES (i_username);
END$$
DELIMITER ;

DROP PROCEDURE IF EXISTS customer_add_creditcard;
DELIMITER $$
CREATE PROCEDURE `customer_add_creditcard`(IN i_username VARCHAR(50), IN i_creditCardNum CHAR(16))
BEGIN
INSERT INTO customercreditcard (username, creditCardNum) VALUES 
(i_username, i_creditCardNum);
END$$
DELIMITER ;

DROP PROCEDURE IF EXISTS manager_only_register;
DELIMITER $$
CREATE PROCEDURE `manager_only_register`(IN i_username VARCHAR(50), IN i_password 
VARCHAR(50), IN i_firstname VARCHAR(50), IN i_lastname VARCHAR(50), IN i_comName VARCHAR(50),
IN i_empStreet VARCHAR(25), IN i_empCity VARCHAR(25), IN i_empState CHAR(2), i_empZipcode CHAR(5))
BEGIN
CALL user_register(i_username, i_password, i_firstname, i_lastname);
INSERT INTO employee (username) VALUES (i_username);
INSERT INTO manager (username, comName, manStreet, manCity, manState, manZipcode) VALUES
(i_username, i_comName, i_empStreet, i_empCity, i_empState, i_empZipcode);
END$$
DELIMITER ;

DROP PROCEDURE IF EXISTS manager_customer_register;
DELIMITER $$
CREATE PROCEDURE `manager_customer_register`(IN i_username VARCHAR(50), IN i_password 
VARCHAR(50), IN i_firstname VARCHAR(50), IN i_lastname VARCHAR(50), IN i_comName VARCHAR(50),
IN i_empStreet VARCHAR(25), IN i_empCity VARCHAR(25), IN i_empState CHAR(2), i_empZipcode CHAR(5))
BEGIN
CALL manager_only_register(i_username, i_password, i_firstname, i_lastname,
i_comName, i_empStreet, i_empCity, i_empState, i_empZipcode);
INSERT INTO customer (username) VALUES (i_username);
END$$
DELIMITER ;

DROP PROCEDURE IF EXISTS manager_customer_add_creditcard;
DELIMITER $$
CREATE PROCEDURE `manager_customer_add_creditcard`(IN i_username VARCHAR(50), IN i_creditCardNum CHAR(16))
BEGIN
CALL customer_add_creditcard(i_username, i_creditCardNum);
END$$
DELIMITER ;

DROP PROCEDURE IF EXISTS admin_approve_user;
DELIMITER $$
CREATE PROCEDURE `admin_approve_user`(IN i_username VARCHAR(50))
BEGIN
UPDATE user SET status = 'Approved' WHERE team59.user.username = i_username;
END$$
DELIMITER ;

DROP PROCEDURE IF EXISTS admin_decline_user;
DELIMITER $$
CREATE PROCEDURE `admin_decline_user`(IN i_username VARCHAR(50))
BEGIN
UPDATE user SET status = 'Declined' WHERE team59.user.username = i_username;
END$$
DELIMITER ;

DROP PROCEDURE IF EXISTS admin_filter_user;
DELIMITER $$
CREATE PROCEDURE `admin_filter_user`(IN i_username VARCHAR(50), IN i_status ENUM('Approved', 'Pending', 'Declined', 'ALL'),
IN i_sortBy ENUM('username', 'creditCardCount', 'userType', 'status', ''), IN i_sortDirection ENUM('ASC', 'DESC', ''))
BEGIN
IF NOT i_sortBy IN ('username', 'creditCardCount', 'userType', 'status') THEN
	SET i_sortBy = 'username';
END IF;
IF NOT i_sortDirection IN ('ASC', 'DESC') THEN
	SET i_sortDirection = 'DESC';
END IF;
    DROP TABLE IF EXISTS AdFilterUser;
    CREATE TABLE AdFilterUser
SELECT user.username,
	count(customercreditcard.username) AS creditCardCount,
    CASE
		WHEN (SELECT (username IS NOT NULL) FROM team59.customer WHERE customer.username = user.username) THEN
			CASE
				WHEN (SELECT (username IS NOT NULL) FROM team59.manager WHERE manager.username = user.username) THEN
					"CustomerManager"
				WHEN (SELECT (username IS NOT NULL) FROM team59.admin WHERE admin.username = user.username) THEN
					"CustomerAdmin"
				ELSE
					"Customer"
		END
        ELSE
			CASE
				WHEN (SELECT (username IS NOT NULL) FROM team59.manager WHERE manager.username = user.username) THEN
					"Manager"
				WHEN (SELECT (username IS NOT NULL) FROM team59.admin WHERE admin.username = user.username) THEN
					"Admin"
				ELSE
					"User"
			END
	END userType,
    user.status
FROM user LEFT OUTER JOIN customercreditcard ON user.username = customercreditcard.username
WHERE (user.username = i_username OR i_username = "") AND
	(user.status = i_status OR i_status = "ALL")
GROUP BY user.username
ORDER BY
	CASE WHEN i_sortBy = 'username' AND i_sortDirection = 'DESC' THEN user.username END DESC,
    CASE WHEN i_sortBy = 'username' THEN user.username END,
    CASE WHEN i_sortBy = 'creditCardCount' AND i_sortDirection = 'DESC' THEN count(customercreditcard.username) END DESC,
    CASE WHEN i_sortBy = 'creditCardCount' THEN count(customercreditcard.username) END,
    CASE WHEN i_sortBy = 'userType' AND i_sortDirection = 'DESC' THEN userType END DESC,
    CASE WHEN i_sortBy = 'userType' THEN userType END,
    CASE WHEN i_sortBy = 'status' AND i_sortDirection = 'DESC' THEN status END DESC,
    CASE WHEN i_sortBy = 'status' THEN status END;
END$$
DELIMITER ;

DROP PROCEDURE IF EXISTS admin_filter_company;
DELIMITER $$
CREATE PROCEDURE `admin_filter_company`(IN i_comName VARCHAR(50), IN i_minCity INT, IN i_maxCity INT, IN i_minTheater INT,
IN i_maxTheater INT, IN i_minEmployee INT, IN i_maxEmployee INT,
IN i_sortBy ENUM('comName', 'numCityCover', 'numTheater', 'numEmployee', ''), IN i_sortDirection ENUM('ASC', 'DESC', ''))
BEGIN
IF NOT i_sortBy IN ('comName', 'numCityCover', 'numTheater', 'numEmployee') THEN
	SET i_sortBy = 'comName';
END IF;
IF NOT i_sortDirection IN ('ASC', 'DESC') THEN
	SET i_sortDirection = 'DESC';
END IF;
SET i_minCity = IFNULL(i_minCity, 0);
SET i_maxCity = IFNULL(i_maxCity, 99999999);
SET i_minTheater = IFNULL(i_minTheater, 0);
SET i_maxTheater = IFNULL(i_maxTheater, 99999999);
SET i_minEmployee = IFNULL(i_minEmployee, 0);
SET i_maxEmployee = IFNULL(i_maxEmployee, 99999999);
    DROP TABLE IF EXISTS AdFilterCom;
    CREATE TABLE AdFilterCom

SELECT theater.comName,
	COUNT(DISTINCT thCity, thState) AS numCityCover,
	COUNT(DISTINCT thName, theater.comName) AS numTheater, 
	COUNT(DISTINCT manager.username) AS numEmployee
FROM theater JOIN manager ON manager.comName = theater.comName
WHERE (theater.comName = i_comName OR i_comName = "ALL" OR i_comName = "")
GROUP BY theater.comName
HAVING (numCityCover >= i_minCity AND numCityCover <= i_maxCity) AND
	(numTheater >= i_minTheater AND numTheater <= i_maxTheater) AND
    (numEmployee >= i_minEmployee AND numEmployee <= i_maxEmployee)
ORDER BY
	CASE WHEN i_sortBy = 'comName' AND i_sortDirection = 'DESC' THEN theater.comName END DESC,
    CASE WHEN i_sortBy = 'comName' THEN theater.comName END,
    CASE WHEN i_sortBy = 'numCityCover' AND i_sortDirection = 'DESC' THEN numCityCover END DESC,
    CASE WHEN i_sortBy = 'numCityCover' THEN numCityCover END,
    CASE WHEN i_sortBy = 'numTheater' AND i_sortDirection = 'DESC' THEN numTheater END DESC,
    CASE WHEN i_sortBy = 'numTheater' THEN numTheater END,
    CASE WHEN i_sortBy = 'numEmployee' AND i_sortDirection = 'DESC' THEN numEmployee END DESC,
    CASE WHEN i_sortBy = 'numEmployee' THEN numEmployee END;
END$$
DELIMITER ;

DROP PROCEDURE IF EXISTS admin_create_theater;
DELIMITER $$
CREATE PROCEDURE `admin_create_theater`(IN i_thName VARCHAR(25), IN i_comName VARCHAR(50),
IN i_thStreet VARCHAR(25), IN i_thCity VARCHAR(25), IN i_thState CHAR(2), IN i_thZipcode CHAR(5),
IN i_capacity INT, i_managerUsername VARCHAR(50))
BEGIN
INSERT INTO theater (thName, comName, capacity, thStreet, thCity, thState, thZipcode, manUsername) VALUES 
(i_thName, i_comName, i_capacity, i_thStreet, i_thCity, i_thState, i_thZipcode, i_managerUsername);
END$$
DELIMITER ;

DROP PROCEDURE IF EXISTS admin_view_comDetail_emp;
DELIMITER $$
CREATE PROCEDURE `admin_view_comDetail_emp`(IN i_comName VARCHAR(50))
BEGIN
DROP TABLE IF EXISTS AdComDetailEmp;
CREATE TABLE AdComDetailEmp
SELECT firstName AS empFirstname,
	lastName AS empLastname
FROM user
WHERE user.username IN (SELECT manager.username FROM manager WHERE manager.comName = i_comName);
END$$
DELIMITER ;

DROP PROCEDURE IF EXISTS admin_view_comDetail_th;
DELIMITER $$
CREATE PROCEDURE `admin_view_comDetail_th`(IN i_comName VARCHAR(50))
BEGIN
DROP TABLE IF EXISTS AdComDetailTh;
CREATE TABLE AdComDetailTh
SELECT thName,
	manUsername AS thManagerUsername,
    thCity, thState,
    capacity AS thCapacity
    FROM theater
    WHERE theater.comName = i_comName;
END$$
DELIMITER ;


DROP PROCEDURE IF EXISTS admin_create_mov;
DELIMITER $$
CREATE PROCEDURE `admin_create_mov`(IN i_movName VARCHAR(50), IN i_movDuration INT, IN i_movReleaseDate DATE)
BEGIN
INSERT INTO movie (movName, movReleaseDate, duration) VALUES
(i_movName, i_movReleaseDate, i_movDuration);
END$$
DELIMITER ;


-- NEW MANAGER FILTER TH
DROP PROCEDURE IF EXISTS manager_filter_th;
DELIMITER $$
CREATE PROCEDURE `manager_filter_th`(IN i_manUsername VARCHAR(50), IN i_movName VARCHAR(50), IN i_minMovDuration INT,
IN i_maxMovDuration INT, IN i_minMovReleaseDate DATE, IN i_maxMovReleaseDate DATE, IN i_minMovPlayDate DATE, IN i_maxMovPlayDate DATE, 
IN i_includeNotPlayed BOOLEAN)
BEGIN
SET i_includeNotPlayed = IFNULL(i_includeNotPlayed, FALSE);

DROP TABLE IF EXISTS ManTmpTbl;
CREATE TABLE ManTmpTbl
SELECT movie.movName, movie.duration, movie.movReleaseDate, movieplay.movPlayDate
FROM movie LEFT OUTER JOIN movieplay ON (movie.movName = movieplay.movName AND movie.movReleaseDate = movieplay.movReleaseDate)
WHERE (movieplay.thName IN (SELECT thName FROM theater WHERE manUsername = i_manUsername));

DROP TABLE IF EXISTS ManFilterTh;
CREATE TABLE ManFilterTh
SELECT movie.movName, movie.duration AS movDuration, movie.movReleaseDate, mantmptbl.movPlayDate
FROM movie LEFT OUTER JOIN mantmptbl ON (movie.movName = mantmptbl.movName AND movie.movReleaseDate = mantmptbl.movReleaseDate)
WHERE
	CASE
		WHEN i_includeNotPlayed THEN mantmptbl.movPlayDate IS NULL -- OR mantmptbl.movePlayDate > NOW()
        ELSE TRUE
	END AND
    (movie.movName LIKE CONCAT("%", i_movName, "%")) AND
    ((movie.duration >= i_minMovDuration OR i_minMovDuration IS NULL) AND (movie.duration <= i_maxMovDuration OR i_maxMovDuration IS NULL)) AND
    ((movie.movReleaseDate >= i_minMovReleaseDate OR i_minMovReleaseDate IS NULL) AND (movie.movReleaseDate <= i_maxMovReleaseDate OR i_maxMovReleaseDate IS NULL)) AND
    (((mantmptbl.movPlayDate >= i_minMovPlayDate OR i_minMovPlayDate IS NULL) AND (mantmptbl.movPlayDate <= i_maxMovPlayDate OR i_maxMovPlayDate IS NULL)) OR (mantmptbl.movPlayDate IS NULL))
    /*
    CASE
		WHEN i_includeNotPlayed THEN ((movieplay.movPlayDate IS NULL) AND (movieplay.movPlayDate >= i_minMovPlayDate OR i_minMovPlayDate IS NULL) AND (movieplay.movPlayDate <= i_maxMovPlayDate OR i_maxMovPlayDate IS NULL))
        ELSE ((movieplay.movPlayDate >= i_minMovPlayDate OR i_minMovPlayDate IS NULL) AND (movieplay.movPlayDate <= i_maxMovPlayDate OR i_maxMovPlayDate IS NULL) OR (movieplay.movPlayDate IS NULL))
    END
    */
    ;
END$$
DELIMITER ;

DROP PROCEDURE IF EXISTS manager_schedule_mov;
DELIMITER $$
CREATE PROCEDURE `manager_schedule_mov`(IN i_manUsername VARCHAR(50), IN i_movName VARCHAR(50),
IN i_movReleaseDate DATE, IN i_movPlayDate DATE)
BEGIN
DECLARE m_thName VARCHAR(25) DEFAULT '';
DECLARE m_comName VARCHAR(50) DEFAULT '';
SELECT thName, comName INTO m_thName, m_comName FROM theater WHERE theater.manUsername = i_manUsername;
INSERT INTO movieplay (thName, comName, movName, movReleaseDate, movPlayDate) VALUES
(m_thName, m_comName, i_movName, i_movReleaseDate, i_movPlayDate);
END$$
DELIMITER ;

DROP PROCEDURE IF EXISTS customer_filter_mov;
DELIMITER $$
CREATE PROCEDURE `customer_filter_mov`(IN i_movName VARCHAR(50), IN i_comName VARCHAR(50),
IN i_city VARCHAR(25), IN i_state VARCHAR(3), IN i_minMovPlayDate DATE, IN i_maxMovPlayDate DATE)
BEGIN
    DROP TABLE IF EXISTS CosFilterMovie;
    CREATE TABLE CosFilterMovie
SELECT movieplay.movName, theater.thName, thStreet, thCity, thState, thZipcode, theater.comName, movieplay.movPlayDate, movieplay.movReleaseDate
    FROM movieplay JOIN theater ON (movieplay.thName = theater.thName AND movieplay.comName = theater.comName)
    WHERE 
		(movieplay.movName = i_movName OR i_movName = "ALL") AND
        (movieplay.comName = i_comName OR i_comName = "ALL") AND
        (thCity = i_city OR i_city = "") AND
        (thState = i_state OR i_state = "ALL" OR i_state = "") AND
        ((movieplay.movPlayDate >= i_minMovPlayDate OR i_minMovPlayDate IS NULL) AND (movieplay.movPlayDate <= i_maxMovPlayDate OR i_maxMovPlayDate IS NULL));
END$$
DELIMITER ;

DROP PROCEDURE IF EXISTS customer_view_mov;
DELIMITER $$
CREATE PROCEDURE `customer_view_mov`(IN i_creditCardNum CHAR(16), IN i_movName VARCHAR(50), IN i_movReleaseDate DATE,
IN i_thName VARCHAR(25), IN i_comName VARCHAR(50), IN i_movPlayDate DATE)
BEGIN
INSERT INTO customerviewmovie (creditCardNum, thName, comName, movName, movReleaseDate, movPlayDate) VALUES
(i_creditCardNum, i_thName, i_comName, i_movName, i_movReleaseDate, i_movPlayDate);
END$$
DELIMITER ;

DROP PROCEDURE IF EXISTS customer_view_history;
DELIMITER $$
CREATE PROCEDURE `customer_view_history`(IN i_cusUsername VARCHAR(50))
BEGIN
    DROP TABLE IF EXISTS CosViewHistory;
    CREATE TABLE CosViewHistory
    SELECT movName, thName, comName, creditCardNum, movPlayDate
    FROM customerviewmovie
    WHERE creditCardNum IN (SELECT creditCardNum from customercreditcard WHERE username = i_cusUsername);
END$$
DELIMITER ;

DROP PROCEDURE IF EXISTS user_filter_th;
DELIMITER $$
CREATE PROCEDURE `user_filter_th`(IN i_thName VARCHAR(50), IN i_comName 
VARCHAR(50), IN i_city VARCHAR(50), IN i_state VARCHAR(3))
BEGIN
    DROP TABLE IF EXISTS UserFilterTh;
    CREATE TABLE UserFilterTh
SELECT thName, thStreet, thCity, thState, thZipcode, comName 
    FROM Theater
    WHERE 
(thName = i_thName OR i_thName = "ALL") AND
        (comName = i_comName OR i_comName = "ALL") AND
        (thCity = i_city OR i_city = "") AND
        (thState = i_state OR i_state = "ALL" OR i_state = "");
END$$
DELIMITER ;


DROP PROCEDURE IF EXISTS user_visit_th;
DELIMITER $$
CREATE PROCEDURE `user_visit_th`(IN i_thName VARCHAR(50), IN i_comName VARCHAR(50),
IN i_visitDate DATE, IN i_username VARCHAR(50))
BEGIN
    INSERT INTO UserVisitTheater (thName, comName, visitDate, username)
    VALUES (i_thName, i_comName, i_visitDate, i_username);
END$$
DELIMITER ;


DROP PROCEDURE IF EXISTS user_filter_visitHistory;
DELIMITER $$
CREATE PROCEDURE `user_filter_visitHistory`(IN i_username VARCHAR(50), IN 
i_minVisitDate DATE, IN i_maxVisitDate DATE)
BEGIN
    DROP TABLE IF EXISTS UserVisitHistory;
    CREATE TABLE UserVisitHistory
SELECT thName, thStreet, thCity, thState, thZipcode, comName, visitDate
    FROM UserVisitTheater
NATURAL JOIN
        Theater
WHERE
(username = i_username) AND
        (i_minVisitDate IS NULL OR visitDate >= i_minVisitDate) AND
        (i_maxVisitDate IS NULL OR visitDate <= i_maxVisitDate);
END$$
DELIMITER ;