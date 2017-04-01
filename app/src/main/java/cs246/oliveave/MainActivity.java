package cs246.oliveave;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private EditText mEmail;
    private EditText mPassword;
    private ProgressDialog progressDialog;
    private FirebaseAuth mAuth;
    private TextView signUp;
    String uidToNextAct;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        mEmail = (EditText) findViewById(R.id.edit_text_email_id);
        mPassword = (EditText) findViewById(R.id.edit_text_password);
        signUp = (TextView) findViewById(R.id.signUp);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            uidToNextAct = mAuth.getCurrentUser().getUid();
            Intent i = new Intent(MainActivity.this, MenuActivity.class);
            i.putExtra("uid", uidToNextAct);
            startActivity(i);
        }

        Spannable colorSpan = new SpannableString("Don't have an account? Sign Up");
        colorSpan.setSpan(new ForegroundColorSpan(Color.BLUE), 22, 30, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        signUp.setText(colorSpan);
    }

    public void login(){

        String email = mEmail.getText().toString();
        String password = mPassword.getText().toString();

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    uidToNextAct = mAuth.getCurrentUser().getUid();
                    Intent i = new Intent(MainActivity.this, MenuActivity.class);
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
        if(mEmail.getText().toString().trim().length() <= 0){
            Toast.makeText(getApplicationContext(),"Enter email", Toast.LENGTH_SHORT).show();
            return;
        }
        if(mPassword.getText().toString().trim().length() <= 0){
            Toast.makeText(getApplicationContext(),"Enter password", Toast.LENGTH_SHORT).show();
            return;
        }
        login();
    }

    public void resetPassword(View view){
        final String email = mEmail.getText().toString();
        if(email.compareTo("") == 0){
            Toast.makeText(MainActivity.this, "Enter email", Toast.LENGTH_SHORT).show();
        }
        else{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Are you sure you want to reset your password?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            mAuth.sendPasswordResetEmail(email);
                            Toast.makeText(MainActivity.this, "Password reset email sent", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("Cancel", null);
            AlertDialog alert = builder.create();
            alert.show();

        }
    }
}

