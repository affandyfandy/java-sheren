## Value Types and Reference Types

To put it simply, in value type, a copy of the original value is made and we manipulate the copy, not the original value. Meanwhile, when manipulating reference types, the original value is changed along with every change. Reference types is usually done for objects (non-primitives).

### Value Type

Value type exists locally in the place it's declared & holds its value inside its memory space. When it's passed somewhere else (assigned to a second variable, passed to a method, etc), its value is copied and two (or more) copies of the value now exist. Changing the value of first integer doesn't affect the original value.
Example:

```markdown
int a = 5;
int b = a;  // b now has the value 5
a = 15;     // changing a doesn't affect b
System.out.println(b); // output: 5
System.out.println(a); // output: 15
```

Passed to a method example:

```markdown
int num = 10;
System.out.println("Before calling method: " + num); // output: 10

modifyValue(num);

System.out.println("After calling method: " + num + "\n"); // output: 10

public static void modifyValue(int x) {
    x = 100;
    System.out.println("Inside method: " + x); // output: 100
}
```

By definition, pass by value means we are making a copy in memory of the actual parameter's value that is passed in, a copy of the contents of the actual parameter. Use pass by value when we are only "using" the parameter for some computation, not changing it for the client program.
Example:

```markdown
class PassingValue {
    public void example(int x, int y) {
        x++;
        y++;
    }
}

public class ValueTypes {
    public static void main(String[] args) {
        int x = 5;
        int y = 10;
 
        PassingValue object = new PassingValue();
 
        System.out.println("Value of x = " + x + " and y = " + y); // output: Value of x = 5 and y = 10
 
        object.example(x, y);

        System.out.println("Value of x = " + x + " and y = " + y); // output: Value of x = 5 and y = 10
    }
```

### Reference Type

Reference type isn't stored locally, but in memory allocated elsewhere (usually on the global memory heap for the process). When it's passed somewhere else (assigned to a second variable, passed to a method, etc), the value that it's copied is the address of the object being passed/assigned, so two places refer to the same object in memory. Changing the value of the object via the new
reference will change the original object.
Example:

```markdown
class MyClass {
    int number;
}

MyClass obj1 = new MyClass();
obj1.number = 10;
MyClass obj2 = obj1;  // obj2 references the same object as obj1
obj1.number = 20;
System.out.println(obj2.number);  // output: 20
```

In pass by reference, a copy of the address of the actual parameter is stored. Use pass by reference when we are changing the parameter passed in by the client program. Any changes to the formal parameter are reflected in the actual parameter in the calling environment as formal parameter receives a reference (or pointer) to the actual data.
Example:

```markdown
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
        PassingReference object = new PassingReference(20, 30);
 
        System.out.println("Value of a = " + object.a + " and b = " + object.b); //output: Value of a = 20 and b = 30
 
        object.ChangeValue(object);
 
        System.out.println("Value of a = " + object.a + " and b = " + object.b); //otput: Value of a = 25 and b = 40
    }
}
```

## Comparison

| Feature | Value Types | Reference Types |
| --- | --- | --- |
| Storage | Actual value in memory | Reference to the object's address in memory |
| Size | Fixed size | Variable size (depends on the object) |
| Example Types | int, char, boolean | String, arrays, custom objects |
| Assignment | Copies the value | Copies the reference |
| Passing to Methods | Pass-by-value | Pass-by-reference |
| Mutability | Immutable within a method | Mutable within a method |
