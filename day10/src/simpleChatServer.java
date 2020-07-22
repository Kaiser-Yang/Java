import java.net.*;
import java.io.*;
import java.util.*;

public class simpleChatServer {
    ArrayList clientOutputStream;

    public static void main(String[] args){
        new simpleChatServer().go();
    }
    void go(){
        clientOutputStream = new ArrayList();
        try{
            ServerSocket serverSocket = new ServerSocket(4242);
            while(true){
                Socket clientSocket = serverSocket.accept();
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());
                clientOutputStream.add(writer);

                Thread t = new Thread(new clientHandle(clientSocket));
                t.start();
                System.out.println("got");
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    class clientHandle implements Runnable{
        BufferedReader reader;
        Socket sock;
        public clientHandle(Socket clientSock){
            try{
                sock = clientSock;
                InputStreamReader streamReader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(streamReader);
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        }
        public void run(){
            String message;
            try{
                while((message = reader.readLine()) != null){
                    System.out.println(message);
                    tellEveryone(message);
                }
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
    void tellEveryone(String message){
        Iterator it = clientOutputStream.iterator();
        while(it.hasNext()){
            try{
                PrintWriter writer = (PrintWriter) it.next();
                writer.println(message);
                writer.flush();
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
}
