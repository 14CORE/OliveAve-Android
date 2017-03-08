package cs246.oliveave;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Console;


public class AdminMenu extends AppCompatActivity {

    Button clearBtn;
    EditText userInput;
    TextView adminName;
    String emailToSearch;
    private FirebaseDatabase dbFirebase;
    private DatabaseReference mDatabase;
    private FirebaseAuth fbAuth;

    String ref;
    final String TAG = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu);

        userInput = (EditText) findViewById(R.id.userEmail);
        adminName = (TextView) findViewById(R.id.activeAdmin);

        FirebaseUser _admin = fbAuth.getCurrentUser();
        fbAuth = FirebaseAuth.getInstance();
        dbFirebase = FirebaseDatabase.getInstance();
        mDatabase = dbFirebase.getReference();


        adminName.setText("Admin session for: " + _admin.getEmail());
        //String lookUser = mDatabase.getDatabase().getReference().equals()
        //userInput = mDatabase.getDatabase().getReference().equals("carlos@email.com")toString();
        checkValidation();

        userInput.addTextChangedListener(mWatcher);

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //get all of the children at the root level
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();

                //loop through the databse
                for(DataSnapshot child: children){
                    child.getValue();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    //validation for text in input
    private void checkValidation() {
        // TODO Auto-generated method stub

        if ((TextUtils.isEmpty(userInput.getText())))
            clearBtn.setEnabled(false);
        else
            clearBtn.setEnabled(true);

        //String userEmail = mDatabase.child(emailToSearch).toString();

    }

    //checks for user input to enable the clear button
    TextWatcher mWatcher = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
            // TODO Auto-generated method stub
            checkValidation();
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
            // TODO Auto-generated method stub

        }

        @Override
        public void afterTextChanged(Editable s) {
            // TODO Auto-generated method stub

        }
    };



    public void continueToAdminOptions(View view){
        Intent intent = new Intent(this, AdminAddPoints.class);
        intent.putExtra("userEmail", emailToSearch);
        startActivity(intent);
    }

    public void clearUserInput(View view){
        userInput.setText("");
    }
}