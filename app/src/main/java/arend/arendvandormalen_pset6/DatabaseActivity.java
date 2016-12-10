package arend.arendvandormalen_pset6;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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


    public void parseResults(ArrayList<ArtObject> searchResultList){
        this.searchResultList = searchResultList;

        SearchListAdapter searchListAdapter = new SearchListAdapter(this, searchResultList);
        ListView resultsList = (ListView)findViewById(R.id.results_list_database);
        resultsList.setAdapter(searchListAdapter);

    }


}
