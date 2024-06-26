package Q1;
public class MyClass implements FirstInterface, SecondInterface {
    @Override
    public void firstMethod(String string) {
        System.out.println("First method implementation: " + string);
    }

    @Override
    public void secondMethod() {
        System.out.println("Second method implementation");
    }

    @Override
    public void log(String str) {
        System.out.println("MyClass log: " + str);
        // We can use this or super keyword like below:
        // SecondInterface.super.log(str);
    }

    public static void main(String[] args) {
        MyClass myClass = new MyClass();

        myClass.firstMethod("Hi, this is the first method");
        myClass.secondMethod();
        myClass.log("Test log message");
    }
}
