package arend.arendvandormalen_pset6;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Arend on 2016-12-05.
 * Home screen of this app. A picture of the Rijksmusuem is displayed. Users have the choice
 * of signing in or going to the database directly, which removes the possibility of adding
 * favorites.
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Sends users to log-in activity
    public void logIn(View view){
        Intent toLogIn = new Intent(this, LogInActivity.class);
        startActivity(toLogIn);
    }

    // Sends users to database activity
    public void toDatabase(View view){
        Intent toDatabase = new Intent(this, DatabaseActivity.class);
        startActivity(toDatabase);
    }

}
