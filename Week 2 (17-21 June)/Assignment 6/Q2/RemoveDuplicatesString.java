// Remove all duplicate elements from a list of string using streams

import java.util.*;
import java.util.stream.Collectors;

public class RemoveDuplicatesString {
    public static void main(String[] args) {
        List<String> color = Arrays.asList("Blue", "Red", "Pink", "Blue", "Yellow", "Red");

        // Get list without duplicates using stream
        List<String> distinctColor = color.stream()
                                            .distinct()
                                            .collect(Collectors.toList());

        System.out.println(distinctColor); // Output: [Blue, Red, Pink, Yellow]
    }
}
