import java.lang.*;

public class test implements Runnable{
    private int balance = 0;
    public static void main(String[] args){
        test job = new test();
        Thread one = new Thread(job);
        Thread two = new Thread(job);
        one.start();
        two.start();
    }
    int getBalance(){
        return balance;
    }
    public synchronized void run(){
        for(int i = 0;i < 25;i ++){
            increment();
            System.out.println(balance);
        }
    }
    void increment(){
        int i = balance;
        try{
            Thread.currentThread().sleep(1);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        balance = i + 1;
    }
}
