public class Teacher {
    // Attributes/state
    private String name;
    private int age;
    private Subject subject;

    // Constructor
    public Teacher(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Getter and setter
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // Assign subject
    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    // Method
    public String teach() {
        if (subject != null) {
            return "Teacher " + name + " teaching " + subject.getName() + " for Class " + subject.getClassId();
        } else {
            return "Teacher " + name + " has no subject assigned.";
        }
    }
}
