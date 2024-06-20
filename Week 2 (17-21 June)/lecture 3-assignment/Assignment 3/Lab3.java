/* Create a method that takes a string as input and throws an exception if the string does not contain vowels
Try to use user defined exception
 */

// Custom exception
class NoVowelsException extends Exception {
    public NoVowelsException(String message) {
        super(message);
    }
}

public class Lab3 {
    // Method to check if the string contains vowels
    public static void checkVowels(String input) throws NoVowelsException {
        boolean foundVowel = false;
        String vowels = "aiueoAIUEO";

        for(char c : input.toCharArray()) {
            if (vowels.indexOf(c) != -1) {
                foundVowel = true;
                break;
            }
        }

        if(!foundVowel) {
            throw new NoVowelsException("String does not contain any vowels");
        }
    }
    public static void main(String[] args) {
        try {
            String input = "Hello";
            System.out.println("String: " + input); // String: Hello
            checkVowels(input); 
            System.out.println("String contains vowels"); // Output: String contains vowels

            checkVowels("Lsd"); // Should throw an exception
            // Output: No vowels found: String does not contain any vowels
        } catch (NoVowelsException e) {
            System.err.println("No vowels found: " + e.getMessage());
        }
    }
}
