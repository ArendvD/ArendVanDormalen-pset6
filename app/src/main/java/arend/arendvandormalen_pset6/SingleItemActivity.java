package arend.arendvandormalen_pset6;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;

public class SingleItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_item);

        Intent intent = getIntent();
        ArtObject artObject = (ArtObject) intent.getSerializableExtra("artObject");

        // Retrieve data from object
        String title = artObject.getTitle();
        String longTitle = artObject.getTitleLong();
        String artist = artObject.getArtist();
        String year = artObject.getCreationDate();
        String century = artObject.getCentury() + "e eeuw";
        String description = artObject.getDescription();
        String imageLink = artObject.getImageLink();
        ArrayList<String> types = artObject.getTypes();
        // Append types-data
        StringBuilder builder = new StringBuilder();
        for(String type : types){
            builder.append(type + "\n");
        }

        // Retrieve fields in xml file
        TextView titleView = (TextView)findViewById(R.id.title_single);
        TextView longTitleView = (TextView)findViewById(R.id.title_long_single);
        TextView artistView = (TextView)findViewById(R.id.artist_single);
        TextView yearView = (TextView)findViewById(R.id.year_single);
        TextView centuryView = (TextView)findViewById(R.id.century_single);
        TextView descriptionView = (TextView)findViewById(R.id.description_single);

        // Fill in fields
        titleView.setText(title);
        longTitleView.setText(longTitle);
        artistView.setText(artist);
        yearView.setText(year);
        centuryView.setText(century);
        descriptionView.setText(description);

        // If image exists, retrieve it through AsyncTask
        if(imageLink != null){
            ImageView imageView = (ImageView)findViewById(R.id.image_single);
            ImageAsyncTask imageAsyncTask = new ImageAsyncTask(imageView);
            imageAsyncTask.execute(imageLink);
        }

    }

}

// New AsyncTask that downloads pictures from database.
class ImageAsyncTask extends AsyncTask<String, Void, Bitmap> {
    private ImageView imageView;

    public ImageAsyncTask(ImageView imageView) {
        this.imageView = imageView;
    }

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

    protected void onPostExecute(Bitmap poster) {
        if(poster != null)
            imageView.setImageBitmap(poster);
    }
}
