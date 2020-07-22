import java.io.*;
import java.util.*;

public class jukebox5 {
    ArrayList<song> songList = new ArrayList<song>();
    TreeSet<song> songSet = new TreeSet<song>();
    int val;

    public static void main(String[] args){
        new jukebox5().go();
    }
    void go(){
        getSongs();
        System.out.println(songList);
        Collections.sort(songList);
        System.out.println(songList);

        songSet.addAll(songList);
        System.out.println(songSet);
    }
    void getSongs(){
        try{
            File file = new File("song.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = null;
            while((line = reader.readLine()) != null) addSong(line);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    void addSong(String line){
        String[] song = line.split("/");
        songList.add(new song(song[0], song[1], song[2], song[3]));
    }
}
