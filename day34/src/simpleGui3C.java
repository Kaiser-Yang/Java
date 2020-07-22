import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

class myDrawPanel extends JPanel{
    public void paintComponent(Graphics g){
        g.setColor(new Color(44, 195, 200));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        int red = (int) (Math.random() * 255);
        int green = (int) (Math.random() * 255);
        int blue = (int) (Math.random() * 255);

        Color randomColor = new Color(red, green, blue);//通过三原色的比例获得颜色
        g.setColor(randomColor);
        g.fillOval(70, 70, 100, 100);
    }
}
public class simpleGui3C implements ActionListener{
    JButton button;
    JFrame frame;
    public static void main(String[] args){
        simpleGui3C gui = new simpleGui3C();
        gui.go();
    }
    void go(){
        button = new JButton("Change colors");
        button.addActionListener(this);

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        myDrawPanel drawPanel = new myDrawPanel();

        frame.setVisible(true);
        frame.setSize(300, 300);
        frame.getContentPane().add(BorderLayout.SOUTH, button);
        frame.getContentPane().add(BorderLayout.CENTER, drawPanel);
    }
    public void actionPerformed(ActionEvent event){
        frame.repaint();
    }
}
