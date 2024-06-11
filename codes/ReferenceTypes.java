public class ReferenceTypes {
    public static void main(String[] args) {
        class MyClass {
            int number;
        }
        
        MyClass obj1 = new MyClass();
        obj1.number = 10;
        MyClass obj2 = obj1;  // obj2 references the same object as obj1
        obj1.number = 20;
        System.out.println(obj2.number);  // output: 20
    }
}
