import UserSystem.User;

public class LoyaltyManager{
    User currentUser = new User();

    public GivePoints(double total){
        int CurrentPoints = currentUser.getUserLoyaltyPoints();
        int PointsEarned = (int) total;
        int newPoints = CurrentPoints + PointsEarned;

        // Set user's loyalty points to CurrentPoints + PointsEarned

        System.System.out.println("\nLoyalty points given: " + newPoints + "\n");
        ViewPoints();
    }

    void ViewPoints(){
        System.System.out.println("\nCurrent loyalty points: " + currentUser.getUserLoyaltyPoints() + "\n");
    };
}