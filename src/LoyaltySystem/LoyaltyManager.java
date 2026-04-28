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
        user.setLoyaltyPoints(newPoints);

        System.out.println("Loyalty points given: " + PointsEarned);
        ViewPoints();
    }

    public static void ViewPoints(){
        System.out.println("Current loyalty points: " + user.getLoyaltyPoints());
    }
}
