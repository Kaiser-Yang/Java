public class HandlerHelp extends Handler{
    public HandlerHelp(Game game){
        super(game);
    }
    public void doCmd(String ope){
        game.help();
    }
}
