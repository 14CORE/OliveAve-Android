package cs246.oliveave;

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

public class AdminSignIn extends AppCompatActivity {

    private EditText adminUsername, adminPwd;
    //private Button signIn;
    private FirebaseAuth adminAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_sign_in);

        adminUsername = (EditText)findViewById(R.id.adminUsername);
        adminPwd = (EditText) findViewById(R.id.adminPassword);
        adminAuth = FirebaseAuth.getInstance();
    }

    public void adminSignIn(View view){
        String _email = adminUsername.getText().toString();
        final String _password = adminPwd.getText().toString();

        if(TextUtils.isEmpty(_email)){
            Toast.makeText(getApplicationContext(),"Enter email address", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(_password)){
            Toast.makeText(getApplicationContext(),"Enter password address", Toast.LENGTH_SHORT).show();
            return;
        }

        adminAuth.signInWithEmailAndPassword(_email,_password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(!task.isSuccessful()){
                    if(_password.length() < 4){
                        adminPwd.setError("Password has to be at least 4 characters.");
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "WRONG PASSWORD", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Intent i = new Intent(AdminSignIn.this, AdminMenu.class);
                    startActivity(i);
                    finish();
                }
            }
        });

    }
}
