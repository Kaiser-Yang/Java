package cellmachine;

import cellmachine.Cell;

public class Field {

    private int column, row;
    private Cell[][] cells;
    private int[] dx = {0, 0, 1, 1, 1, -1, -1, -1};
    private int[] dy = {1, -1, 0, 1, -1, 0, 1, -1};

    public Field(int row, int column){
        this.row = row;
        this.column = column;
        cells = new Cell[row][column];
    }
    public void place(int x, int y, Cell cell){
        cells[x][y] = cell;
    }
    public Cell getCell(int x, int y){
        return cells[x][y];
    }
    public int getColumn() {
        return column;
    }
    public int getRow() {
        return row;
    }
    public Cell[] getNeighbor(int x, int y){
        Cell[] ret = new Cell[8];
        for (int i = 0;i < 8;i ++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && ny >= 0 && nx < row && ny < row) ret[i] = cells[nx][ny];
            else ret[i] = null;
        }
        return ret;
    }
}
