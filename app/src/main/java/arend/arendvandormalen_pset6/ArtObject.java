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
    public ArrayList<String> types;
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
    public void setArtist(String artist) {
        this.artist = artist;
    }

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

    public void setTypes(ArrayList<String> types) {
        this.types = types;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCentury(String century) {
        this.century = century;
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

    public ArrayList<String> getTypes() {
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
