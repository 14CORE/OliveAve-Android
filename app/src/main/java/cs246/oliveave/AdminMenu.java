package cs246.oliveave;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import org.json.JSONException;
import org.json.JSONObject;

public class AdminMenu extends AppCompatActivity  {
    //qr code scanner object
    private IntentIntegrator qrScan;
    String uidToNextAct;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu);

        //intializing scan object
        qrScan = new IntentIntegrator(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        qrScan.initiateScan();
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
                    Intent sendUid = new Intent(AdminMenu.this, AdminAddPoints.class);
                    sendUid.putExtra("UserUid", uidToNextAct);
                    startActivity(sendUid);
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}