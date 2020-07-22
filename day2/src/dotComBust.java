import java.util.*;

public class dotComBust {
    private int numOfGuesses = 0;
    private gameHelper helper = new gameHelper();
    private ArrayList<dotCom> dotComList = new ArrayList<dotCom>();
    String[] name = {"baidu.com", "qq.com", "xinlang.com", "tencent.com", "4399.com", "7k7k.com", "hao123.com", "mozhu.today"};

    private void initializeGame(){
        dotCom[] ship= new dotCom[3];
        for (int i = 0;i < 3;i ++) ship[i] = new dotCom();
        System.out.println("Your goal is to sink the three ships in the 7x7's map");
        System.out.print("The three ships are: ");
        for (int i = 0;i < 3;i ++){
            ship[i].setName(name[(int) (name.length * Math.random())]);
            System.out.print(ship[i].getName() + " ");
        }
        System.out.println("");
        System.out.println("Try to sink them all in the fewest times, good luck");
        for (int i= 0;i < 3;i ++) dotComList.add(ship[i]);
        for (dotCom dot: dotComList){
            ArrayList<String> newLocation = helper.placeDotCom(3);
            dot.setLocation(newLocation);
        }
    }
    private void startPlaying(){
        while(!dotComList.isEmpty()){
            numOfGuesses ++;
            String userGuess = helper.getUserInput("Please input your guess: ");
            checkUserGuess(userGuess);
        }
        finishGame();
    }
    private void checkUserGuess(String userGuess){
        String result = "miss";
        boolean ok = false;
        for (dotCom testEle: dotComList){
            result = testEle.checkYourself(userGuess);
            if (result.equals("hit")){
                ok = true;
                System.out.println(result);
                break;
            }
            if (result.equals("kill")) {
                ok = true;
                System.out.println("You sunk " + testEle.getName());
                dotComList.remove(testEle);
                break;
            }
        }
        if (!ok) System.out.println("miss");
    }
    private void finishGame(){
        System.out.println("All ships has been sunk! Your stock is now worthless.");
        if (numOfGuesses <= 18)
            System.out.println("It only take you " + numOfGuesses + " times to sink the ships, good job");
        else
            System.out.println("It take you many times(" + numOfGuesses + ") to sink the ships, you can try it again");
    }
    public static void main(String[] args){
        dotComBust newGame = new dotComBust();
        newGame.initializeGame();
        newGame.startPlaying();
    }
}
