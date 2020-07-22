import java.io.*;

public class writeAFile {
    public static void main(String[] args){
        try{
            FileWriter file = new FileWriter("foo.txt");
            file.write("hello, java");
            file.close();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
