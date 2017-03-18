package cs246.oliveave;

import android.app.ProgressDialog;
        import android.content.Intent;
        import android.support.annotation.NonNull;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.text.TextUtils;
import android.util.Log;
import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;


        import com.google.android.gms.tasks.OnCompleteListener;
        import com.google.android.gms.tasks.Task;
        import com.google.firebase.auth.AuthResult;
        import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private Button buttonRegister;
    private Button buttonSignIn;
    private EditText mEmail;
    private EditText mPassword;
    private ProgressDialog progressDialog;
    private FirebaseAuth mAuth;
    //private DatabaseReference myFirebaseRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://oliveavecs246.firebaseio.com/");
    //User user;
    //FirebaseUser mFirebaseUser;
    //private FirebaseAuth.AuthStateListener mAuthListener;
    String uidToNextAct;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        buttonRegister = (Button) findViewById(R.id.buttonSignUp);
        buttonSignIn = (Button) findViewById(R.id.button_user_log_in);
        mEmail = (EditText) findViewById(R.id.edit_text_email_id);
        mPassword = (EditText) findViewById(R.id.edit_text_password);

        //mFirebaseUser = mAuth.getCurrentUser();

        if(mAuth.getCurrentUser()!=null){
            Toast.makeText(MainActivity.this, "Current User", Toast.LENGTH_SHORT).show();
        }
    }

    public void login(){
        String email = mEmail.getText().toString();
        String password = mPassword.getText().toString();

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    uidToNextAct = mAuth.getCurrentUser().getUid();
                    Intent i = new Intent(MainActivity.this, ViewPoints.class);
                    i.putExtra("uid", uidToNextAct);
                    startActivity(i);
                } else {
                    Toast.makeText(MainActivity.this, "LOGIN FAILED", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void signIn(View view){
        Intent i = new Intent(this, SignUpActivity.class);
        startActivity(i);
    }

    public void onLoginClicked(View view){
        login();
    }

    public void adminBtn(View view) {
        Intent i = new Intent(this, AdminSignIn.class);
        startActivity(i);
    }

    public void signOut(View view){
        mAuth.signOut();
    }

    public void resetPassword(View view){
        String email = mEmail.getText().toString();
        if(email.compareTo("") == 0){
            Toast.makeText(MainActivity.this, "Enter email", Toast.LENGTH_SHORT).show();
        }
        else{
            mAuth.sendPasswordResetEmail(email);
            Toast.makeText(MainActivity.this, "Password reset email sent", Toast.LENGTH_SHORT).show();
        }
    }
}

