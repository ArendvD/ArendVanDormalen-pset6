package arend.arendvandormalen_pset6;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Arend on 2016-12-10.
 * Activity where user information is displayed. The user is sent here on logging in or registering.
 * In this activity options for editing email address should be added.
 * Additionally, the list of favorites will be displayed here.
 */

public class UserActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);


        // Retrieve database
        mDatabase = FirebaseDatabase.getInstance().getReference();

        // Display email address of user, if possible to retrieve.
        FirebaseUser fbUser = FirebaseAuth.getInstance().getCurrentUser();
        String mailAddress = null;
        if (fbUser != null) {
            mailAddress = fbUser.getEmail();
        }
        String userEmail = "Signed in as: " + mailAddress;
        TextView emailView = (TextView)findViewById(R.id.email_user);
        emailView.setText(userEmail);

        // KNOWN BUG: Retrieving items from database not possible. Attempt is below

        /*
        String fbUserId = fbUser.getUid();

        User user = new User(fbUserId, mailAdress);

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

    public void toDatabase2(View view){

        Intent toDatabase = new Intent(this, DatabaseActivity.class);
        startActivity(toDatabase);

    }

}
