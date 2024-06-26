package Q8;

public class HashCodeExample {
    private String name;
    private int age;

    public HashCodeExample(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        HashCodeExample person = (HashCodeExample) obj;
        return age == person.age && name.equals(person.name);
    }

    @Override
    public int hashCode() {
        return 25 * name.hashCode() + age;
    }

    public static void main(String[] args) {
        HashCodeExample p1 = new HashCodeExample("Liam", 19);
        HashCodeExample p2 = new HashCodeExample("Dwayne", 23);
        HashCodeExample p3 = new HashCodeExample("Liam", 19);

        System.out.println(p1.equals(p2)); // Output: false
        System.out.println(p1.equals(p3)); // Output: true
        System.out.println(p1.hashCode() == p2.hashCode()); // Output: false
        System.out.println(p1.hashCode() == p3.hashCode()); // Output: true
    }

    // Getter and setter

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
