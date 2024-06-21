/* Count the number of strings in a list that start with a specific letter using streams.
a) Input: ["Red", "Green", "Blue", "Pink", "Brown“] and String = “G”
b) Output: 1
*/

import java.util.*;

public class CountString {
    public static void main(String[] args) {
        List<String> colors = Arrays.asList("Red", "Green", "Blue", "Pink", "Brown");
        String letter = "G";

        long count = colors.stream()
                        .filter(color -> color.startsWith(letter))
                        .count();

        System.out.println(count); // Output: 1
    }
}
