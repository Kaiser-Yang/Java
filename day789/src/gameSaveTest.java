import java.io.*;

class gameCharacter implements Serializable{
    int power;
    String type;
    String[] weapons;
    public gameCharacter(int p, String t, String[] w){
        power = p;
        type = t;
        weapons = w;
    }
    int getPower(){
        return power;
    }
    String getType(){
        return type;
    }
    String getWeapons(){
        String ans = "";
        int len = weapons.length;
        for (int i = 0;i < len;i ++) ans +=  weapons[i] + " ";
        return ans;
    }
    void print(){
        System.out.println(getPower() + " " + getType() + " " + getWeapons());
    }
}
public class gameSaveTest {
    public static void main(String[] args){
        gameCharacter one = new gameCharacter(50, "Elf", new String[] {"bow", "sword", "dust"});
        gameCharacter two = new gameCharacter(200, "Troll", new String[] {"bare hands", "big ax"});
        gameCharacter three = new gameCharacter(120, "Magician", new String[] {"spells", "invisibility"});

        try{
            FileOutputStream file = new FileOutputStream("game.ser");
            ObjectOutputStream os = new ObjectOutputStream(file);
            os.writeObject(one);
            os.writeObject(two);
            os.writeObject(three);
            os.close();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }

        try{
            FileInputStream file = new FileInputStream("game.ser");
            ObjectInputStream os = new ObjectInputStream(file);
            one = (gameCharacter)os.readObject();
            two = (gameCharacter)os.readObject();
            three = (gameCharacter)os.readObject();
            one.print();
            two.print();
            three.print();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
