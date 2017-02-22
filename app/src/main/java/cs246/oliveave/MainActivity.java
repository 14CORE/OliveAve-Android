package cs246.oliveave;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import cs246.oliveave.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void adminBtn(View view){
        Intent i = new Intent(this, AdminMenu.class);
        startActivity(i);

    }

    @Override
    public void onClick(View v) {
        // Add buttons here

    }
}


// HEY GUYS (ANDERSON!!!)
// HEY GUYS (BRUNO)
