package animal;

import cell.Cell;

import java.awt.*;

public class Rabbit extends Animal implements Cell {
    public Rabbit(){
        super(20, 4);
    }
    public void draw(Graphics g, int x, int y, int size){
        int alpha = (int)((1 - getAge()) * 255);
        g.setColor(new Color(0, 0, 0, alpha));
        g.fillRect(x, y, size, size);
    }
    public Animal breed(){
        Animal ret = null;
        if (isScalable() && Math.random() < 0.05){
            ret = new Rabbit();
        }
        return ret;
    }
}
