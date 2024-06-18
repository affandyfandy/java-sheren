
import java.util.ArrayList;

public class Student {
    // Attribute/State
    private String name;
    private int age;
    private ArrayList <Subject> subjects;

    // Constructor
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
        this.subjects = new ArrayList<>();
    }

    // Getter and setter
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getSubjects() {
        StringBuilder subjectList = new StringBuilder();
        for (Subject subject : subjects) {
            subjectList.append(subject.getName()).append(" ");
        }
        return subjectList.toString();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSubjects(ArrayList<Subject> subjects) {
        this.subjects = subjects;
    }

    // Method
    public void learn(Subject subject) {
        subjects.add(subject);
    }
}
