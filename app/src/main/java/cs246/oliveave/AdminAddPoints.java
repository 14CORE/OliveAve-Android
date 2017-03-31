package cs246.oliveave;

import android.content.Intent;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
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
    private FirebaseUser _newUser;
    TextView _userName;
    TextView _userPoints;
    User customer;
    int points;
    String mPoints;
    Button redeemBtn;
    Button addBtn;
    Button subtractBtn;
    TextView amountText;
    CheckBox selectAmount;
    String TAG;
    String localPoints;
    //final String getOldPoints = customer.getPoints();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_points);
        Bundle extras = getIntent().getExtras();
        redeemBtn = (Button)findViewById(R.id.redeemBtn);
        addBtn = (Button) findViewById(R.id.addPointsBtn);
        subtractBtn = (Button) findViewById(R.id.subtractPointsBtn);
        amountText = (TextView) findViewById(R.id.amountText);
        selectAmount = (CheckBox) findViewById(R.id.checkBox);
        _userName = (TextView)findViewById(R.id.userName);
        _userPoints = (TextView)findViewById(R.id.userPoints);

        if(extras == null){
            userKey = null;
            finish();
        }else{
            userKey = extras.getString("UserUid");
        }

        try{
            _databaseRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://oliveavecs246.firebaseio.com/Users/"+userKey);
            _authDb = FirebaseAuth.getInstance();
        }catch(Exception e){
            e.getMessage();
            Toast.makeText(this, "USER DOESN'T EXIST",Toast.LENGTH_LONG).show();
            Intent i = new Intent(this, AdminMenu.class);
            startActivity(i);
            finish();
        }
    }

    @Override
    protected void onStart(){
        super.onStart();
            _databaseRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    try {
                        customer = dataSnapshot.getValue(User.class);
                        _userName.setText(customer.getName());
                        _userPoints.setText(customer.getPoints() + " points");
                        mPoints = customer.getPoints();
                        localPoints = customer.getPoints();
                        points = Integer.parseInt(customer.getPoints());
                    }catch (Exception e){
                        e.getMessage();
                        userDoesntExist();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


    }
    public void subtractPoints(View view){
        if(points > 0) {
            points--;
            mPoints = String.valueOf(points);
            _databaseRef.child("points").setValue(mPoints);
        }
    }


    public void addPoints(View view){
        if (!selectAmount.isChecked()){
            //amountText.setEnabled(true);
            points++;
        }
        else{

            if (amountText.getText().toString().compareTo("") == 0)
                Toast.makeText(this,"Enter an amount", Toast.LENGTH_LONG).show();
            else{
                String value = amountText.getText().toString();
                int amount = Integer.parseInt(value);
                points += amount / 20;
                // error handling if there is only digits
                //if amount is empty SEND AN ERROR MESSAGE
            }

        }
        mPoints = String.valueOf(points);
        _databaseRef.child("points").setValue(mPoints);
    }

    public void redeemPoints(View view){
        if(points >= 10){
            points -= 10;
            mPoints = String.valueOf(points);
            _databaseRef.child("points").setValue(mPoints);
        }
}

    public void cancelTransaction(View view){
        //_databaseRef.child("points").setValue(getOldPoints);
        finish();

    }

    public void userDoesntExist(){
        Toast.makeText(this, "USER DOESN'T EXIST",Toast.LENGTH_LONG).show();
        finish();
    }
}