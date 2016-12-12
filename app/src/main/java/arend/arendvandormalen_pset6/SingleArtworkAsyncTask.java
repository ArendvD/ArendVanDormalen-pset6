package arend.arendvandormalen_pset6;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * Created by Arend on 2016-12-10.
 * AsyncTask for retrieving extended data of a single artwork.
 */

public class SingleArtworkAsyncTask extends AsyncTask<String, Integer, String> {

    Context context;
    DatabaseActivity activity;
    ArrayList<ArtObject> searchResults;
    String clickedArtworkId;
    ArtObject clickedArtwork;

    public SingleArtworkAsyncTask(DatabaseActivity activity, ArrayList<ArtObject> searchResults){
        this.activity = activity;
        this.context = this.activity.getApplicationContext();
        this.searchResults = searchResults;
    }

    // onPreExecute()
    @Override
    protected void onPreExecute(){
        Toast.makeText(context, "Getting data from the server", Toast.LENGTH_SHORT).show();
    }

    // doInBackground()
    @Override
    protected String doInBackground(String... params) {
        clickedArtworkId = params[0];
        Log.d("clickedArtworkTitle", clickedArtworkId);
        return HttpRequestHelper.downloadFromServer("d", params);
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
                    Log.d("checking item", currentId);
                    if(currentId.equals(clickedArtworkId)){
                        Log.d("matched id", currentId);
                        clickedArtwork = currentObject;
                        break;
                    }
                }

                // Retrieve data from query
                String description = artObject.getString("description");
                String longTitle = artObject.getString("longTitle");

                ArrayList<String> typesList = new ArrayList<>();
                if (artObject.has("types") && !artObject.isNull("types")) {
                    JSONArray typesArray = artObject.getJSONArray("types");

                    for (int i = 0; i < typesArray.length(); i++) {
                        String type = typesArray.getString(i);
                        typesList.add(type);
                    }
                }

                JSONObject dateObject = artObject.getJSONObject("dating");
                String year = dateObject.getString("year");
                String century = dateObject.getString("period");

                // Add data to object
                clickedArtwork.setDescription(description);
                clickedArtwork.setTitleLong(longTitle);
                clickedArtwork.setTypes(typesList);
                clickedArtwork.setCreationDate(year);
                clickedArtwork.setCentury(century);

                // Call method to show new activity
                activity.toSingleArtwork(clickedArtwork);
            }

        } catch (JSONException e){
            e.printStackTrace();
        }

    }


}
