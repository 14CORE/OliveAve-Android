package cs246.oliveave;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    String uidToNextAct;
    User userClient;
    String newUid;
    Button admin;

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
        Intent intent = new Intent(MenuActivity.this, ViewNewProducts.class);
        startActivity(intent);
    }


    public void adminBtn(View view) {
        Intent i = new Intent(this, AdminSignIn.class);
        startActivity(i);
    }
}
