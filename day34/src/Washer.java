class Laundry{
    void doLaundry(){

    }
}
public class Washer {
    Laundry laundry = new Laundry();
    public void foo() throws Exception{
        laundry.doLaundry();
    }
    public static void main(String[] args){
        Washer a = new Washer();
        try{
            a.foo();
        }
        catch(Exception es){

        }
    }
}
