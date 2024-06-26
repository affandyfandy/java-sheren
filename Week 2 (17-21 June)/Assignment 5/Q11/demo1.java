package Q11;

// Provided codes:
// import java.util.*;

// public class demo1 {
//     List<String> data = new ArrayList();
//     data.add("Joe");
//     data.add("Helen");
//     data.add("Test");
//     data.add("Rien");
//     data.add("Ruby");
//     for (String d : data) {
//         if (d.equals("Test")) {
//             data.remove(d);
//         }
//     }
// }

// The solution:
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class demo1 {
    public static void main(String[] args) {
        List<String> data = new ArrayList<>();
        data.add("Joe");
        data.add("Helen");
        data.add("Test");
        data.add("Rien");
        data.add("Ruby");

        // Use an explicit iterator to safely remove elements
        Iterator<String> iterator = data.iterator();
        while (iterator.hasNext()) {
            String d = iterator.next();
            if (d.equals("Test")) {
                iterator.remove(); // Call remove method to safe removal of element
            }
        }

        // Print the list to verify the removal
        for (String d : data) {
            System.out.println(d);
        }
    }
}

/* Output:
 * Joe
 * Helen
 * Rien
 * Ruby
 */