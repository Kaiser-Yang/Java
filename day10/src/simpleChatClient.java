import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.lang.*;
import java.awt.event.*;
import java.net.*;

public class simpleChatClient {
    JTextArea incoming;
    JTextField outgoing;
    BufferedReader reader;
    PrintWriter writer;
    Socket sock;

    public static void main(String[] args){
        simpleChatClient client = new simpleChatClient();
        client.go();
    }
    class sendListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            try{
                writer.println(outgoing.getText());
                writer.flush();
                outgoing.setText("");
                outgoing.requestFocus();
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
    class incomingRead implements Runnable{
        public void run(){
            String message;
            try{
                while((message = reader.readLine()) != null) incoming.append(message + '\n');
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
    public void go(){
        incoming = new JTextArea(15, 50);
        incoming.setLineWrap(true);
        incoming.setWrapStyleWord(true);
        incoming.setEditable(false);

        JScrollPane scroller = new JScrollPane(incoming);
        scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        outgoing = new JTextField(20);

        JButton send = new JButton("Send");
        send.addActionListener(new sendListener());

        JPanel mainPanel = new JPanel();
        mainPanel.add(scroller);
        mainPanel.add(outgoing);
        mainPanel.add(send);

        JFrame frame = new JFrame("Ludicrously Simple Chat Client");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        frame.setVisible(true);
        frame.setSize(600, 400);

        setUpNetworking();

        Thread readerThread = new Thread(new incomingRead());
        readerThread.start();
    }
    private void setUpNetworking(){
        try{
            sock = new Socket("127.0.0.1", 4242);
            InputStreamReader streamReader = new InputStreamReader(sock.getInputStream());
            reader = new BufferedReader(streamReader);
            writer = new PrintWriter(sock.getOutputStream());
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
