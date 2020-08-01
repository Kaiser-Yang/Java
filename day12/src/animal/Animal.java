package animal;

import location.Location;

import java.util.ArrayList;

public abstract class Animal {
    protected boolean alive;
    protected int age;
    protected int scalableAge;
    protected int ageLimit;

    public abstract Animal breed();

    public Animal(int ageLimit, int scalableAge){
        age = 0;
        this.ageLimit = ageLimit;
        this.scalableAge = scalableAge;
        alive = true;
    }
    public int getAge(){
        return age;
    }
    public boolean isAlive(){
        return alive;
    }
    public boolean isScalable(){
        return age >= scalableAge;
    }
    public String toString(){
        return "" + age + ": " + (isAlive() ? "alive" : "dead");
    }
    public void grow(){
        age ++;
        if (age >= ageLimit){
            die();
        }
    }
    public void die(){
        alive = false;
    }
    public Location move(Location[] freeAdj){
        Location ret = null;
        if (freeAdj.length > 0 && Math.random() < 0.02){
            ret = freeAdj[(int)(Math.random() * freeAdj.length)];
        }
        return ret;
    }
    public Animal feed(ArrayList<Animal> list){
        return null;
    }
}
