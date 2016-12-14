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
 * Thread to get data from server without overloading Activities.
 * Returns a list of items based on a search.
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

    // onPreExecute()
    @Override
    protected void onPreExecute(){
        Toast.makeText(context, "Getting data from the server", Toast.LENGTH_SHORT).show();
    }

    // doInBackground()
    @Override
    protected String doInBackground(String... params) {
        return HttpRequestHelper.downloadFromServer("s", params);
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

        try{
            JSONObject resultsObj = new JSONObject(result);

            JSONArray artObjects = resultsObj.getJSONArray("artObjects");
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


                    ArtObject movieData = new ArtObject(id, title, artist);
                    searchResultsList.add(movieData);

                    if(artObject.has("webImage") && !artObject.isNull("webImage")){
                        JSONObject imageObject = artObject.getJSONObject("webImage");
                        String imageLink = imageObject.getString("url");
                        movieData.setImageLink(imageLink);
                    }
                }

                activity.parseResults(searchResultsList);
            }

        } catch (JSONException e){
            e.printStackTrace();
        }

    }

}
