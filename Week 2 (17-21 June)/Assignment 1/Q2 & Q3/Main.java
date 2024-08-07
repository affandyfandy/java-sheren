/* Design Class with below describe:​
Teacher with state is name, age, subject and the behavior is teaching​
Subject with state is name, classId​
We can create teacher with name and age​
We can create teacher with subject(class)​
We can create Subject with name​
Print to console info: ​Teacher Tam teaching Mathematics for Class 1​

Design Class with below describe:​
Same lab2 Teacher and Subject​
Student with name, age and behavior is learning​
The teacher will teaching a Subject​
The student will learning some Subject(array)​ */

public class Main {
    // Main method
    public static void main(String[] args) {
        // TASK 2
        // Create a subject
        Subject math = new Subject("Mathematics", 1);
        Subject chemistry = new Subject("Chemistry", 2);
        Subject biology = new Subject("Biology", 3);

        // Create a teacher
        Teacher teacher = new Teacher("Tam", 25);

        // Assigning subject
        teacher.setSubject(math);

        System.out.println(teacher.teach()); // Output: Teacher Tam teaching Mathematics for Class 1

        // TASK 3
        // Create a student
        Student student = new Student("Sheren", 19);

        // Assign subject
        student.learn(math);
        student.learn(chemistry);
        student.learn(biology);

        System.out.println("Student " + student.getName() + " is learning " + student.getSubjects());
        // Student Sheren is learning Mathematics Chemistry Biology
    }
}