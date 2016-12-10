package arend.arendvandormalen_pset6;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void logIn(View view){

        Intent toLogIn = new Intent(this, LogInActivity.class);
        startActivity(toLogIn);

    }

    public void toDatabase(View view){

        Intent toDatabase = new Intent(this, DatabaseActivity.class);
        startActivity(toDatabase);

    }

}
