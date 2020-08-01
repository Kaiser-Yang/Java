package cellmachine;

import java.awt.*;

public class Cell{
    private boolean alive;

    public void draw(Graphics g, int x, int y, int size){
        g.drawRect(x, y, size, size);
        if (alive){
            g.fillRect(x, y, size, size);
        }
    }
    public Cell(){
        alive = false;
    }
    public boolean isAlive(){
        return alive;
    }
    public void reborn(){
        alive = true;
    }
    public void die(){
        alive = false;
    }
}
