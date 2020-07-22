public class GuessGame {
    Player p1, p2, p3;
    public void startGame(){
        p1 = new Player();
        p2 = new Player();
        p3 = new Player();

        int guessp1 = 0, guessp2 = 0, guessp3 = 0;
        boolean p1right = false, p2right = false, p3right = false;
        int targetNum = (int) (Math.random() * 10);
        System.out.println("I'm thinking of a number between 0 and 9 ... ");
        while(true){
            p1.guess();
            p2.guess();
            p3.guess();
            guessp1 = p1.number;
            guessp2 = p2.number;
            guessp3 = p3.number;
            if (guessp1 == targetNum) p1right = true;
            if (guessp2 == targetNum) p2right = true;
            if (guessp3 == targetNum) p3right = true;
            if (p1right || p2right || p3right){
                System.out.println("We have a winner.");
                System.out.println("Player one got it right? " + p1right);
                System.out.println("Player two got it right? " + p2right);
                System.out.println("Player three got it right? " + p3right);
                System.out.println("Guess Game is over!");
                break;
            }
            System.out.println("Players will have to try it again.");
        }
    }
}
