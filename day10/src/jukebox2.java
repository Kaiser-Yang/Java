import java.io.*;
import java.util.*;

class song implements Comparable<song>{
    private String title;
    private String artist;
    private String rating;
    private String bpm;

    public boolean equals(Object a){
        song one = (song) a;
        return one.getTitle().equals(this.getTitle());
    }
    public int hashCode(){
        return title.hashCode();
    }
    public int compareTo(song a) {
        return title.compareTo(a.getTitle());
    }

    public song(String a, String b, String c, String d){
        title = a;
        artist = b;
        rating = c;
        bpm = d;
    }
    String getTitle(){
        return title;
    }
    String getArtist(){
        return artist;
    }
    String getRating(){
        return rating;
    }
    String getBpm(){
        return bpm;
    }
}
public class jukebox2 {
    ArrayList<song> songList = new ArrayList<song>();

    public static void main(String[] args){
        new jukebox2().go();
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
        Collections.sort(songList);
        System.out.println(songList);
    }
    void addSong(String line){
        String[] song = line.split("/");
        songList.add(new song(song[0], song[1], song[2], song[3]));
    }
}
