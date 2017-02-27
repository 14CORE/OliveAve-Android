package cs246.oliveave;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminMenu extends AppCompatActivity {

    Button confirmBtn;
    Button clearBtn;
    EditText userInput;
    //private FirebaseDatabase dbFirebase;
    private DatabaseReference mDatabase;
    private FirebaseAuth fbAuth;
    TextView adminName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu);

        fbAuth = FirebaseAuth.getInstance();
        //dbFirebase = FirebaseDatabase.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        FirebaseUser _admin = fbAuth.getCurrentUser();


        clearBtn = (Button)findViewById(R.id.clearUserID);
        confirmBtn = (Button) findViewById(R.id.emailContinue);
        userInput = (EditText) findViewById(R.id.userEmail);
        adminName = (TextView) findViewById(R.id.activeAdmin);

        adminName.setText("Admin session for: " + _admin.getEmail());

        checkValidation();

        userInput.addTextChangedListener(mWatcher);


    }

    //validation for text in input
    private void checkValidation() {
        // TODO Auto-generated method stub

        if ((TextUtils.isEmpty(userInput.getText())))
            clearBtn.setEnabled(false);
        else
            clearBtn.setEnabled(true);

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
        startActivity(intent);
    }

    public void clearUserInput(View view){
        userInput.setText("");
    }
}