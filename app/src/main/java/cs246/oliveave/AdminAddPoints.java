package cs246.oliveave;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminAddPoints extends AppCompatActivity {

    TextView userDisplay;
    String userEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_points);
        userDisplay = (TextView) findViewById(R.id.welcomeUser);
        Intent intent = getIntent();
        //userEmail = intent.getExtras().getString(userEmail);

        userDisplay.setText(userEmail);

    }


    public void addPointsToUser(){

    }

    public void redeemPointsFromUser(){

    }

    public void cancelTransaction(){

    }
}
