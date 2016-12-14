package arend.arendvandormalen_pset6;

import java.util.ArrayList;

/**
 * Created by Arend on 2016-12-14.
 * Custom data class for a user.
 */

//@IgnoreExtraProperties
public class User {

    public String username;
    public String email;
    public ArrayList<ArtObject> favorites;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    // Constructor
    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    // Get methods to return values
    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public ArrayList<ArtObject> getFavorites() {
        return favorites;
    }

    // Set methods to update values
    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFavorites(ArrayList<ArtObject> favorites) {
        this.favorites = favorites;
    }
}
