package cs246.oliveave;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import cs246.oliveave.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonRegister;
    private Button buttonSignIn;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        buttonRegister = (Button) findViewById(R.id.buttonSignUp);
    }

    public void adminBtn(View view){
        Intent i = new Intent(this, AdminMenu.class);
        startActivity(i);

    }

    private void registerUser() {
        String email =
    }
    // add login and sign in button here.
    // It would be a good idea to add the adminBtn here as well or hide it a little better
    @Override
    public void onClick(View v) {
        // Add buttons here

    }
}


// HEY GUYS (ANDERSON!!!)
// HEY GUYS (BRUNO)
