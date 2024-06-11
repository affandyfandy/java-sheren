Value Types and Reference Types

To put it simply, in value type, a copy of the original value is made and we manipulate the copy, not the original value. Meanwhile, when manipulating reference types, the original value is changed along with every change. Reference types is usually done for objects (non-primitives).

VALUE TYPE
Value type exists locally in the place it's declared & holds its value inside its memory space
When it's passed somewhere else (assigned to a second variable, passed to a method, etc), its value is copied and two (or more) copies of the value now exist. Changing the value of first integer doesn't affect the original value.
Example:
int a = 5;
int b = a;  // b now has the value 5
a = 15;     // changing a doesn't affect b
System.out.println(b); // output: 5
System.out.println(a); // output: 15

Passed to a method example:
int num = 10;
System.out.println("Before calling method: " + num); // output: 10

modifyValue(num);

System.out.println("After calling method: " + num + "\n"); // output: 10

public static void modifyValue(int x) {
    x = 100;
    System.out.println("Inside method: " + x); // output: 100
}


REFERENCE TYPE
Reference type isn't stored locally, but in memory allocted elsewhere (usually on the global memory heap for the process). When it's passed somewhere else (assigned to a second variable, passed to a method, etc), the value that it's copied is the address of the object being passed/assigned, so two places refer to the same object in memory. Changing the value of the object via the new
reference will change the original object.
Example:
class MyClass {
    int number;
}

MyClass obj1 = new MyClass();
obj1.number = 10;
MyClass obj2 = obj1;  // obj2 references the same object as obj1
obj1.number = 20;
System.out.println(obj2.number);  // output: 20
