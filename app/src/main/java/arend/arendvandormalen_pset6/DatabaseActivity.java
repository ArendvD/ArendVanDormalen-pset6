package arend.arendvandormalen_pset6;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Arend on 2016-12-06.
 * Activity that displays the database. It contains a search bar and a button that sends a
 * query to the Rijksmuseum server. This will result in a list of items being displayed.
 * Items can be clicked, on which a second query will be sent. This query will return more
 * detailed information on this item, which is not present in the first query.
 */

public class DatabaseActivity extends AppCompatActivity {

    public ArrayList<ArtObject> searchResultList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
    }

    // Reads the user input and sends it to an AsyncTask in order to avoid freezing UI.
    public void searchOnTitle(View view){

        EditText searchBox = (EditText)findViewById(R.id.search_box_database);
        String searchTerm = searchBox.getText().toString();

        ArtAsyncTask artAsyncTask = new ArtAsyncTask(DatabaseActivity.this);
        artAsyncTask.execute(searchTerm);
    }

    // Reads results from AsyncTask and sends it to adapter to fill list.
    public void parseResults(final ArrayList<ArtObject> searchResultList){
        this.searchResultList = searchResultList;

        SearchListAdapter searchListAdapter = new SearchListAdapter(this, searchResultList);
        ListView resultsList = (ListView)findViewById(R.id.results_list_database);
        resultsList.setAdapter(searchListAdapter);

        resultsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

               // Find ID of selected artwork in list
               ArtObject selectedArtwork = (ArtObject) parent.getItemAtPosition(position);
               String artId = selectedArtwork.getId();

               Log.d("clicked", artId);

               // AsyncTask will request additional information to server and add data to ArtObject
               SingleArtworkAsyncTask singleArtworkAsyncTask = new
                       SingleArtworkAsyncTask(DatabaseActivity.this, searchResultList);
               singleArtworkAsyncTask.execute(artId);

           }
        });

    }

}
