package LoyaltySystem;

import AccountSystem.User;

public class LoyaltyManager{

    private static User user;

    public LoyaltyManager(User user) {
        this.user = user;
    }

    public static void GivePoints(double total){
        int CurrentPoints = user.getLoyaltyPoints();
        // £1 = 1 point, data loss intentional.
        int PointsEarned = (int) total;
        int newPoints = CurrentPoints + PointsEarned;

        // Set user's loyalty points to CurrentPoints + PointsEarned

        System.out.println("\nLoyalty points given: " + newPoints + "\n");
        ViewPoints();
    }

    public static void ViewPoints(){
        System.out.println("\nCurrent loyalty points: " + user.getLoyaltyPoints() + "\n");
    }
}
