public class ValueTypes {
    public static void main(String[] args) {
        int a = 5;
        int b = a;  // b now has the value 5
        a = 15;     // changing a doesn't affect b
        System.out.println(b); // output: 5
        System.out.println(a); // output: 15

        // Passed to a method example:
        int num = 10;
        System.out.println("Before calling method: " + num); // output: 10
        
        modifyValue(num);

        System.out.println("After calling method: " + num); // output: 10
    }

    public static void modifyValue(int x) {
        x = 100;
        System.out.println("Inside method: " + x); // output: 100
    }

}
