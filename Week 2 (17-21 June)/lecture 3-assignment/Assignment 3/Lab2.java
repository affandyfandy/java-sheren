/* Create array menu with 5  element String
User enter the number and print menu follow user enter
Create Lab2Exception and main function will throws this
Handle unchecked exception if user try to read data element>5 and It do not crash application
 */

import java.util.Scanner;

class Lab2Exception extends RuntimeException {
    public Lab2Exception(String message) {
        super(message);
    }
}

public class Lab2 {
    public static void main(String[] args) {
        // Create array menu
        String[] menu = {
            "1. Fried rice",
            "2. Pizza",
            "3. Hotdog",
            "4. Hamburger",
            "5. Salad"
        };

        // Implement try-with-resources
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter a menu number (1-5): ");
            int userInput = scanner.nextInt();

            // Validate and handle user input
            if (userInput < 1 || userInput > 5) {
                throw new Lab2Exception("Invalid menu number: " + userInput);
            }

            System.out.println(menu[userInput - 1]);
        } catch (Lab2Exception e) {
            // Handle custom exception
            System.err.println(e.getMessage());
        } catch (Exception e) {
            // Handle other exceptions
            System.err.println("An unexpected error occured: " + e.getMessage());
        }
    }
}
