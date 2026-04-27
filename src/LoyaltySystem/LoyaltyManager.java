package LoyaltySystem;

import UserSystem.User;

public class LoyaltyManager{

    private final User user;

    public LoyaltyManager(User user) {
        this.user = user;
    }

    public GivePoints(double total){
        int CurrentPoints = user.getLoyaltyPoints();
        int PointsEarned = (int) total;
        // £1 = 1 point, data loss intentional.

        int newPoints = CurrentPoints + PointsEarned;

        user.setLoyaltyPoints(newPoints);

        System.out.println("Loyalty points given: " + newPoints);
        ViewPoints();
    }

    void ViewPoints(){
        System.out.println("\nCurrent loyalty points: " + currentUser.getLoyaltyPoints());
    }
}