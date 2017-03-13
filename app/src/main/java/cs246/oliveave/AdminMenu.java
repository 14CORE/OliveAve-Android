package cs246.oliveave;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.R.attr.name;

public class AdminMenu extends AppCompatActivity {
    EditText emailToSearch;
    String userUid;
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
    }
}