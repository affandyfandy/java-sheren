## OOP

Object-oriented programming (OOP) is programming based on the concept of objects. The data structures have properties and behaviors. OOP allows programmers to organize code mirrors real-world objects and their relationships. It makes it easier to understand, modify, and maintain. OOP also provides benefits such as encapsulation, inheritance, polymorphism, and abstraction which help prevent bugs, increase code reusability, and make code more flexible and extensible. OOP is a powerful and widely used that supports programmers in writing better code.

OOP Principles

1. Encapsulation. The basic concept of encapsulation is hiding implementation details of an object from its clients. Encapsulation allows us to define controlled access to data stored inside objects of our class. Example:
    
    ```markdown
    public class Student {
        private String studentId;
        private String name;
        private double gpa;
    
        public Student(String studentId, String name, double gpa) {
            this.studentId = studentId;
            this.name = name;
            this.gpa = gpa;
        }
    
        public String getStudentId() {
            return studentId;
        }
    
        public String getName() {
            return name;
        }
    
        public void setName(String name) {
            this.name = name;
        }
    
        public double getGpa() {
            return gpa;
        }
    
        public void setGpa(double gpa) {
            if (gpa >= 0.0 && gpa <= 4.0) { 
                this.gpa = gpa;
            }
        }
    
        public void displayStudentInfo() {
            System.out.println("Student ID: " + studentId);
            System.out.println("Name: " + name);
            System.out.println("GPA: " + gpa);
        }
    }
    
    public class Main {
        public static void main(String[] args) {
            Student student = new Student("12", "Lee", 3.7);
    
            student.displayStudentInfo();
    
            student.setName("Kang");
            student.setGpa(4);
            
            System.out.println("\nUpdated Student Details:");
            student.displayStudentInfo();
        }
    }
    ```
    
2. Inheritance.
Inheritance allows one class to inherit properties from another class. The class that inherits from another class is called a child/subclass. Meanwhile, the class that is inherited from is called a parent/superclass.
Example:
    
    ```markdown
    class Student {  
        String campus = "President University";  
    }  
    class Computing extends Student {  
        String major = "Information System";  
        public static void main(String args[]){  
          Computing c = new Computing();  
          System.out.println("Student campus is: "+ c.campus);  
          System.out.println("Student major is: "+ c.major);  
       }
    } 
    ```
    
3. Polymorphism.
Polymorphism allows different classes to have the same name of the method but distinct implementations.
Overriding is a type of polymorphism where a child class has a different implementation of the method that is already defined in the parent class. A child class can customize the behavior of the inherited method.
Example:
    
    ```markdown
    class Animal {
        void sound() {
            System.out.println("Bark!");
        }
    }
    
    class Cat extends Animal {
        @Override
        void sound() {
            System.out.println("Meow!");
        }
    }
    
    public class Main {
        public static void main(String[] args) {
            Animal anAnimal = new Animal();  
            Animal aDog = new Cat();       
    
            anAnimal.sound(); // Outputs: Animal makes a sound
            aDog.sound();    // Outputs: Dog barks
        }
    }
    ```
    
    Overloading allows to define multiple methods with the same name in the same class. However, the methods must be different in the parameter list. When the method is called, the version of the method is selected based on the arguments.
    Example:
    
    ```markdown
    class Calculator {
        int add(int a, int b) {
            return a + b;
        }
    
        int add(int a, int b, int c) {
            return a + b + c;
        }
    
        int multiply(int a, int b) {
            return a * b;
        }
    }
    
    public class Main {
        public static void main(String[] args) {
            Calculator calc = new Calculator();
    
            System.out.println(calc.add(5, 10));                  
            System.out.println(calc.add(2, 3, 4));            
            System.out.println(calc.multiply(2, 3));        
        }
    }
    ```
    
4. Abstraction.
Abstraction in OOP is used to hide unnecessary information and display only necessary information to the users interacting. It is essential to represent real-world objects in a simplified manner for users to interact easily.
    
    Example:
    
    ```markdown
    interface Vehicle {
        void start();
    }
    
    class Car implements Vehicle {
        @Override
        public void start() {
            System.out.println("Car is big.");
        }
    }
    
    class Motorcycle implements Vehicle {
        @Override
        public void start() {
            System.out.println("Motorcycle is small.");
        }
    }
    
    public class Main {
        public static void main(String[] args) {
            Vehicle car = new Car();
            Vehicle motorcycle = new Motorcycle();
    
            car.start();          
            motorcycle.start(); 
        }
    }
    
    ```
