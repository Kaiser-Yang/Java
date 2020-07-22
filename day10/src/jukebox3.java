import java.util.*;
import java.io.*;

public class jukebox3 {
    ArrayList<song> songList = new ArrayList<song>();


    public static void main(String[] args){
        new jukebox3().go();
    }
    class artistCompare implements Comparator<song>{
        public int compare(song a, song b){
            return a.getArtist().compareTo(b.getArtist());
        }
    }
    void go(){
        try{
            File file = new File("song.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = null;
            while((line = reader.readLine()) != null) addSong(line);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        System.out.println(songList);
        Collections.sort(songList, new artistCompare());
        System.out.println(songList);
    }
    void addSong(String line){
        String[] song = line.split("/");
        songList.add(new song(song[0], song[1], song[2], song[3]));
    }
}
