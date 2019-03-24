package pl.sda.intermediate16.playlist;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import pl.sda.intermediate16.playlists.Movie;
import pl.sda.intermediate16.playlists.Music;
import pl.sda.intermediate16.playlists.PlayMode;
import pl.sda.intermediate16.playlists.Playlist;

class PlaylistTest {

    protected Music music1 = new Music("Metallica","Sad but true");
    protected Music music2 = new Music("Kult","6 lat pozniej");
    protected Music music3 = new Music("U2","With or without you");
    protected Music music4 = new Music("Perfect","Autobiografia");
    protected Music music5 = new Music("David Bowie","Lazarus");
    protected Movie movie1 = new Movie("Bohemian Rhapsody");
    protected Movie movie2 = new Movie("Kevin sam w domu");
    protected Movie movie3 = new Movie("Janosik");
    protected Movie movie4 = new Movie("Sami swoi");

    @Test
    void shouldWorkProperlyWithRandomMode() {
        Playlist playlist = new Playlist(PlayMode.RANDOM);
        playlist.addToPlaylist(music1);
        playlist.addToPlaylist(music2);
        playlist.addToPlaylist(music3);
        playlist.addToPlaylist(music4);
        playlist.addToPlaylist(music5);
        playlist.addToPlaylist(movie1);
        playlist.addToPlaylist(movie2);
        playlist.addToPlaylist(movie3);
        playlist.addToPlaylist(movie4);
        System.out.println(playlist.play());
    }

    @Test
    void shouldWorkProperlyWithRandomModeWithSubPlaylist() {
        Playlist playlist = new Playlist(PlayMode.RANDOM);
        playlist.addToPlaylist(music1);
        playlist.addToPlaylist(music2);
        playlist.addToPlaylist(music3);
        playlist.addToPlaylist(movie1);
        playlist.addToPlaylist(movie2);
        Playlist subPlaylist = new Playlist(PlayMode.SEQUENTIAL);
        subPlaylist.addToPlaylist(music4);
        subPlaylist.addToPlaylist(music5);
        subPlaylist.addToPlaylist(movie3);
//        playlist.addToPlaylist(subPlaylist); <- StackOverflowError
        System.out.println(playlist.play());
    }
}
