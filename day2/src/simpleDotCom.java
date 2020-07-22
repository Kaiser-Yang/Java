public class simpleDotCom {
    int[] location = new int[3];
    int cnt = 0;
    boolean[] has = {true, true, true};

    void setLocation(int[] locations){
        for (int i = 0;i < 3;i ++) location[i] = locations[i];
    }

    String checkYourself(String s){
        int num = Integer.parseInt(s);
        String result = "miss";
        boolean flag = false;
        for (int cell: location){
            if (cell == num){
                flag = true;
                cnt ++;
                break;
            }
        }
        if (flag == true) result = "hit";
        if (cnt == 3) result = "kill";
        return result;
    }
}
