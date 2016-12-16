package arend.arendvandormalen_pset6;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Arend on 2016-12-10.
 * Thread to get data from server without overloading Activities.
 * Returns a list of artObjects based on a search query.
 */

public class ArtAsyncTask extends AsyncTask<String, Integer, String> {

    Context context;
    DatabaseActivity activity;
    ArrayList<ArtObject> searchResultsList;

    // Constructor
    public ArtAsyncTask(DatabaseActivity activity){
        this.activity = activity;
        this.context = this.activity.getApplicationContext();
    }

    // Notifies user connection will be made
    @Override
    protected void onPreExecute(){
        Toast.makeText(context, "Getting data from the server", Toast.LENGTH_SHORT).show();
    }

    // Sends url with an extra parameter to Http helper class
    @Override
    protected String doInBackground(String... params) {
        return HttpRequestHelper.downloadFromServer("search", params);
    }


    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }


    // Create list of ArtObjects based on found API.
    // String result is the API result found on the generated URL.
    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        // Try-catch for JSON errors.
        try{
            JSONObject resultsObj = new JSONObject(result);

            // Select artworks from metadata
            JSONArray artObjects = resultsObj.getJSONArray("artObjects");

            // Check if results are found based on query
            if (artObjects.length() == 0){
                Toast.makeText(context, "No art works were found", Toast.LENGTH_SHORT).show();

            } else {

                // Tell user how many artworks are found based on query
                String count = resultsObj.getString("count");
                Toast.makeText(context, count+" art works found.", Toast.LENGTH_SHORT).show();

                searchResultsList = new ArrayList<>();

                // Fill array with search results, fill objects with data available at this point
                for (int i = 0; i < artObjects.length(); i++) {
                    JSONObject artObject = artObjects.getJSONObject(i);
                    String id = artObject.getString("objectNumber");
                    String title = artObject.getString("title");
                    String artist = artObject.getString("principalOrFirstMaker");

                    // Create artObject and add values
                    ArtObject artData = new ArtObject();
                    artData.setId(id);
                    artData.setTitle(title);
                    artData.setArtist(artist);
                    searchResultsList.add(artData);

                    // Check if an image is present in API and set the link if so.
                    if(artObject.has("webImage") && !artObject.isNull("webImage")){
                        JSONObject imageObject = artObject.getJSONObject("webImage");
                        String imageLink = imageObject.getString("url");
                        artData.setImageLink(imageLink);
                    }
                }
                // send search results back to activity
                activity.parseResults(searchResultsList);
            }

        } catch (JSONException e){
            e.printStackTrace();
        }

    }

}
