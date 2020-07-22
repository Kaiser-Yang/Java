class movie{
    String name, genre;
    int rating;

    void playIt(){
        System.out.println("The movie is being played.");
    }
}
public class movieTestDrive {
    public static void main(String[] args){
        movie one = new movie();
        one.name = "X-MAN";
        one.genre = "Action";
        one.rating = 100;
        one.playIt();
    }
}
