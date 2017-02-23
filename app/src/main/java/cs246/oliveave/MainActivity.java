package cs246.oliveave;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


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
        buttonSignIn = (Button) findViewById(R.id.buttonSignIn);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        buttonRegister.setOnClickListener(this);
        buttonSignIn.setOnClickListener(this);

        //Check if user is already signed-in
        //if(firebaseAuth.getCurrentUser() != null) {
        //  // profile activity here
        //finish();
        //startActivity(new Intent(getApplicationContext(), ProfileActivity.class));

        //}

    }

    private void registerUser() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            // Email is empty
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
            // Stop the function from executing further
            return;
        }

        if (TextUtils.isEmpty(password)) {
            // Password is empty
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            // Stop the function from executing further
            return;
        }
        // if validations are okay, progress bar will show
        progressDialog.setMessage("Registering User...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // user register successfully
                            //Toast.makeText(MainActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                            //progressDialog.dismiss();
                            //finish();
                            //startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(MainActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }

    private void userLogin() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            // Email is empty
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
            // Stop the function from executing further
            return;
        }

        if (TextUtils.isEmpty(password)) {
            // Password is empty
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            // Stop the function from executing further
            return;
        }

        // if validations are okay, progress bar will show
        progressDialog.setMessage("Logging in User...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();

                        //if(task.isSuccessful()) {
                        //start the profile activity
                        //  finish();
                        //startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                        //}

                    }
                });


    }


    public void adminBtn(View view) {
        Intent i = new Intent(this, AdminMenu.class);
        startActivity(i);

    }


    @Override
    public void onClick(View v) {
        if (v == buttonRegister) {
            registerUser();

        }

        if (v == buttonSignIn) {
            userLogin();

        }
    }
}


// HEY GUYS (ANDERSON!!!)
// HEY GUYS (BRUNO)
