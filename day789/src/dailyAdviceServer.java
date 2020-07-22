import java.io.*;
import java.net.*;

public class dailyAdviceServer {
    String[] adviceList = {"Take smaller bites", "Go for the tight jeans. No they do NOT make you look fat"
                            , "One word: inappropriate", "Just for today, be honest. Tell your boss what you really think",
                            "You might want to rethink that haircut"};
    public static void main(String[] args){
        dailyAdviceServer server = new dailyAdviceServer();
        server.go();
    }
    public String getAdvice(){
        int pos = (int) (Math.random() * adviceList.length);
        return adviceList[pos];
    }
    public void go(){
        try{
            ServerSocket server = new ServerSocket(4242);

            Socket sock = server.accept();

            PrintWriter writer = new PrintWriter(sock.getOutputStream());
            String advice = getAdvice();
            writer.println(advice);
            writer.close();
            System.out.println(advice);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
