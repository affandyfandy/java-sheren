/* Illustrate Serialization and Deserialization in write list of object (employee) to
file and read file to convert to object and using serialVersionUID */

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int id;
    private String name;
    private String department;

    public Employee(int id, String name, String department) {
        this.id = id;
        this.name = name;
        this.department = department;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public String toString() {
        return "Employee{id=" + id + ", name='" + name + "', department='" + department + "'}";
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}

public class SerializeEmployee {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Reine Sara", "Engineering"));
        employees.add(new Employee(2, "Raymond Dave", "IT"));
        employees.add(new Employee(3, "Luis Fernandez", "Sales"));

        try (FileOutputStream fileOut = new FileOutputStream("employees.ser");
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(employees);
            System.out.println("Serialized data is saved in employees.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}

// Output: Serialized data is saved in employees.ser