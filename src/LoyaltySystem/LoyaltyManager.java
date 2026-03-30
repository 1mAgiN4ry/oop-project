import UserSystem.User;

public class LoyaltyManager{

    public GivePoints(double total, int User){
        int CurrentPoints = currentUser.getUserLoyaltyPoints();
        // £1 = 1 point, data loss intentional.
        int PointsEarned = (int) total;
        int newPoints = CurrentPoints + PointsEarned;

        // Set user's loyalty points to CurrentPoints + PointsEarned

        System.out.println("\nLoyalty points given: " + newPoints + "\n");
        ViewPoints();
    }

    void ViewPoints(){
        System.out.println("\nCurrent loyalty points: " + currentUser.getUserLoyaltyPoints() + "\n");
    };
}