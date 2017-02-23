package cs246.oliveave;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AdminMenu extends AppCompatActivity {

    Button confirmBtn;
    Button clearBtn;
    EditText userInput;
    //private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu);

        clearBtn = (Button)findViewById(R.id.clearUserID);
        confirmBtn = (Button) findViewById(R.id.emailContinue);
        userInput = (EditText) findViewById(R.id.userEmail);

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
        Intent intent = new Intent(this, PointsActivity.class);
        startActivity(intent);
    }

    public void clearUserInput(View view){
        userInput.setText("");
    }
}
