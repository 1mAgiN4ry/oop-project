package LoyaltySystem;

import AccountSystem.User;

public class LoyaltyManager{

    public void GivePoints(double total, User user){
        int CurrentPoints = user.getLoyaltyPoints();
        // £1 = 1 point, data loss intentional.
        int PointsEarned = (int) total;
        int newPoints = CurrentPoints + PointsEarned;

        // Set user's loyalty points to CurrentPoints + PointsEarned

        System.out.println("\nLoyalty points given: " + newPoints + "\n");
        ViewPoints(user);
    }

    public void ViewPoints(User user){
        System.out.println("\nCurrent loyalty points: " + user.getLoyaltyPoints() + "\n");
    }
}
