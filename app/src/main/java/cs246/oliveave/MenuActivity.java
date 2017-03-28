package cs246.oliveave;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MenuActivity extends AppCompatActivity {

    String uidToNextAct;
    User userClient;
    String newUid;
    Button admin;
    TextView user_name;
    private DatabaseReference myFirebaseRef;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Bundle extras = getIntent().getExtras();


        userClient = new User();
        admin = (Button) findViewById(R.id.adminBtn);


        if(extras == null) {
            newUid = null;
        } else {
            newUid = extras.getString("uid");
        }

        if(newUid.equals("lpHSI7WLAWTngyRwwpUN2fqbwKI2")){
           admin.setVisibility(View.VISIBLE);
        }
        else{
            admin.setVisibility(View.INVISIBLE);
        }

        myFirebaseRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://oliveavecs246.firebaseio.com/Users/"+ newUid);
        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    protected void onStart() {
        super.onStart();
        user_name = (TextView) findViewById(R.id.user_name);
        //Referring to the name of the User who has logged in currently and adding a valueChangeListener
        myFirebaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userClient=dataSnapshot.getValue(User.class);
                // To say hello and UpperCase the first letter of the name only
                user_name.setText("Hello, " + userClient.getName().substring(0,1).toUpperCase() +
                        userClient.getName().substring(1));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void signOut(View view){
        Toast.makeText(this, "BYE BYE", Toast.LENGTH_SHORT).show();
        mAuth.signOut();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void newProducts(View view) {
        Intent intent = new Intent(MenuActivity.this, ViewNewProducts.class);
        startActivity(intent);
    }

    public void checkPoints(View view) {
        Intent intent = new Intent(MenuActivity.this, ViewPoints.class);
        intent.putExtra("uid", newUid);
        startActivity(intent);
    }

    public void findStore(View view) {
        Intent intent = new Intent(MenuActivity.this, MapsActivity.class);
        startActivity(intent);
    }

    public void adminBtn(View view) {
        Intent i = new Intent(this, AdminSignIn.class);
        startActivity(i);
    }
}
