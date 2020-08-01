package cellmachine;

import javax.swing.*;
import java.awt.*;

public class View extends JPanel {
    private Field theField;
    private static final int GRID_SIZE = 16;

    public View(Field field){
        theField = field;
    }

    public void paint(Graphics g){
        super.paint(g);
        for (int i = 0;i < theField.getRow();i ++)
            for (int j = 0;j < theField.getColumn();j ++){
                Cell cell = theField.getCell(i, j);
                if (cell != null){
                    cell.draw(g, i * GRID_SIZE, j * GRID_SIZE, GRID_SIZE);
                }
            }
    }
}
