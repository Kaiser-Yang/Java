import java.util.*;

public class Game {

    private Room currentRoom;
    private HashMap<String, Handler> handlers = new HashMap<String, Handler>();


    public Game(){
        handlers.put("go", new HandlerGo(this));
        handlers.put("bye", new HandlerBye(this));
        handlers.put("help", new HandlerHelp(this));
        createRooms();
    }

    private void createRooms(){
        Room outside, lobby, pub, study, bedroom;

        outside = new Room("the outside");
        lobby = new Room("the lobby");
        pub = new Room("the pub");
        study = new Room("the study");
        bedroom = new Room("the bedroom");

        outside.setRoom("north", lobby);
        lobby.setRoom("south", outside);
        outside.setRoom("south", pub);
        pub.setRoom("north", outside);
        outside.setRoom("east", study);
        study.setRoom("west", outside);
        study.setRoom("north", bedroom);
        bedroom.setRoom("south", study);
        bedroom.setRoom("west", lobby);
        lobby.setRoom("east", bedroom);

        currentRoom = outside;
    }
    public static void main(String[] args){
        Game game = new Game();
        game.start();
    }
    private void play(){
        Scanner in = new Scanner(System.in);
        while(true){
            String line = in.nextLine();
            String[] ope = line.split(" ");
            Handler handler = handlers.get(ope[0]);
            if (handler == null) System.out.println("Wrong command!");
            else if (handler.isBye()) break;
            else if (ope.length == 2) handler.doCmd(ope[1]);
            else handler.doCmd("");
        }
        in.close();
    }
    public void go(String dir){
        Room nextRoom = currentRoom.getRoom(dir);
        if (nextRoom == null) System.out.println("Wrong command");
        else{
            currentRoom = nextRoom;
            printNowPlace();
        }
    }
    public void help(){
        System.out.println("The commands you can input are: go, bye and help, for example: you can input: go east");
    }
    private void printNowPlace(){
        if (currentRoom.getName().equals("exit")) System.out.println("you win!!! Input \"bye\" to exit or you can continue.");
        System.out.println("Now you are at the " + currentRoom.getName() + ".");
        System.out.print("Now you can go to ");
        System.out.println(currentRoom.getDirections());
        System.out.println();
    }
    public void start(){
        printWelcome();
        play();
        System.out.println("Good bye, see you next time!");
    }
    private void printWelcome(){
        System.out.println("Welcome to this game!");
        System.out.println("It's a boring one.");
        System.out.println("If you need help, please input \"help\"");
        System.out.println();
        printNowPlace();
    }
}
