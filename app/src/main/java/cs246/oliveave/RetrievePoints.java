package cs246.oliveave;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RetrievePoints extends AppCompatActivity {

    private TextView pointsValue;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_points);
        //FirebaseUser user = firebaseAuth.getCurrentUser();

        pointsValue = (TextView) findViewById(R.id.textViewPointsRetrieved);
        //reference.child(user.getUid()).setValue(userPoints);

        //FirebaseDatabase database = FirebaseDatabase.getInstance();
        //DatabaseReference databaseReference = database.getReference();
        //DatabaseReference retrievePoints = databaseReference.child("Points");

        //FirebaseUser = user firebaseauth.getCurrentUser();

        //databaseReference.child(user.getUid()).setValue(userPoints);




    }
}
