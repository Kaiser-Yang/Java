import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

public class testOne {
    public static void main(String[] args){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);;
        frame.setSize(300, 300);

        JButton button = new JButton("center");
        frame.getContentPane().add(BorderLayout.CENTER, button);
        button = new JButton("north");
        frame.getContentPane().add(BorderLayout.NORTH, button);
        button = new JButton("south");
        frame.getContentPane().add(BorderLayout.SOUTH, button);
        button = new JButton("west");
        frame.getContentPane().add(BorderLayout.WEST, button);
        button = new JButton("east");
        frame.getContentPane().add(BorderLayout.EAST, button);
    }
}
