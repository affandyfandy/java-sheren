/* Design Class generic for paging data (any Object). Demo the use.
Include: Object, size, pageNumber,... */

import java.util.*;
import java.util.stream.Collectors;

class EmployeeData {
    private int employeeID;
    private String name;
    private String department;

    public EmployeeData(int employeeID, String name, String department) {
        this.employeeID = employeeID;
        this.name = name;
        this.department = department;
    }

    // Getter and setter
    public int getEmployeeID() {
        return employeeID;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeID=" + employeeID +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}

public class PagingDemo {
    public static void main(String[] args) {
        List<EmployeeData> employees = Arrays.asList(
            new EmployeeData(1, "Lisa", "Finance"),
            new EmployeeData(2, "Luna", "IT"),
            new EmployeeData(3, "Ray", "HR"),
            new EmployeeData(4, "Bila", "Finance"),
            new EmployeeData(5, "Nina", "IT"),
            new EmployeeData(6, "Gary", "HR"),
            new EmployeeData(7, "Huang", "Finance"),
            new EmployeeData(8, "Sera", "IT")
        );

        int size = 3; // Number of items per page
        int totalPages = (employees.size() + size - 1) / size; // Calculate total pages

        for (int pageNumber = 1; pageNumber <= totalPages; pageNumber++) {
            PagedData<EmployeeData> pagedEmployees = getPagedData(employees, pageNumber, size);
            System.out.println("Page " + pageNumber + ":");
            pagedEmployees.getItems().forEach(System.out::println);
            System.out.println();
        }
    }

    // Method to get paged data
    public static <T> PagedData<T> getPagedData(List<T> fullList, int pageNumber, int size) {
        int totalItems = fullList.size();
        // Get items for the current page
        List<T> pagedItems = fullList.stream()
                                     .skip((pageNumber - 1) * size)
                                     .limit(size)
                                     .collect(Collectors.toList());
        // Return a PagedData object
        return new PagedData<>(pagedItems, size, pageNumber, totalItems);
    }
}

/* Output:
 * Page 1:
 * Employee{employeeID=1, name='Lisa', department='Finance'}
 * Employee{employeeID=2, name='Luna', department='IT'}
 * Employee{employeeID=3, name='Ray', department='HR'}
 * 
 * Page 2:
 * Employee{employeeID=4, name='Bila', department='Finance'}
 * Employee{employeeID=5, name='Nina', department='IT'}
 * Employee{employeeID=6, name='Gary', department='HR'}
 * 
 * Page 3:
 * Employee{employeeID=7, name='Huang', department='Finance'}
 * Employee{employeeID=8, name='Sera', department='IT'}
 */
