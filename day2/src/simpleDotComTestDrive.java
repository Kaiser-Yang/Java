public class simpleDotComTestDrive {
    public static void main(String[] args){
        simpleDotCom dot = new simpleDotCom();
        int[] locations = {(int) (Math.random() * 10), (int) (Math.random() * 10), (int) (Math.random() * 10)};
        dot.setLocation(locations);
        String guessNum = "8";
        String result = dot.checkYourself(guessNum);
        if (result.equals("hit")) System.out.println("hit!!!");
        else if (result.equals("miss")) System.out.println("miss!!!");
        else System.out.println("You've destroyed the ship!!!");
    }
}
