import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Album {
    private String artist;
    private String name;
    private List<Song> songs;

    public Album() {
    }

    public Album(String artist, String name) {
        this.artist = artist;
        this.name = name;
        this.songs = new ArrayList<>();
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    // Functionalities in the album
    boolean findSong(String name) {
        for(Song song : songs) {
            if(name.equals(song.getTitle())) {
                return true;
            }
        }
        return false;
    }

    String addSongToAlbum(String title, int durationMinutes, int durationSeconds) {
        if(!findSong(title)) {
            Song song = new Song(title, durationMinutes, durationSeconds);
            this.songs.add(song);
            return "Song has been added to " + this.songs + " successfully.";
        } else {
            return "Song already exists in the Album.";
        }
    }

    String addToPlaylistFromAlbum(String title, LinkedList<Song> playlist) {
        for(Song song : songs) {
            if(title.equals(song.getTitle())) {
                playlist.add(song);
                return "Song has been added to your playlist successfully.";
            }
        }
        return "Song is not present in album please select another song.";
    }

    /**
     * Polymorphism
     * as we are overloading function addToPlayListFromAlbum
     */
    String addToPlaylistFromAlbum(int songNo, LinkedList<Song> playlist) {
        int songIndex = songNo - 1;
        if(songIndex >= 0 && songIndex < songs.size()) {
            playlist.add(songs.get(songIndex));
            return "Song has been added to your playlist successfully.";
        }
        return "Song is not present in album please select another song.";
    }
}
