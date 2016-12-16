package arend.arendvandormalen_pset6;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.google.firebase.database.DatabaseReference;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * Created by Arend on 2016-12-10.
 * AsyncTask for retrieving extended data of a single artwork.
 * AsyncTask is called when an item is clicked. This extended data is not available in AsyncTask
 * for multiple artworks.
 */

public class SingleArtworkAsyncTask extends AsyncTask<String, Integer, String> {

    Context context;
    ArrayList<ArtObject> searchResults;
    String clickedArtworkId;
    ArtObject clickedArtwork;

    public SingleArtworkAsyncTask(Context context, ArrayList<ArtObject> searchResults){
        this.searchResults = searchResults;
        this.context = context;
    }

    // onPreExecute()
    @Override
    protected void onPreExecute(){
        super.onPreExecute();
    }

    // doInBackground()
    @Override
    protected String doInBackground(String... params) {
        clickedArtworkId = params[0];
        Log.d("clickedArtworkTitle", clickedArtworkId);
        return HttpRequestHelper.downloadFromServer("detail", params);
    }

    // onProgressUpdate()
    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }


    // onPostExecute()
    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        try {
            JSONObject resultsObj = new JSONObject(result);

            // Check if object is present
            if(resultsObj.has("artObject") && !resultsObj.isNull("artObject")){
                JSONObject artObject = resultsObj.getJSONObject("artObject");

                // Find data object from list
                for (int i = 0; i < searchResults.size() ; i++) {
                    ArtObject currentObject = searchResults.get(i);
                    String currentId = currentObject.getId();

                    if(currentId.equals(clickedArtworkId)){
                        clickedArtwork = currentObject;
                        break;
                    }
                }

                // Retrieve data from query
                String description = artObject.getString("description");
                String longTitle = artObject.getString("longTitle");

                ArrayList<String> typesList = new ArrayList<>();
                if (artObject.has("objectTypes") && !artObject.isNull("objectTypes")) {
                    JSONArray typesArray = artObject.getJSONArray("objectTypes");

                    for (int i = 0; i < typesArray.length(); i++) {
                        String type = typesArray.getString(i);
                        typesList.add(type);
                    }
                }

                // Open dating info, which is a child object from artwork in API
                JSONObject dateObject = artObject.getJSONObject("dating");
                String year = dateObject.getString("year");
                String century = dateObject.getString("period");

                // Add data to object
                clickedArtwork.setDescription(description);
                clickedArtwork.setTitleLong(longTitle);
                clickedArtwork.setTypes(typesList);
                clickedArtwork.setCreationDate(year);
                clickedArtwork.setCentury(century);

                // Send intent to open single item
                Intent toSingleItemActivity = new Intent(context, SingleItemActivity.class);
                toSingleItemActivity.putExtra("artObject", clickedArtwork);
                context.startActivity(toSingleItemActivity);

                // Close the old activity
                ((Activity)context).finish();

            }

        } catch (JSONException e){
            e.printStackTrace();
        }

    }


}
