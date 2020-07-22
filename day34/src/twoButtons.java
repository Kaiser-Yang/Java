import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class twoButtons {
    JFrame frame;
    JLabel label;

    public static void main(String[] args){
        twoButtons gui = new twoButtons();
        gui.go();
    }
    public void go(){
        frame = new JFrame();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton labelButton = new JButton("Change label");
        JButton colorButton = new JButton("Change color");

        labelButton.addActionListener(new LabelListener());
        colorButton.addActionListener(new ColorListener());

        label = new JLabel("I'm a label");

        myDrawPanel drawPanel = new myDrawPanel();

        frame.setVisible(true);
        frame.setSize(500, 300);
        frame.getContentPane().add(BorderLayout.SOUTH, colorButton);
        frame.getContentPane().add(BorderLayout.CENTER, drawPanel);
        frame.getContentPane().add(BorderLayout.EAST, labelButton);
        frame.getContentPane().add(BorderLayout.WEST, label);
    }

    class LabelListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            label.setText("Ouch!");
        }
    }
    class ColorListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            frame.repaint();
        }
    }
}
