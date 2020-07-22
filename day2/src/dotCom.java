import java.util.ArrayList;

public class dotCom {
    private String name;
    private ArrayList<String> location = new ArrayList<String>();
    int num = 0;

    void setName(String s){
        name = s;
    }
    String getName(){
        return name;
    }
    void setLocation(ArrayList<String> loc){
        location = loc;
    }
    String checkYourself(String userGuess){
        String result = "miss";
        for (String cell: location) {
            if (cell.equals(userGuess)) {
                location.remove(cell);
                result = "hit";
                num++;
                break;
            }
        }
        if (num == 3) result = "kill";
        return result;
    }
}
