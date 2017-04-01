package cs246.oliveave;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.KeyListener;
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
    Button subtractBtn;
    TextView amountText;
    CheckBox selectAmount;
    String localPoints;
    Boolean getPoints = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_points);
        Bundle extras = getIntent().getExtras();
        redeemBtn = (Button)findViewById(R.id.redeemBtn);
        addBtn = (Button) findViewById(R.id.addPointsBtn);
        subtractBtn = (Button) findViewById(R.id.subtractPointsBtn);
        amountText = (TextView) findViewById(R.id.amountText);
        amountText.setVisibility(View.INVISIBLE);
        selectAmount = (CheckBox) findViewById(R.id.checkBox);
        selectAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectAmount.isChecked())
                    amountText.setVisibility(View.VISIBLE);
                else
                    amountText.setVisibility(View.INVISIBLE);
            }
        });
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
            Intent i = new Intent(this, MenuActivity.class);
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
                        if (getPoints){
                            localPoints = mPoints;
                            getPoints = false;
                        }

                        points = (int)Double.parseDouble(customer.getPoints());
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
            points++;
        }
        else{
            if (amountText.getText().toString().compareTo("") == 0)
                Toast.makeText(this,"Enter an amount", Toast.LENGTH_LONG).show();
            else{
                String value = amountText.getText().toString();
                int amount;
                boolean success = false;
                try{
                    // declared here and bellow just for the purpose of debugging
                    amount = (int)Double.parseDouble(value);
                    success = true;
                } catch (NumberFormatException nfe){
                    Toast.makeText(this,"Enter a valid amount", Toast.LENGTH_LONG).show();
                }
                if (success){
                    amount = (int)Double.parseDouble(value);
                    points += amount / 20;
                }
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
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Are you sure you want to cancel your changes?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        _databaseRef.child("points").setValue(localPoints);
                        finish();
                    }
                })
                .setNegativeButton("Cancel", null);
        AlertDialog alert = builder.create();
        alert.show();


        //Intent intent = new Intent(AdminAddPoints.this, MenuActivity.class);
        //startActivity(intent);


    }

    public void userDoesntExist(){
        Toast.makeText(this, "USER DOESN'T EXIST",Toast.LENGTH_LONG).show();
        finish();
    }
}