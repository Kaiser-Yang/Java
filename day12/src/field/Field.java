package field;

import cell.Cell;
import location.Location;

public class Field {
    private int width, height;
    Cell[][] field;
    private int dx[] = {1, 1, 1, -1, -1, -1, 0, 0};
    private int dy[] = {-1, -1, 0, 1, 1, 0, 1, -1};

    public Field(int width, int height){
        this.width = width;
        this.height = height;
        field = new Cell[height][width];
    }

    public void setHeight(int height) {
        this.height = height;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public int getHeight() {
        return height;
    }
    public int getWidth() {
        return width;
    }

    public Cell placeCell(int x, int y, Cell cell){
        field[x][y] = cell;
        return field[x][y];
    }
    public Cell getCell(int x, int y){
        return field[x][y];
    }
    public Cell[] getNeighbor(int x, int y){
        Cell[] ans = new Cell[8];
        for (int i = 0;i < 8;i ++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && ny >= 0 && nx < height && ny < width) ans[i] = field[nx][ny];
            else ans[i] = null;
        }
        return ans;
    }
    public void remove(int x, int y){
        field[x][y] = null;
    }
    public Location[] getFreeNeighbor(int x, int y){
        int len = 0;
        for (int i = 0;i < 8;i ++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && ny >= 0 && nx < height && ny < width && field[nx][ny] == null) len ++;
        }
        Location[] loc = new Location[len];
        len = 0;
        for (int i = 0;i < 8;i ++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && ny >= 0 && nx < height && ny < width && field[nx][ny] == null) loc[len ++] = new Location(nx, ny);
        }
        return loc;
    }
    public void move(int x, int y, Location loc){
        field[loc.getX()][loc.getY()] = field[x][y];
        field[x][y] = null;
    }
    public void remove(Cell cell){

    }
    public void placeRandomAdj(int x, int y, Cell cell){
        Location[] loc = getFreeNeighbor(x, y);
        if (loc.length != 0){
            int pos = (int) (Math.random() * loc.length);
            field[loc[pos].getY()][loc[pos].getY()] = cell;
        }
    }
}
