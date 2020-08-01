package animal;

import java.awt.*;
import java.util.ArrayList;

import cell.Cell;


public class Fox  extends Animal implements Cell{
    public Fox(){
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
            ret = new Fox();
        }
        return ret;
    }
    public String toString(){
        return "Fox: " + super.toString();
    }
    public void longerLife(int inc){
        ageLimit += inc;
    }
    public Animal feed(ArrayList<Animal> list){
        Animal ret = null;
        if (Math.random() < 0.2){
            ret = list.get((int)(Math.random() * list.size()));
            longerLife(2);
        }
        return ret;
    }
}
