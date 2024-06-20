public class EqualsExample {
    private String name;
    private int age;

    public EqualsExample(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        EqualsExample person = (EqualsExample) obj;
        return age == person.age && name.equals(person.name);
    }

    public static void main(String[] args) {
        EqualsExample p1 = new EqualsExample("Liam", 19);
        EqualsExample p2 = new EqualsExample("Dwayne", 23);
        EqualsExample p3 = new EqualsExample("Liam", 19);

        System.out.println(p1.equals(p2)); // Output: false
        System.out.println(p1.equals(p3)); // Output: true
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
