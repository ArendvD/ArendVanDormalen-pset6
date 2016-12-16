package arend.arendvandormalen_pset6;

import java.util.ArrayList;

/**
 * Created by Arend on 2016-12-14.
 * Custom data class for a user. Contains id, email and list of favorites
 */

//@IgnoreExtraProperties
public class User {

    public String userId;
    public String email;
    public ArrayList<ArtObject> favorites;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    // Constructor
    public User(String userId, String email) {
        this.userId = userId;
        this.email = email;
    }

    // Get methods to return values
    public String getEmail() {
        return email;
    }

    public String getUserId() {
        return userId;
    }

    public ArrayList<ArtObject> getFavorites() {
        return favorites;
    }

    // Set methods to update values
    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserId(String username) {
        this.userId = username;
    }

    public void setFavorites(ArrayList<ArtObject> favorites) {
        this.favorites = favorites;
    }
}
