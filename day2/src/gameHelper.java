import java.io.*;
import java.util.*;

public class gameHelper {
    private static final String alphabet = "ABCDEFG";
    private boolean[][] vis = new boolean[7][7];

    public String getUserInput(String prompt){
        String inputLine = null;
        System.out.print(prompt);
        try{
            BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
            inputLine = is.readLine();
            if (inputLine.length() == 0) return null;
        }
        catch(IOException e){
            System.out.println("IOException: " + e);
        }
        return inputLine;
    }
    public ArrayList<String> placeDotCom(int size){
        ArrayList<String> pos = new ArrayList<String>();
        String nowPos = null;
        int x, y;//横纵坐标
        int flag = (int) (Math.random() * 2);
        if (flag == 0){//横向放置
            while(true){
                boolean ok = true;
                x = (int) (Math.random() * 7);
                y = (int) (Math.random() * 5);
                for (int i = 0;i < 3;i ++) if (vis[x][y + i]){
                    ok = false;
                    break;
                }
                if (ok){
                    for (int i = 0;i < 3;i ++){
                        vis[x][y + i] = true;
                        nowPos = String.valueOf(alphabet.charAt(x));
                        pos.add(nowPos.concat(Integer.toString(y + i)));
//                        System.out.print(nowPos.concat(Integer.toString(y + i)) + " ");
                    }
                    break;
                }
            }
        }
        else{//竖向放置
            while(true){
                boolean ok = true;
                x = (int) (Math.random() * 5);
                y = (int) (Math.random() * 7);
                for (int i = 0;i < 3;i ++) if (vis[x + i][y]){
                    ok = false;
                    break;
                }
                if (ok){
                    for (int i = 0;i < 3;i ++){
                        vis[x + i][y] = true;
                        nowPos = String.valueOf(alphabet.charAt(x + i));
                        pos.add(nowPos.concat(Integer.toString(y)));
//                        System.out.print(nowPos.concat(Integer.toString(y)) + " ");
                    }
                    break;
                }
            }
        }
//        System.out.println();
        return pos;
    }
}
