package Q1;
public interface SecondInterface {
    void secondMethod();

    default void log(String str) {
        System.out.println("This method is default implementation" + str);
    }
}
