package cs246.oliveave;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminAddPoints extends AppCompatActivity {

    TextView userDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_points);
        userDisplay = (TextView) findViewById(R.id.welcomeUser);
    }


    public void addPointsToUser(){

    }

    public void redeemPointsFromUser(){

    }

    public void cancelTransaction(){

    }
}
