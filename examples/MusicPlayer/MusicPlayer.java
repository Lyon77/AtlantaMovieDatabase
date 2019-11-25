import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.TextInputDialog;
import java.util.Optional;
import javafx.scene.control.ChoiceDialog;

// import javafx.beans.binding.Bindings;
// import javafx.beans.binding.BooleanBinding;
// import javafx.beans.value.ObservableBooleanValue;

/**
 * A MusicPlayer
 *
 * @author rli342
 * @version 9.0.1
*/
public class MusicPlayer extends Application {

    private Media me;
    private MediaPlayer mp;
    private MediaView mv;
    private File[] directory;
    private ObservableList<Song> obList;
    private TableView<Song> media;
    private Song mda;

    /**
     * The main method of creating the music payer
     * @param stage is the output scene
     */
    @Override
    public void start(Stage stage) {

        directory = new File(".").listFiles(f ->
                f.toURI().toString().contains(".mp3")
            );

        SongList s = new SongList();

        obList = FXCollections.observableArrayList(s.getSongs());
        media = createTable(obList);

        Button playButton = new Button("Play");
        Button pauseButton = new Button("Pause");
        pauseButton.setDisable(true);

        playButton.setOnAction(e -> {
            if (!media.getSelectionModel().isEmpty()) {
                mda = media.getSelectionModel().getSelectedItem();
                mda.getSong().play();
                playButton.setDisable(true);
                pauseButton.setDisable(false);
            }});
        // BooleanBinding booleanBind = Bindings
        //     .isNull(media.getSelectionModel().selectedItemProperty())
        //     .and(new ObservableBooleanValue() {
        //             @Override
        //             public boolean get(){
        //                 return pauseButton.isDisabled();
        //         }});
        // playButton.disableProperty()
        //     .bind(booleanBind);

        pauseButton.setOnAction(e -> {
                mda.getSong().pause();
                playButton.setDisable(false);
                pauseButton.setDisable(true);
            });
        // BooleanBinding booleanBind2 = Bindings
        //     .isNull(media.getSelectionModel().selectedItemProperty())
        //     .and(new ObservableBooleanValue() {
        //             @Override
        //             public boolean get(){
        //                 return playButton.isDisabled();
        //         }});
        // pauseButton.disableProperty()
        //     .bind(booleanBind2);

        Button searchButton = new Button("Search Songs");
        Button showButton = new Button("Show all Songs");
        showButton.setDisable(true);

        searchButton.setOnAction(e -> {
                String term = "";
                String category = "";

                TextInputDialog dialog = new TextInputDialog("");

                dialog.setTitle("Search Music");
                dialog.setHeaderText("Input Search Term:");
                dialog.setContentText("Term:");

                Optional<String> result = dialog.showAndWait();

                term = result.get();

                ChoiceDialog<String> dialog2 = new ChoiceDialog<>("Name"
                    , "Artist", "Title", "Album");

                dialog2.setTitle("Search Category");
                dialog2.setHeaderText("Select Category:");
                dialog2.setContentText("Category:");

                Optional<String> result2 = dialog2.showAndWait();

                category = result2.get();

                List<Song> list = s.getSongs();
                for (int i = list.size() - 1; i >= 0; i--) {
                    if (category.equals("Name")) {
                        if (!list.get(i).getName().contains(term)) {
                            media.getItems().remove(i);
                        }
                    } else if (category.equals("Artist")) {
                        if (!list.get(i).getArtist().contains(term)) {
                            media.getItems().remove(i);
                        }
                    } else if (category.equals("Title")) {
                        if (!list.get(i).getTitle().contains(term)) {
                            media.getItems().remove(i);
                        }
                    } else if (category.equals("Album")) {
                        if (!list.get(i).getAlbum().contains(term)) {
                            media.getItems().remove(i);
                        }
                    }
                }

                searchButton.setDisable(true);
                showButton.setDisable(false);
            });

        showButton.setOnAction(e -> {
                media.getItems().clear();
                List<Song> list = s.getSongs();
                for (Song songInList:list) {
                    media.getItems().add(songInList);
                }

                searchButton.setDisable(false);
                showButton.setDisable(true);
            });

        HBox buttons = new HBox();
        buttons.getChildren()
            .addAll(playButton, pauseButton, searchButton, showButton);

        VBox vbox = new VBox();
        vbox.getChildren().addAll(media, buttons);

        Scene scene = new Scene(vbox);
        stage.setScene(scene);
        stage.setTitle("Music Player");
        media.refresh();
        stage.show();
    }

    /**
     * used to create table and columns
     * @param list is song list
     * @return the completed table
     */
    private TableView<Song> createTable(ObservableList<Song> list) {
        //outer table
        TableView<Song> table = new TableView<>();
        table.setItems(list);

        TableColumn<Song, String>  nameCol =
            new TableColumn<>("File Name");
        nameCol.setCellValueFactory(new PropertyValueFactory("name"));

        //inner table
        TableColumn<Song, String>  artistCol =
            new TableColumn<>("Artist");
        artistCol.setCellValueFactory(new PropertyValueFactory("artist"));

        TableColumn<Song, String>  titleCol =
            new TableColumn<>("Title");
        titleCol.setCellValueFactory(new PropertyValueFactory("title"));

        TableColumn<Song, String>  albumCol =
            new TableColumn<>("Album");
        albumCol.setCellValueFactory(new PropertyValueFactory("album"));

        //combine the two
        TableColumn  attributesCol =
            new TableColumn("Attributes");
        attributesCol.getColumns().addAll(artistCol, titleCol, albumCol);

        table.getColumns().setAll(nameCol, attributesCol);

        return table;
    }

    /**
     * inner class to store songs
     *
     * @author rli342
     * @version 9.0.1
    */
    public class SongList {

        private List<Song> songs;

        /**
         * A contructor with no parameters
         */
        public SongList() {
            songs = new ArrayList<>();
            for (File file : directory) {
                songs.add(new Song(file));
            }
        }

        /**
         * get songs
         * @return the list of songs
         */
        public List<Song> getSongs() {
            return songs;
        }
    }

    /**
     * song creator
     *
     * @author rli342
     * @version 9.0.1
    */
    public class Song {

        private String name;
        private String artist;
        private String title;
        private String album;
        private Media m;
        private MediaPlayer song;

        /**
         * A contructor with one parameter
         * @param song is the File of the song
         */
        public Song(File song) {

            name = song.toString().substring(2);
            artist = "";
            title = "";
            album = "";

            m = new Media(song.toURI().toString());

            this.song = new MediaPlayer(m);
            m.getMetadata()
                .addListener((MapChangeListener<String, Object>) ch -> {
                        if (ch.getKey().equals("artist")) {
                            artist = ch.getValueAdded().toString();
                        }
                        if (ch.getKey().equals("title")) {
                            title = ch.getValueAdded().toString();
                        }
                        if (ch.getKey().equals("album")) {
                            album = ch.getValueAdded().toString();
                        }
                        media.refresh();
                    });

        }

        /**
         * get name
         * @return the name
         */
        public String getName() {
            return name;
        }
        /**
         * get artist
         * @return the artist
         */
        public String getArtist() {
            return artist;
        }
        /**
         * get title
         * @return the title
         */
        public String getTitle() {
            return title;
        }
        /**
         * get album
         * @return the album
         */
        public String getAlbum() {
            return album;
        }
        /**
         * get media
         * @return the media
         */
        public Media getM() {
            return m;
        }
        /**
         * get mediaplayer
         * @return the mediaplayer
         */
        public MediaPlayer getSong() {
            return song;
        }
    }
}