import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

public class DeserializeEmployee {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        List<Employee> employees = null;

        try (FileInputStream fileIn = new FileInputStream("employees.ser");
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            employees = (List<Employee>) in.readObject();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Employee class not found");
            c.printStackTrace();
        }

        System.out.println("Deserialized Employees...");
        if (employees != null) {
            for (Employee emp : employees) {
                System.out.println(emp);
            }
        }
    }
}

/* Output:
 * Deserialized Employees...
 * Employee{id=1, name='Reine Sara', department='Engineering'}
 * Employee{id=2, name='Raymond Dave', department='IT'}
 * Employee{id=3, name='Luis Fernandez', department='Sales'}
 */
