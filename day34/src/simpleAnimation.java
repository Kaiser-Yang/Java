import java.awt.*;
import javax.swing.*;

public class simpleAnimation {
    int x = 70, y = 70;

    public static void main(String[] args){
        simpleAnimation gui = new simpleAnimation();
        gui.go();
    }
    void go(){
        JFrame frame = new JFrame();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        myDrawPanel drawPanel = new myDrawPanel();

        frame.getContentPane().add(drawPanel);
        frame.setVisible(true);
        frame.setSize(300, 300);

        for (int i = 0;i < 130;i ++){
            x ++;
            y ++;
            drawPanel.repaint();

            try{
                Thread.sleep(50);
            }
            catch(Exception es){}
        }
    }
    class myDrawPanel extends JPanel{
        public void paintComponent(Graphics g){
            g.setColor(Color.white);
            g.fillRect(0, 0, this.getWidth(), this.getHeight());

            g.setColor(Color.blue);
            g.fillOval(x, y, 40, 40);
        }
    }
}
