package arend.arendvandormalen_pset6;

import android.util.Log;

import java.io.Serializable;

/**
 * Created by Arend on 2016-12-09.
 * Custom data class for each Art Object.
 */

public class ArtObject implements Serializable{

    public String id;
    public String title;
    public String artist;
    public String imageLink;

    public String[] types;
    public String descriptionShort;
    public String descriptionLong;
    public String creationDate; // Either single year or time span



    public ArtObject(String id, String title, String imageLink, String artist){
        this.id = id;
        this.title = title;
        this.imageLink = imageLink;
        this.artist = artist;
    }

    // Set-methods
    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public void setDescriptionLong(String descriptionLong) {
        this.descriptionLong = descriptionLong;
    }

    public void setDescriptionShort(String descriptionShort) {
        this.descriptionShort = descriptionShort;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public void setTypes(String[] types) {
        this.types = types;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // Get-methods
    public String getArtist() {
        return artist;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public String getDescriptionLong() {
        return descriptionLong;
    }

    public String getDescriptionShort() {
        return descriptionShort;
    }

    public String getImageLink() {
        return imageLink;
    }

    public String[] getTypes() {
        return types;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
