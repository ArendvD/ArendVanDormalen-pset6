package arend.arendvandormalen_pset6;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class DatabaseActivity extends AppCompatActivity {

    public String key = "VPovhf3D";
    public ArrayList<ArtObject> searchResultList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
    }

    public void searchOnTitle(View view){

        EditText searchBox = (EditText)findViewById(R.id.search_box_database);
        String searchTerm = searchBox.getText().toString();

        ArtAsyncTask artAsyncTask = new ArtAsyncTask(DatabaseActivity.this);
        artAsyncTask.execute(searchTerm);
    }


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

               SingleArtworkAsyncTask singleArtworkAsyncTask = new
                       SingleArtworkAsyncTask(DatabaseActivity.this, searchResultList);
               singleArtworkAsyncTask.execute(artId);

           }
        });

    }

    public void toSingleArtwork(ArtObject artObject){

        Intent toSingleItemActivity = new Intent(this, SingleItemActivity.class);
        toSingleItemActivity.putExtra("artObject", artObject);
        startActivity(toSingleItemActivity);

    }


}
