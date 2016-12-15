package arend.arendvandormalen_pset6;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Arend on 2016-12-09.
 * Custom data class for each Art Object.
 */

public class ArtObject implements Serializable{

    // Constructor
    public String id;
    public String title;
    public String artist;

    // On search query, not all objects have an image.
    public String imageLink;

    // On details query
    public String types;
    public String titleLong;
    public String description;
    public String creationDate; // Either single year or time span
    public String century;



    public ArtObject(String id, String title, String artist){
        this.id = id;
        this.title = title;
        this.artist = artist;
    }

    // Set-methods

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitleLong(String titleLong) {
        this.titleLong = titleLong;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCentury(String century) {
        this.century = century;
    }

    // Save types in a single string instead of an ArrayList
    public void setTypes(ArrayList<String> types) {

        String typesString = "";

        // Check if data on types is present, as not all artworks have this data
        if (types == null) {
            typesString = "Type Unknown";
        } else {
            // Append types-data
            StringBuilder builder = new StringBuilder();
            for (String type : types) {
                String typeLine = type + "\n";
                builder.append(typeLine);
            }
            typesString = builder.toString();
        }

        this.types = typesString;

    }

    // Get-methods
    public String getArtist() {
        return artist;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public String getDescription() {
        return description;
    }

    public String getTitleLong() {
        return titleLong;
    }

    public String getImageLink() {
        return imageLink;
    }

    public String getTypes() {
        return types;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String
    getCentury() {
        return century;
    }
}
