package cellmachine;

import javax.swing.*;
import java.awt.*;

public class CellMachine {
    public static void main(String[] args){
        JFrame frame = new JFrame("Cell Machine");
        Field field = new Field(30, 30);

        for (int i = 0;i < 30;i ++ )
            for (int j = 0;j < 30;j ++) field.place(i, j, new Cell());
        for (int i = 0;i < 30;i ++){
            for (int j = 0;j < 30;j ++){
                Cell cell = field.getCell(i, j);
                if (Math.random() < 0.2) cell.reborn();
            }
        }
        View view = new View(field);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.add(BorderLayout.CENTER, view);
        frame.setVisible(true);
        frame.setSize(500, 520);

        for (int t = 0;t < 1000;t ++){
            for (int i = 0;i < 30;i ++)
                for (int j = 0;j < 30;j ++){
                    Cell cell = field.getCell(i, j);
                    Cell[] neighbor = field.getNeighbor(i, j);
                    int cnt = 0;
                    for (Cell c: neighbor)
                        if (c != null && c.isAlive()){
                            cnt ++;
                        }
                    if (cell.isAlive()){
                        if (cnt < 2 || cnt > 3) cell.die();
                    }
                    else if (cnt == 3){
                        cell.reborn();
                    }
                }
            try{
                Thread.sleep(250);
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
            frame.repaint();
        }
    }
}
