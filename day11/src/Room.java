import java.util.HashMap;

public class Room {
    private String name;
    private HashMap<String, Room> exits = new HashMap<String, Room>();

    public Room(String name){
        this.name = name;
    }
    public void setRoom(String dir, Room room){
        exits.put(dir, room);
    }
    public void setName(String name){
        this.name = name;
    }
    public StringBuffer getDirections(){
        StringBuffer ans = new StringBuffer();
        if (exits.get("north") != null) ans.append("north ");
        if (exits.get("south") != null) ans.append("south ");
        if (exits.get("west") != null) ans.append("west ");
        if (exits.get("east") != null) ans.append("east ");
        return ans;
    }
    public String getName(){
        return name;
    }
    public Room getRoom(String dir){
        return exits.get(dir);
    }
}
