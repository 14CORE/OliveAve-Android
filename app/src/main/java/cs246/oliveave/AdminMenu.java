package cs246.oliveave;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.R.attr.name;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import org.json.JSONException;
import org.json.JSONObject;

public class AdminMenu extends AppCompatActivity implements View.OnClickListener {
    //View Objects
    private Button buttonScan;
    private TextView textViewName, textViewAddress;

    //qr code scanner object
    private IntentIntegrator qrScan;
    String uidToNextAct;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu);

        //View objects
        buttonScan = (Button) findViewById(R.id.buttonScan);


        //intializing scan object
        qrScan = new IntentIntegrator(this);

        //attaching onclick listener
        buttonScan.setOnClickListener(this);
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
                    //startActivity(sendUid);
                    //converting the data to json
                    JSONObject obj = new JSONObject(result.getContents());
                    //setting values to textviews
                    //textViewName.setText(uidToNextAct);
                    //textViewAddress.setText(obj.getString("address"));
                } catch (JSONException e) {
                    e.printStackTrace();
                    //if control comes here
                    //that means the encoded format not matches
                    //in this case you can display whatever data is available on the qrcode
                    //to a toast
                    //Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
                    uidToNextAct = result.getContents();
                    Intent sendUid = new Intent(AdminMenu.this, AdminAddPoints.class);
                    sendUid.putExtra("UserUid", uidToNextAct);
                    startActivity(sendUid);
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
    @Override
    public void onClick(View view) {
        //initiating the qr code scan
        qrScan.initiateScan();
    }


    /*EditText emailToSearch;
    String userUid;

    //private ZXingScannerView scannerView;
    IntentIntegrator integrator = new IntentIntegrator(this);
    String uidToNextAct;




    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu);
        emailToSearch = (EditText)findViewById(R.id.userEmail);
        userUid = emailToSearch.getText().toString();
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setOrientationLocked(false);
        integrator.initiateScan();

    }




    DatabaseReference _database;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu);
        emailToSearch = (EditText)findViewById(R.id.userEmail);
        userUid = emailToSearch.getText().toString();
    }

    public void continueToAdminOptions(View view){
        Intent i = new Intent(this, AdminAddPoints.class);
        i.putExtra("UserUid", userUid);
        startActivity(i);
    }

    public void clearUserInput(View view){
        emailToSearch.setText("");
    }*/
}