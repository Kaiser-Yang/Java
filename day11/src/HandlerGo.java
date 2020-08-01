public class HandlerGo extends Handler{
    public HandlerGo(Game game){
        super(game);
    }
    public void doCmd(String ope){
        game.go(ope);
    }
}
