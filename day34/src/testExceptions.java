public class testExceptions {
    public static void main(String[] args){
        String test = "yes";
        try{
            System.out.println("start try");
            doRisky(test);
            System.out.println("end risky");
        }
        catch(Exception se){
            System.out.println("Scary Exception");
        }
        finally {
            System.out.println("finally");
        }
        System.out.println("end of main");
    }
    static void doRisky(String test) throws Exception{
        if (test.equals("yes")) throw new Exception();
        System.out.println("end risky");

    }
}
