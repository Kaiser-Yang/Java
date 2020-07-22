import javax.sound.midi.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;

class myDrawPanel extends JPanel implements ControllerEventListener {
    boolean msg = false;

    public void controlChange(ShortMessage event){
        msg = true;
        repaint();
    }

    public void paintComponent(Graphics g){
        if (msg){
            Graphics g2 = (Graphics2D) g;
            int r = (int) (Math.random() * 250);
            int gr = (int) (Math.random() * 250);
            int b = (int) (Math.random() * 250);

            g.setColor(new Color(r, gr, b));

            int ht = (int) ((Math.random() * 120) + 10);
            int width = (int) ((Math.random() * 120) + 10);
            int x = (int)((Math.random() * 40) + 10);
            int y = (int) ((Math.random() * 40) + 10);
            g.fillRect(x, y, width, ht);
            msg = false;
        }
    }
}
public class miniMusicPlayer3 {
    static JFrame frame = new JFrame("My First Music Video");
    static myDrawPanel ml;

    public static void main(String[] args){
        miniMusicPlayer3 mini = new miniMusicPlayer3();
        mini.go();
    }
    public void setUpGui(){
        ml = new myDrawPanel();
        frame.setContentPane(ml);
        frame.setBounds(30, 30, 300, 300);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void go(){
        setUpGui();
        try{
            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.open();
            sequencer.addControllerEventListener(ml, new int[] {127});
            Sequence seq = new Sequence(Sequence.PPQ, 4);

            Track track = seq.createTrack();

            int r = 0;
            for (int i = 0;i < 60;i += 4){
                r = (int) ((Math.random() * 50) + 1);
                track.add(makeEvent(144, 1, r, 100, i));
                track.add(makeEvent(176, 1, 127, 0, i));
                track.add(makeEvent(128, 1, r, 100, i + 2));
            }
            sequencer.setSequence(seq);
            sequencer.setTempoInBPM(120);
            sequencer.start();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public MidiEvent makeEvent(int cmd, int chan, int tone, int volume, int tick){
        MidiEvent event = null;
        try{
            ShortMessage a = new ShortMessage();
            a.setMessage(cmd, chan, tone, volume);
            event = new MidiEvent(a, tick);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return event;
    }
}
