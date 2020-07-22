import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

class expresionHandle {
    private static String str;
    private static Stack<Character> operators = new Stack<Character>();
    private static Stack<Double> operands = new Stack<Double>();

    public static boolean canInput(String s, char ch, boolean shift){
        if (ch == KeyEvent.VK_ENTER || ch == KeyEvent.VK_DECIMAL || (ch == KeyEvent.VK_EQUALS && !shift)) return true;
        if (ch == KeyEvent.VK_BACK_SPACE) return true;
        if (ch == KeyEvent.VK_ESCAPE) return true;
        str = s;
        if (s == null || s.isEmpty()) return isDigit(ch);//刚开始输入一定要是数字
        char las = s.charAt(s.length() - 1);

        if (isDigit(las)) return true;

        if (isDot(las)) return isDigit(ch);

        if (isOperator(las)) return isDigit(ch);

        return false;
    }
    public static boolean empty(){
        return operators.isEmpty();
    }
    public static boolean canCalculate(char ch){
        if (operators.isEmpty()) return false;
        return operands.size() >= 2;
    }
    public static void calculate() throws Exception{
        double a = operands.peek();
        operands.pop();
        double b = operands.peek();
        operands.pop();
        char ope = operators.peek();
        operators.pop();

        double ans = 0.0;
        switch (ope){
            case '+':
                ans = b + a;
                break;
            case '-':
                ans = b - a;
                break;
            case '*':
                ans = b * a;
                break;
            case '/':
                if (Math.abs(a) < 1e-9) throw new Exception();
                ans = b / a;
                break;
        }
        operands.push(ans);
    }
    public static double result() throws Exception{
        int len = str.length();

        for (int i = 0;i < len;i ++){
            if (isDigit(str.charAt(i))){
                int i1 = i + 1;
                while(i1 < len && (isDigit(str.charAt(i1)) || isDot(str.charAt(i1)))) i1 ++;
                operands.push(Double.parseDouble(str.substring(i, i1)));
                i = i1 - 1;
            }
            else{
                while(canCalculate(str.charAt(i))) calculate();
                operators.push(str.charAt(i));
            }
        }
        while(!empty()) calculate();
        return operands.peek();
    }
    public static void setExpression(String s){
        str = s;
        operands.clear();
        operators.clear();
    }
    public static boolean isDigit(char ch){
        return ch >= '0' && ch <= '9';
    }
    public static boolean isDot(char ch){
        return ch == '.';
    }
    public static boolean isOperator (char ch){
        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }
}
public class calculator {
    private JFrame mainFrame;
    private JPanel buttons;
    private JPanel text;
    private JTextField box;

    private JButton[] num;
    private JButton[] character;
    private JButton dot;
    private JButton equal;

    public static void main(String[] args){
        calculator cal = new calculator();
        cal.init();
        cal.setFont(new Font("宋体", Font.BOLD, 17));//字体， 加粗， 大小
        cal.addKeyboardEvent();
        cal.addMouseEvent();
    }
    private boolean isEmpty(){
        return box.getText() == null || box.getText().length() == 0;
    }
    private void showMessage(String s){
        JOptionPane.showMessageDialog(null, s, "提示信息: ", JOptionPane.INFORMATION_MESSAGE);
    }
    private void setResult(){
        if (!isEmpty()){
            expresionHandle.setExpression(box.getText());
            try{
                showMessage("答案:" + String.valueOf(expresionHandle.result()));
            }
            catch(Exception e){
                showMessage("除数为0， 请重新输入");
            }
            box.setText("");
        }
        else showMessage("表达式为空， 请重新输入。");
    }
    private class myMouseListener implements MouseListener{
        private Color defaultColor = null;
        public void mouseClicked(MouseEvent event) {
            JButton button = (JButton) event.getSource();
            if (expresionHandle.canInput(box.getText(), button.getText().charAt(0), false)){
                int code = (int) button.getText().charAt(0);
                if (code >= '0' && code <= '9')
                    box.setText(box.getText() + (code - '0'));//显示数字
                else if (code == '+')
                    box.setText(box.getText() + "+");
                else if (code == '-')
                    box.setText(box.getText() + "-");
                else if (code == '*')
                    box.setText(box.getText() + "*");
                else if (code == '/')
                    box.setText(box.getText() + "/");
                else if (code == '.')
                    box.setText(box.getText() + ".");
                else if (code == '=')
                    setResult();
                mainFrame.requestFocus();
            }
        }
        public void mouseEntered(MouseEvent event) {
            JButton button = (JButton) event.getSource();
            defaultColor = button.getBackground();
            button.setBackground(new Color(180, 180, 180));
        }
        public void mouseExited(MouseEvent event) {
            JButton button = (JButton) event.getSource();
            if (defaultColor == null) defaultColor = button.getBackground();
            button.setBackground(defaultColor);
        }
        public void mousePressed(MouseEvent mouseEvent) {}
        public void mouseReleased(MouseEvent mouseEvent) {}
    }
    private void addMouseEvent()
    {
        for (int i = 0;i < 10;i ++) num[i].addMouseListener(new myMouseListener());

        for (int i = 0;i < 4;i ++) character[i].addMouseListener(new myMouseListener());

        equal.addMouseListener(new myMouseListener());

        dot.addMouseListener(new myMouseListener());
    }
    private class myKeyListener implements KeyListener{
        private boolean shift = false;//按没按shift

