import javax.swing.*;
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class simpleChatClientA {
    JTextField outgoing;
    PrintWriter writer;
    Socket sock;

    public static void main(String[] args){
        simpleChatClientA worker = new simpleChatClientA();
        worker.go();
    }
    class sendButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            try{
                writer.println(outgoing.getText());
                writer.flush();
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
            outgoing.setText("");
            outgoing.requestFocus();
        }
    }
    public void go(){
        JFrame frame = new JFrame("Ludicrously Simple Chat Client");
        JPanel mainPanel = new JPanel();
        outgoing = new JTextField(20);
        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(new sendButtonListener());
        mainPanel.add(outgoing);
        mainPanel.add(sendButton);
        frame.getContentPane().add(mainPanel);
        frame.setVisible(true);
        frame.setSize(400, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUpNetworking();
    }
    private void setUpNetworking(){
        try{
            sock = new Socket("127.0.0.1", 5000);
            writer = new PrintWriter(sock.getOutputStream());
            System.out.println("networking established");
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
