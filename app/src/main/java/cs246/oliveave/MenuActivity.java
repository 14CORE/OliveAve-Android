package cs246.oliveave;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    String uidToNextAct;
    User userClient;
    String newUid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_menu);
        Bundle extras = getIntent().getExtras();
        userClient = new User();
        if(extras == null) {
            newUid = null;
        } else {
            newUid = extras.getString("uid");
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
}
