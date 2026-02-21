package UserSystem;

import java.util.Random;

public class User {
    private Integer userID;
    private String userFirstName;
    private String userLastName;
    private String userEmail;

    private Random rand = new Random();            // Temporary

    public User() {
        userID = rand.nextInt(100000000);   // Temporary
        userFirstName = "Barry";                  // Temporary
        userLastName = "Rajesh";                  // Temporary
        userEmail = "BRajesh03@gmail.com";        // Temporary
    }

    public Integer getUserID() {

        return userID;
    }
}
