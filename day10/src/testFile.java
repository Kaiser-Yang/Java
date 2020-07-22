public class testFile {
    public static void main(String[] args)  {
        MyClass myClass = new MyClass();
        StringBuffer buffer = new StringBuffer("hello");
        myClass.changeValue(buffer);
        System.out.println(buffer.toString());
    }
}

class MyClass {

    void changeValue(final StringBuffer buffer) {
        buffer.append("world");
    }
}