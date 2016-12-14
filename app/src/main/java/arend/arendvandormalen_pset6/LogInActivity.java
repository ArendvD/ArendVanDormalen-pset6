package arend.arendvandormalen_pset6;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LogInActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String TAG = "LogIn";

    EditText emailBox;
    EditText passwordBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        // Retrieve fields used in multiple methods
        emailBox = (EditText)findViewById(R.id.email_box);
        passwordBox = (EditText)findViewById(R.id.password_box);

        // Connect to Firebase
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mAuthListener != null){
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    // New user is added to database through build-in Firebase function
    public void createAccount(View view){

        if(!validateForm()){
            return;
        }

        final String email = emailBox.getText().toString();
        final String password = passwordBox.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful() && task.isComplete()) {
                            Toast.makeText(LogInActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            Log.d("TaskError", task.getException().toString());
                        } else {

                            Toast.makeText(LogInActivity.this, "Account created.",
                                    Toast.LENGTH_SHORT).show();
                            Intent toUser = new Intent(LogInActivity.this, UserActivity.class);
                            toUser.putExtra("email", email);
                            toUser.putExtra("password", password);
                            startActivity(toUser);

                        }

                    }
                });

    }

    // Existing user is signed in through build-in Firebase function
    public void signIn(View view) {

        if(!validateForm()){
            return;
        }

        final String email = emailBox.getText().toString();
        final String password = passwordBox.getText().toString();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithEmail", task.getException());
                            Toast.makeText(LogInActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        } else {

                            Intent toUser = new Intent(LogInActivity.this, UserActivity.class);
                            toUser.putExtra("email", email);
                            toUser.putExtra("password", password);
                            startActivity(toUser);

                        }


                    }
                });
    }

    // Checks whether form has been filled in and password has correct length
    private boolean validateForm() {
        boolean valid = true;

        String email = emailBox.getText().toString();
        if (TextUtils.isEmpty(email)) {
            emailBox.setError("Required.");
            valid = false;
        } else {
            emailBox.setError(null);
        }

        String password = passwordBox.getText().toString();
        if (TextUtils.isEmpty(password)) {
            passwordBox.setError("Required.");
            valid = false;
        } else if (password.length() < 6) {
            passwordBox.setError("Password should contain six or more characters");
            valid = false;
        }
        else{
            passwordBox.setError(null);
        }

        return valid;
    }

}