class Dog{
    int size;
    String breed, name;

    void bark(){
        System.out.print("Ruff, ruff, ruff!");
    }
}
public class DogTest {
    public static void main(String[] args){
        Dog myDog = new Dog();
        myDog.size = 40;
        myDog.breed = "xxx";
        myDog.name = "Lucy";
        myDog.bark();
    }
}
