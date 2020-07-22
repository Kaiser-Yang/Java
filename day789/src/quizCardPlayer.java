import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class quizCardPlayer {
    private JTextArea display;
    private JTextArea answer;
    private ArrayList<quizCard> cardList = null;
    private quizCard currentCard;
    private int currentCardIndex = 0;
    private JFrame frame;
    private JButton nextButton;
    private boolean isShowAnswer;

    public static void main(String[] args){
        quizCardPlayer player = new quizCardPlayer();
        player.go();
    }
    void go(){
        frame = new JFrame("Quiz Card Player");
        JPanel mainPanel = new JPanel();
        Font font = new Font("微软雅黑", Font.BOLD, 24);

        display = new JTextArea(10, 20);
        display.setFont(font);
        display.setLineWrap(true);
        display.setWrapStyleWord(true);

        nextButton = new JButton("Show Question!");
        nextButton.addActionListener(new nextListener());

        JScrollPane qScroller = new JScrollPane(display);
        qScroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        qScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        mainPanel.add(qScroller);
        mainPanel.add(nextButton);

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem loadMenuItem = new JMenuItem("Load card set");
        loadMenuItem.addActionListener(new openListener());
        fileMenu.add(loadMenuItem);
        menuBar.add(fileMenu);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(640, 500);
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        frame.setJMenuBar(menuBar);
    }
    class openListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            JFileChooser fileOpen = new JFileChooser();
            fileOpen.showOpenDialog(frame);
            loadFile(fileOpen.getSelectedFile());
        }
    }
    void loadFile(File file){
        cardList = new ArrayList<quizCard>();
        try{
            BufferedReader read = new BufferedReader(new FileReader(file));
            String line = null;
            while((line = read.readLine()) != null){
                makeCard(line);
            }
            read.close();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    void makeCard(String card){
        String[] result = card.split("/");
        quizCard one = new quizCard(result[0], result[1]);
        cardList.add(one);
        System.out.println("have made a card.");
    }
    class nextListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            if (isShowAnswer){
                display.setText(currentCard.getAnswer());
                nextButton.setText("Next Card");
                isShowAnswer = false;
            }
            else{
                if (cardList != null && currentCardIndex < cardList.size())
                    currentCard = cardList.get(++ currentCardIndex);
                else{
                    display.setText("This was the last card");
                    nextButton.setEnabled(false);
                }
            }
        }
    }
}
