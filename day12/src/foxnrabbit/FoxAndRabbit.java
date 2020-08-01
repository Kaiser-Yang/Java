package foxnrabbit;

import location.Location;
import animal.Rabbit;
import cell.Cell;
import field.Field;
import view.View;
import animal.*;
import javax.swing.*;
import java.util.ArrayList;

public class FoxAndRabbit {
    private Field theField;
    private View theView;

    public FoxAndRabbit(int size){
        theField = new Field(size, size);
        for (int i = 0;i < size;i ++){
            for (int j = 0;j < size;j ++){
                double probability = Math.random();
                if (probability < 0.05) theField.placeCell(i, j, new Fox());
                else if (probability < 0.15) theField.placeCell(i, j, new Rabbit());
                else theField.placeCell(i, j, null);
            }
        }
        theView = new View(theField);
        JFrame frame= new JFrame("Fox And Rabbit");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setResizable(false);
        frame.add(theView);
        frame.pack();
        frame.setVisible(true);
        for (int i = 0;i < 1000;i ++) start();
    }
    public void start(){
        for (int i = 0;i < theField.getHeight();i ++){
            for (int j = 0;j < theField.getWidth();j ++){
                Cell cell = theField.getCell(i, j);
                if (cell != null){
                    Animal animal = (Animal) cell;
                    animal.grow();
                    if (animal.isAlive()){
                        Location loc = animal.move(theField.getFreeNeighbor(i, j));
                        if (loc != null){
                            theField.move(i, j, loc);
                        }
                        Cell[] neighbors = theField.getNeighbor(i, j);
                        ArrayList<Animal> listRabbit = new ArrayList<Animal>();
                        for (Cell an: neighbors){
                            if (an instanceof Rabbit){
                                listRabbit.add((Rabbit) an);
                            }
                        }
                        if (!listRabbit.isEmpty()){
                            Animal fed = animal.feed(listRabbit);
                            if (fed != null){
                                theField.remove((Cell)fed);
                            }
                        }
                        Animal baby = animal.breed();
                        if (baby != null){
                            theField.placeRandomAdj(i, j, (Cell) baby);
                        }
                    }
                    else theField.remove(i, j);
                }
            }
        }
    }
    public static void main(String[] args){
        new FoxAndRabbit(30);
    }
}
