/* Object has states and behaviors. States -> color, name, breed. Behaviors -> wagging the tail
barking, eating. Object is an instance of a class */

public class Dog {
    // Specified attributes
    private String color;
    private String name;
    private String breed;

    // Constructor -> initializes dog object with specified attributes
    public Dog(String color, String name, String breed) {
        this.color = color;
        this.name = name;
        this.breed = breed;
    }
    
    // Getter and setter -> for encapsulation, these methods help to access private data
    // Getter returns value and setter sets or updates the value
    public String getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public String getBreed() {
        return breed;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    // Methods for behaviors
    public String wagTail() {
        return name + " is wagging its tail";
    }

    public String bark() {
        return name + " is barking";
    }

    public String eat() {
        return name + " is eating";
    }

    // Main method
    public static void main(String[] args) {
        Dog dog = new Dog("White", "Heli", "Poodle");
        System.out.println(dog.wagTail()); // Output: Heli is wagging its tail
        System.out.println(dog.bark()); // Heli is barking
        System.out.println(dog.eat()); // Heli is eating
    }
}