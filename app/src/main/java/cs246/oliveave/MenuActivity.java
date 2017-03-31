package cs246.oliveave;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

public class MenuActivity extends AppCompatActivity {

    String uidToNextAct;
    User userClient;
    String newUid;
    ImageView admin;
    TextView user_name;
    TextView adminText;
    private DatabaseReference myFirebaseRef;
    private FirebaseAuth mAuth;
    TextView pointstText;
    private IntentIntegrator qrScan;
    //String uidToNextAct;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Bundle extras = getIntent().getExtras();


        userClient = new User();
        admin = (ImageView) findViewById(R.id.adminBtn);
        adminText = (TextView) findViewById(R.id.text_adminBtn);
        pointstText = (TextView) findViewById(R.id.text_checkPoints);

        if (extras == null) {
            newUid = null;
        } else {
            newUid = extras.getString("uid");
        }

        if (newUid.equals("lv6hWWp6mibWOeZZfZpMFh97jrq2")) {
            admin.setVisibility(View.VISIBLE);
            adminText.setVisibility(View.VISIBLE);
            pointstText.setVisibility(View.INVISIBLE);
        } else {
            admin.setVisibility(View.INVISIBLE);
            adminText.setVisibility(View.INVISIBLE);
            pointstText.setVisibility(View.VISIBLE);
        }

        myFirebaseRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://oliveavecs246.firebaseio.com/Users/" + newUid);
        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    protected void onStart() {
        super.onStart();/*
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
        */
    }

    public void signOut(View view) {
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
        qrScan = new IntentIntegrator(this);
        qrScan.initiateScan();
        Intent i = new Intent(this, AdminAddPoints.class);
        startActivity(i);
    }

    public void displayStoreInfo(View view) {
        Intent i = new Intent(this, AboutUs.class);
        startActivity(i);
    }

    //Getting the scan results
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (result != null) {
            //if qrcode has nothing in it
            if (result.getContents() == null) {
                Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show();
            } else {
                //if qr contains data
                try {
                    //converting the data to json
                    JSONObject obj = new JSONObject(result.getContents());

                } catch (JSONException e) {
                    e.printStackTrace();
                    //if control comes here
                    //that means the encoded format not matches
                    //in this case you can display whatever data is available on the qrcode
                    uidToNextAct = result.getContents();
                    Intent sendUid = new Intent(MenuActivity.this, AdminAddPoints.class);
                    sendUid.putExtra("UserUid", uidToNextAct);
                    startActivity(sendUid);
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onBackPressed() {
       Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
