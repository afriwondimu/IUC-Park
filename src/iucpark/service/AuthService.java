package iucpark.service; // Defines package
import java.util.Scanner; // Reads user input

public class AuthService { // Handles authentication
    public boolean authenticate(Scanner scanner) { // Authenticates user
        System.out.print("Username: "); 
        String username = scanner.nextLine(); 
        System.out.print("Password: "); 
        String password = scanner.nextLine(); 
        return username.equals("iuc") && password.equals("123"); 
    }
}
