import java.io.*;
import java.util.*;

public class jukebox1 {
    ArrayList<String> songList = new ArrayList<String>();

    public static void main(String[] args){
        new jukebox1().go();
    }

    void go(){
        getSongs();
        System.out.println(songList);
        Collections.sort(songList);
        System.out.println(songList);
    }
    void getSongs(){
        try{
            File file = new File("song.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while((line = reader.readLine()) != null) addSong(line);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    void addSong(String song){
        String[] tokens = song.split("/");
        songList.add(tokens[0]);
    }
}
