import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        Album rare = new Album("Selena Gomez", "Rare");
        Album planetHer = new Album("Doja Cat", "Planet Her");

        rare.addSongToAlbum("Lose You to Love me", 3, 26);
        rare.addSongToAlbum("Look at Her Now", 2, 43);
        rare.addSongToAlbum("Let Me Get Me", 3, 9);

        planetHer.addSongToAlbum("Been Like This", 2, 57);
        planetHer.addSongToAlbum("Get Into Yuh", 2, 18);
        planetHer.addSongToAlbum("Need to Know", 3, 31);
        /* To show the list of songs added
        System.out.println(rare.getSongs());
        System.out.println(planetHer.getSongs());
         */

        LinkedList<Song> myPlaylist = new LinkedList<>();
        planetHer.addToPlaylistFromAlbum("Been Like This", myPlaylist);
//        System.out.println(planetHer.addToPlaylistFromAlbum("Rules", myPlaylist));
        planetHer.addToPlaylistFromAlbum(3, myPlaylist);

        /* To show the error while loading invalid song
        System.out.println(planetHer.addToPlaylistFromAlbum(5, myPlaylist));
        System.out.println(planetHer.addToPlaylistFromAlbum(-8, myPlaylist));
         */

        rare.addToPlaylistFromAlbum("Look at Her Now", myPlaylist);
        rare.addToPlaylistFromAlbum("Let Me Get Me", myPlaylist);

        /* To show the error while loading invalid song
        System.out.println(rare.addToPlaylistFromAlbum("Come And Get It", myPlaylist));
        System.out.println(rare.addToPlaylistFromAlbum(0, myPlaylist));
         */
        rare.addToPlaylistFromAlbum(1, myPlaylist);
//        System.out.println(rare.addToPlaylistFromAlbum(4, myPlaylist));
        play(myPlaylist);


    }

    private static void play(LinkedList<Song> playlist) {
        ListIterator<Song> itr = playlist.listIterator();

        if(playlist.isEmpty()) {
            System.out.println("Add songs to this playlist to play");
            return;
        }
        Song currentSong = itr.next();

        Scanner scn = new Scanner(System.in);

        boolean quitPlaylist = false;
        boolean songIsPaused = false;
        int previousCmd = 1; // 0 for move back and 1 for move forward

//        this loop will help the user to navigate in the playlist
        int wrongChoiceCount = 0;
        int choice = 0;
        while(!quitPlaylist && !playlist.isEmpty()) {
            if(choice != 2) {
                System.out.println("Now Playing : " + currentSong.getTitle());
            }
            printMenu(songIsPaused);
            choice = scn.nextInt();
            switch (choice) {
                case 1 -> printSongs(playlist);
                case 2 -> {
                    if (songIsPaused) {
                        songIsPaused = false;
                        System.out.println(currentSong.getTitle() + " : is resumed");
                    } else {
                        songIsPaused = true;
                        System.out.println(currentSong.getTitle() + " : is paused");
                    }
                }
                case 3 -> {
                    if (previousCmd == 0) {
                        previousCmd = 1;
                        itr.next();
                    }
                    if (itr.hasNext()) {
                        currentSong = itr.next();
//                        System.out.println("Now Playing : " + currentSong.getTitle());
                    } else {
                        System.out.println("No next song exists you are at the end of playlist");
                    }
                }
                case 4 -> {
                    if (previousCmd == 1) {
                        previousCmd = 0;
                        itr.previous();
                    }
                    if (itr.hasPrevious()) {
                        currentSong = itr.previous();
//                        System.out.println("Now Playing : " + currentSong.getTitle());
                    } else {
                        System.out.println("No previous song exists you are at the start of playlist");
                    }
                }
                case 5 -> System.out.println("Replaying : " + currentSong.getTitle());
                case 6 -> {
                    if (!itr.hasNext()) {
                        itr.previous();
                    }
                    if (!itr.hasPrevious()) {
                        itr.next();
                    }
                    System.out.println("Deleted : " + currentSong.getTitle());
                    itr.remove();
                    if (playlist.isEmpty()) {
                        return;
                    }
                    if (itr.hasNext()) {
                        currentSong = itr.next();
                        previousCmd = 1;
                    } else {
                        previousCmd = 0;
                        currentSong = itr.previous();
                    }
                }
                case 7 -> {
                    quitPlaylist = true;
                    System.out.println("Quit");
                }
                default -> {
                    wrongChoiceCount++;
                    if (wrongChoiceCount <= 3) {
                        System.out.println("Your input is invalid");
                    } else {
                        wrongChoiceCount = 0;
                        quitPlaylist = true;
                        System.out.println("Detected unintentional behaviour" + "\n" + "Quit the Application");
                    }
                }
            }
            if(choice >= 1 && choice <= 7) {
                wrongChoiceCount = 0;
            }
        }

    }

    private static void printSongs(LinkedList<Song> playlist) {
        int serialNo = 1;
        System.out.println("******************** Playlist ********************");
        for(Song song : playlist) {
            String songTitle = validateTitle(song.getTitle());
            String songDuration = song.getDuration();
            if(serialNo < 10) {
                System.out.print(0);
            }
            System.out.print(serialNo + ". ");
            System.out.print(songTitle);
            for(int i = songTitle.length(); i <= 40; i++) {
                System.out.print(" ");
            }
            System.out.println(songDuration);

            serialNo++;
        }
        System.out.println("**************************************************");
    }

    private static String validateTitle(String songTitle) {
        if(songTitle.length() <= 37) {
            return  songTitle;
        }

        String trimmedTitle = songTitle.substring(0, 35);
        for(int i = 34; i >= 14; i--) {
            char ch = trimmedTitle.charAt(i);
            if(ch != '\'' && ch < 'A' || ch > 'Z' && ch < 'a' || ch > 'z') {
                return songTitle.substring(0, i) + "...";
            }
        }
        return songTitle.substring(0, 34) + "...";
    }

    private  static void printMenu(boolean songIsPaused) {
//        System.out.println();
        System.out.println("************* Please select an Option ************");
        System.out.println("1 : Show all songs in playlist");
        if(songIsPaused) {
            System.out.println("2 : Resume");
        } else {
            System.out.println("2 : Pause");
        }
        System.out.println("3 : Play next song");
        System.out.println("4 : Play previous song");
        System.out.println("5 : Replay");
        System.out.println("6 : Delete this song from playlist");
        System.out.println("7 : Stop and exit from playlist");
        System.out.println("************** Your Input goes here **************");
    }
}