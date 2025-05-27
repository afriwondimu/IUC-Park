package iucpark.service; // Defines package
import java.util.Scanner; // Reads user input

public class AuthService { // Handles authentication
    public boolean authenticate(Scanner scanner) { // Authenticates user
        System.out.print("Username: "); // Prompts username
        String username = scanner.nextLine(); // Reads username
        System.out.print("Password: "); // Prompts password
        String password = scanner.nextLine(); // Reads password
        return username.equals("iuc") && password.equals("123"); // Checks credentials
    }
}