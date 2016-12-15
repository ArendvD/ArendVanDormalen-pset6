package arend.arendvandormalen_pset6;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserActivity extends AppCompatActivity {


    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        // Display email adress of user, if possible to retrieve.
        FirebaseUser fbUser = FirebaseAuth.getInstance().getCurrentUser();
        if (fbUser.getEmail() != null) {
            String userEmail = "Signed in as: " + fbUser.getEmail();
            TextView emailView = (TextView)findViewById(R.id.email_user);
            emailView.setText(userEmail);
        }
        /*
        String fbUserId = fbUser.getUid();


        // TODO: Get list of ArtObjects from Firebase server
        User user = new User("test", "test");
        final ArrayList<ArtObject> favoritesArray = user.getFavorites();

        // Set adapter to fill list with favorited items, using same layout as in search results
        SearchListAdapter searchListAdapter = new SearchListAdapter(this, favoritesArray);
        ListView favoritesList = (ListView)findViewById(R.id.favorites_list_user);
        favoritesList.setAdapter(searchListAdapter);

        favoritesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Find ID of selected artwork in list
                ArtObject selectedArtwork = (ArtObject) parent.getItemAtPosition(position);
                String artId = selectedArtwork.getId();

                Log.d("clicked", artId);

                SingleArtworkAsyncTask singleArtworkAsyncTask = new
                        SingleArtworkAsyncTask(UserActivity.this, favoritesArray);
                singleArtworkAsyncTask.execute(artId);

            }
        });
        */

    }
}
