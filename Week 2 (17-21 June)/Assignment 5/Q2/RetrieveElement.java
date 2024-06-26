package Q2;
// Retrieve an element (at a specified index) from a given array list

import java.util.ArrayList;
import java.util.List;

public class RetrieveElement {
    public static void main(String[] args) {
        List<String> color = new ArrayList<>();
        color.add("Black");
        color.add("White");
        color.add("Red");
        color.add("Purple");
        color.add("Yellow");

        System.out.println(color);
        
        String element = color.get(0);

        System.out.println(element);

        element = color.get(3);

        System.out.println(element);
    }
}
