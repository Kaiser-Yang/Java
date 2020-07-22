import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import javax.sound.midi.*;

public class beatBox {
    JPanel mainPanel;
    ArrayList<JCheckBox> checkBoxLIst;
    Sequencer sequencer;
    Sequence sequence;
    Track track;
    JFrame frame;

    String[] instrumentsName = {"Bass Drum", "Closed Hi-Hat", "Open Hi-Hat", "Acoustic Snare", "Crash Cymbal", "Hand Clap",
            "High Tom", "Hi Bongo", "Maracas", "Whistle", "Low Conga", "Cowbell", "Vibraslap", "Low-mid Tom", "High Agogo",
            "Open Hi Conga"};

    int[] instruments = {35, 42, 46, 38, 49, 39, 50, 60, 70, 72, 64, 56, 58, 47, 67, 63};

    public static void main(String[] args){
        beatBox bb = new beatBox();
        bb.buildGUI();
    }
    public void buildGUI() {
        frame = new JFrame("Cyber Beat-Box");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BorderLayout layout = new BorderLayout();
        JPanel background = new JPanel(layout);
        background.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        checkBoxLIst = new ArrayList<JCheckBox>();
        Box buttonBox = new Box(BoxLayout.Y_AXIS);

        JButton start = new JButton("Start");
        start.addActionListener(new startListener());
        buttonBox.add(start);

        JButton stop = new JButton("Stop");
        stop.addActionListener(new stopListener());
        buttonBox.add(stop);

        JButton tempUP = new JButton("Tempo Up");
        tempUP.addActionListener(new tempUpListener());
        buttonBox.add(tempUP);

        JButton tempDown = new JButton("Tempo down");
        tempDown.addActionListener(new tempDownListener());
        buttonBox.add(tempDown);

        Box nameBox = new Box(BoxLayout.Y_AXIS);
        for (int i = 0;i < 16;i ++) nameBox.add(new Label(instrumentsName[i]));

        background.add(BorderLayout.WEST, nameBox);
        background.add(BorderLayout.EAST, buttonBox);

        GridLayout grid = new GridLayout(16, 16);
        grid.setVgap(1);//竖直间距
        grid.setHgap(2);//水平间距
        mainPanel = new JPanel(grid);//创建网格面板

        for (int i = 0;i < 256;i ++){
            JCheckBox c = new JCheckBox();
            c.setSelected(false);
            checkBoxLIst.add(c);
            mainPanel.add(c);
        }

        background.add(BorderLayout.CENTER, mainPanel);

        frame.getContentPane().add(background);

        setUpMidi();
        frame.setVisible(true);
        frame.setBounds(50, 50, 300, 300);
        frame.pack();
    }
    public void setUpMidi(){
        try{
            sequencer = MidiSystem.getSequencer();
            sequencer.open();
            sequence = new Sequence(Sequence.PPQ, 4);
            track = sequence.createTrack();
            sequencer.setTempoInBPM(120);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void buildTrackAndStart(){
        int[] trackList = null;

        sequence.deleteTrack(track);
        track = sequence.createTrack();

        for (int i = 0;i < 16;i ++){
            trackList = new int[16];
             int key = instruments[i];
              for (int j = 0;j < 16;j ++){
                  JCheckBox jc = (JCheckBox) checkBoxLIst.get(j + 16 * i);
                  if (jc.isSelected()) trackList[j] = key;
                  else trackList[j] = 0;
              }
              makeTracks(trackList);
              track.add(makeEvent(176, 1, 127, 0, 16));
        }
        track.add(makeEvent(192, 9, 1, 0, 15));
        try{
            sequencer.setSequence(sequence);
            sequencer.setLoopCount(sequencer.LOOP_CONTINUOUSLY);//无限循环
            sequencer.setTempoInBPM(120);
            sequencer.start();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void makeTracks(int[] list){
        for (int i = 0;i < 16;i ++){
            int key = list[i];
            if (key == 0) continue;
            track.add(makeEvent(144, 9, key, 100, i));
            track.add(makeEvent(128, 9, key, 100, i + 1));
        }
    }
    public MidiEvent makeEvent(int cmt, int chan, int one, int two, int tick){
        MidiEvent event = null;
        try{
            ShortMessage a = new ShortMessage();
            a.setMessage(cmt, chan, one, two);
            event = new MidiEvent(a, tick);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return event;
    }
    public class startListener implements ActionListener{
        public void actionPerformed(ActionEvent actionEvent) {
            buildTrackAndStart();
        }
    }
    public class stopListener implements ActionListener{
        public void actionPerformed(ActionEvent actionEvent){
            sequencer.stop();
        }
    }
    public class tempUpListener implements ActionListener{
        public void actionPerformed(ActionEvent actionEvent){
            float tempoFactor = sequencer.getTempoFactor();
            sequencer.setTempoInBPM(tempoFactor * 1.1f);
        }
    }
    public class tempDownListener implements ActionListener{
        public void actionPerformed(ActionEvent actionEvent){
            float tempoFactor = sequencer.getTempoFactor();
            sequencer.setTempoInBPM(tempoFactor * 0.9f);
        }
    }
}
