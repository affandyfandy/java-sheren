class PassingReference {
    int a, b;
    
    PassingReference(int x, int y) {
        a = x;
        b = y;
    }
 
    void ChangeValue(PassingReference obj) {
        obj.a += 5;
        obj.b += 10;
    }
}

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

        
        //Passed as parameters into function example:
        PassingReference object = new PassingReference(20, 30);
 
        System.out.println("Value of a = " + object.a + " and b = " + object.b);
 
        object.ChangeValue(object);
 
        System.out.println("Value of a = " + object.a + " and b = " + object.b);
    }
}
