package cs246.oliveave;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminAddPoints extends AppCompatActivity {

    TextView userDisplay;
    String userEmail;
    String userKey;
    private  DatabaseReference _databaseRef;
    private FirebaseAuth _authDb;
    TextView _userName;
    TextView _userPoints;
    User customer;
    int points;
    String mPoints;
    Button redeemBtn;
    Button addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_points);
        Bundle extras = getIntent().getExtras();
        redeemBtn = (Button)findViewById(R.id.redeemBtn);

        addBtn = (Button) findViewById(R.id.addPointsBtn);

        _userName = (TextView)findViewById(R.id.userName);
        _userPoints = (TextView)findViewById(R.id.userPoints);

        if(extras == null){
            userKey = null;
        }else{
            userKey = extras.getString("UserUid");
        }
        _databaseRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://oliveavecs246.firebaseio.com/Users/" + userKey);
        _authDb = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart(){
        super.onStart();
        _databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                customer = dataSnapshot.getValue(User.class);
                _userName.setText(customer.getName());
                _userPoints.setText(customer.getPoints()+" points");
                mPoints = customer.getPoints();
                points = Integer.parseInt(customer.getPoints());
                if(points==10){

                    redeemBtn.setClickable(true);
                    addBtn.setClickable(false);
                }else if(points<10){
                    redeemBtn.setClickable(false);
                    addBtn.setClickable(true);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });
    }

    public void addPoints(View view){

        //points = Integer.parseInt(customer.getPoints());
        if(points==10) {
            Toast.makeText(this,"15% off next purchase", Toast.LENGTH_LONG).show();
        }
        points++;
        mPoints = String.valueOf(points);
        _databaseRef.child("points").setValue(mPoints);

    }

    public void redeemPoints(View view){

        _databaseRef.child("points").setValue("0");

}

    public void cancelTransaction(View view){
        finish();

    }
}