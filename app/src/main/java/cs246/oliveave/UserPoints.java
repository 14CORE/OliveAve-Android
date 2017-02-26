package cs246.oliveave;

/**
 * Created by Anderson on 2/23/17.
 */

public class UserPoints {
    public String points;

    public UserPoints(String points) {
        this.points = points;
    }

    public UserPoints(){
        // Default constructor required for calls to DataSnapshot.getValue(UserPoints.class)
    }

}