        public void keyTyped(KeyEvent event) {}//按下释放连贯动作
        public void keyPressed(KeyEvent event) {//按下键盘
            if (event.getKeyCode() == KeyEvent.VK_SHIFT) shift = true;
        }
        public void keyReleased(KeyEvent event) {//释放键盘
            if (expresionHandle.canInput(box.getText(), event.getKeyChar(), shift)){//判断能否继续输入
                int code = event.getKeyCode();//输入的是什么
                if (code >= KeyEvent.VK_0 && code <= KeyEvent.VK_7)
                    box.setText(box.getText() + (code - KeyEvent.VK_0));//显示数字
                else if (code >= KeyEvent.VK_NUMPAD0 && code <= KeyEvent.VK_NUMPAD9)
                    box.setText(box.getText() + (code - KeyEvent.VK_NUMPAD0));
                else if (code == KeyEvent.VK_8)
                    box.setText(box.getText() + (shift ? "*" : "8"));//判断是8还是*
                else if (code == KeyEvent.VK_9)
                    box.setText(box.getText() + "9");
                else if (code == KeyEvent.VK_DECIMAL || code == KeyEvent.VK_PERIOD)
                    box.setText(box.getText() + ".");
                else if (code == KeyEvent.VK_PLUS || code == KeyEvent.VK_ADD)
                    box.setText(box.getText() + "+");
                else if (code == KeyEvent.VK_MINUS || code == KeyEvent.VK_SUBTRACT)
                    box.setText(box.getText() + "-");
                else if (code == KeyEvent.VK_MULTIPLY)
                    box.setText(box.getText() + "*");
                else if (code == KeyEvent.VK_SLASH || code == KeyEvent.VK_DIVIDE)
                    box.setText(box.getText() + "/");
                else if (code == KeyEvent.VK_ESCAPE)
                    box.setText("");
                else if (code == KeyEvent.VK_EQUALS){
                    if (shift) box.setText(box.getText() + "+");
                    else setResult();
                }
                else if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_SEPARATOR) setResult();
                else if (code == KeyEvent.VK_BACK_SPACE && !box.getText().equals(""))//退格
                    box.setText(box.getText().substring(0, box.getText().length() - 1));

                shift = false;
            }
        }
    }
    private void addKeyboardEvent(){
        mainFrame.addKeyListener(new myKeyListener());
    }
    private void setFont(Font font){
        for (int i = 0;i < 10;i ++) num[i].setFont(font);
        for (int i = 0;i < 4;i ++) character[i].setFont(font);
        dot.setFont(font);
        equal.setFont(font);
    }
    private void init(){
        text = new JPanel();
        mainFrame = new JFrame("Calculator");
        buttons = new JPanel();//用于存放按钮
        box = new JTextField();
        box.setPreferredSize(new Dimension(300, 25));
        box.setEditable(false);
        text.add(box);
        num = new JButton[10];
        dot = new JButton(".");
        equal = new JButton("=");
        character = new JButton[4];//存储加减乘除按钮
        character[0] = new JButton("+");
        character[1] = new JButton("-");
        character[2] = new JButton("*");
        character[3] = new JButton("/");
        for (int i = 0;i < 10;i ++) num[i] = new JButton(String.valueOf(i));

        buttons.setLayout(new GridLayout(4, 4, 20, 20));
        buttons.setPreferredSize(new Dimension(300, 300));

        buttons.add(num[7]);
        buttons.add(num[8]);
        buttons.add(num[9]);
        buttons.add(character[3]);

        buttons.add(num[4]);
        buttons.add(num[5]);
        buttons.add(num[6]);
        buttons.add(character[2]);

        buttons.add(num[1]);
        buttons.add(num[2]);
        buttons.add(num[3]);
        buttons.add(character[1]);

        buttons.add(character[0]);
        buttons.add(num[0]);
        buttons.add(dot);
        buttons.add(equal);

        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));
        mainFrame.setSize(450, 450);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.getContentPane().add(text);//显示屏
        mainFrame.getContentPane().add(buttons);
    }
}
