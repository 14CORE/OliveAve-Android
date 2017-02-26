package cs246.oliveave;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth firebaseAuth;
    private TextView textViewUserEmail;
    private Button buttonLogout;
    private TextView retrievePoints;

    private DatabaseReference databaseReference;
    private EditText editTextPoints;
    private Button buttonSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }

        databaseReference = FirebaseDatabase.getInstance().getReference();

        editTextPoints = (EditText) findViewById(R.id.editTextPoints);
        buttonSave = (Button) findViewById(R.id.savePoints);

        FirebaseUser user = firebaseAuth.getInstance().getCurrentUser();

        textViewUserEmail = (TextView) findViewById(R.id.textViewUserEmail);

        textViewUserEmail.setText("Welcome " + user.getEmail());
        buttonLogout = (Button) findViewById(R.id.buttonLogOut);

        buttonLogout.setOnClickListener(this);
        buttonSave.setOnClickListener(this);

        retrievePoints = (TextView) findViewById(R.id.retrievePoints);

        retrievePoints.setText("Points go here");

    }

    private void savePoints(){
        String points = editTextPoints.getText().toString().trim();

        UserPoints userPoints = new UserPoints(points);

        FirebaseUser user = firebaseAuth.getCurrentUser();

        //databaseReference.child(user.getUid()).setValue(userPoints);
        databaseReference.child(user.getUid()).setValue(points);

        Toast.makeText(this, "Points Saved...", Toast.LENGTH_SHORT).show();

        databaseReference.child(points).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                //retrievePoints is my TextView
                retrievePoints.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    @Override
    public void onClick(View v) {
        if(v == buttonLogout){
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }

        if(v == buttonSave){
            savePoints();
        }

    }

}