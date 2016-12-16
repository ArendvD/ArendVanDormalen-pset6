package arend.arendvandormalen_pset6;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.InputStream;

/**
 * Created by Arend on 2016-12-07.
 * Activity for displaying a single artwork. Object is received from intent.
 * Data is recovered from this object and send to the corresponding fields in the layout file.
 * A heart-shaped button is used for saving an item to the users favorites. Clicking this button
 * will either add or delete it from the database.
 * This class contains a small local AsyncTask for loading the image. This will keep the UI from
 * freezing.
 *
 * Pressing android's back button will send the user back to the home page.
 * Preferably, it would have send them back to the search results.
 */

public class SingleItemActivity extends AppCompatActivity {

    String userId;
    DatabaseReference ref;
    ArtObject artObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_item);

        // Retrieve database, used for adding/removing item from favorites
        ref = FirebaseDatabase.getInstance().getReference();

        // Retrieve the object
        Intent intent = getIntent();
        artObject = (ArtObject) intent.getSerializableExtra("artObject");

        // Retrieve data from object
        String title = artObject.getTitle();
        String longTitle = artObject.getTitleLong();
        String artist = artObject.getArtist();
        String year = artObject.getCreationDate();
        String century = artObject.getCentury() + "e eeuw";
        String description = artObject.getDescription();
        String imageLink = artObject.getImageLink();
        String types = artObject.getTypes();

        // Retrieve fields in xml file
        TextView titleView = (TextView)findViewById(R.id.title_single);
        TextView longTitleView = (TextView)findViewById(R.id.title_long_single);
        TextView artistView = (TextView)findViewById(R.id.artist_single);
        TextView yearView = (TextView)findViewById(R.id.year_single);
        TextView centuryView = (TextView)findViewById(R.id.century_single);
        TextView descriptionView = (TextView)findViewById(R.id.description_single);
        TextView typesView = (TextView)findViewById(R.id.types_single);

        // Fill in fields in layout
        titleView.setText(title);
        longTitleView.setText(longTitle);
        longTitleView.setVisibility(View.GONE); // Test layout no title
        artistView.setText(artist);
        yearView.setText(year);
        centuryView.setText(century);
        descriptionView.setText(description);
        typesView.setText(types);

        // If an image exists, retrieve it through AsyncTask
        if(imageLink != null){
            ImageView imageView = (ImageView)findViewById(R.id.image_single);
            ImageAsyncTask imageAsyncTask = new ImageAsyncTask(imageView);
            imageAsyncTask.execute(imageLink);
        }

    }

    public void addToFavorites(View view) {

        // Switch image to filled heart
        ViewSwitcher switcher = (ViewSwitcher)findViewById(R.id.heart_image_single);
        switcher.showNext();

        // Find if user is logged in
        try {
            // Retrieve user
            userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

            // Add artwork below user in database
            // KNOWN BUG: artwork is added below uid of user instead of below user.
            DatabaseReference newRef = ref.child("user").child(userId).child("favorites").push();
            newRef.setValue(artObject);

            // Notify user that item is saved
            Toast.makeText(SingleItemActivity.this, "Saved to favorites",
                    Toast.LENGTH_SHORT).show();

        } catch (NullPointerException e){
            // If not logged in, show toast
            e.printStackTrace();
            Toast.makeText(SingleItemActivity.this, "Log-in needed",
                    Toast.LENGTH_SHORT).show();
        }

    }

    public void removeFromFavorites(View view) {

        // Switch image to empty heart
        ViewSwitcher switcher = (ViewSwitcher)findViewById(R.id.heart_image_single);
        switcher.showNext();

        // KNOWN BUG: Deleting not possible, as no way is found to recover item from database.

        // Notify user that item is removed
        Toast.makeText(SingleItemActivity.this, "Removed from favorites",
                Toast.LENGTH_SHORT).show();

    }
}

// New AsyncTask that downloads pictures from database.
class ImageAsyncTask extends AsyncTask<String, Void, Bitmap> {
    private ImageView imageView;

    public ImageAsyncTask(ImageView imageView) {
        this.imageView = imageView;
    }

    // Load bitmap image
    protected Bitmap doInBackground(String... urls) {
        String url = urls[0];
        Bitmap image = null;
        try {
            InputStream in = new java.net.URL(url).openStream();
            image = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }

    // Set bitmap on correct field
    protected void onPostExecute(Bitmap poster) {
        if(poster != null)
            imageView.setImageBitmap(poster);
    }
}
