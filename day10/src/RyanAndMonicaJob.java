import java.lang.*;

class bankAccount{
    private int balance = 100;
    public int getBalance(){
        return balance;
    }
    public void withdraw(int amount){
        balance -= amount;
    }
}
public class RyanAndMonicaJob implements Runnable{
    private bankAccount account = new bankAccount();
    public static void main(String[] args){
        RyanAndMonicaJob job = new RyanAndMonicaJob();
        Thread one = new Thread(job);
        Thread two = new Thread(job);
        one.setName("Ryan");
        two.setName("Monica");
        one.start();
        two.start();
    }
    public void run(){
        for (int i = 0;i < 10;i ++){
            makeWithdraw(10);
            if (account.getBalance() < 0){
                System.out.println("Over Draw!");
            }
        }
    }
    public synchronized void makeWithdraw(int amount){
        if (account.getBalance() >= amount){
            System.out.println(Thread.currentThread().getName() + " is about to withdraw money");
            try{
                System.out.println(Thread.currentThread().getName() + " is going to sleep");
                Thread.sleep(500);
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " woke up");
            account.withdraw(amount);
            System.out.println(Thread.currentThread().getName() + " complete the withdraw");
        }
        else
            System.out.println("Sorry, not enough for " + Thread.currentThread().getName());
    }
}
