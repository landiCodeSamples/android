package com.se391.davidlandi.landifinal;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class SignUpActivity extends ActionBarActivity {
    private EditText name;
    private EditText email;
    private EditText userName;
    private EditText password;
    private EditText confirmPassword;
    private  TextView dataInputError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        name = (EditText)findViewById(R.id.txtName);
        email = (EditText)findViewById(R.id.txtEmail);
        userName = (EditText)findViewById(R.id.txtLoginName);
        password = (EditText)findViewById(R.id.txtPassword);
        confirmPassword = (EditText)findViewById(R.id.txtConfPassword);
        dataInputError = (TextView)findViewById(R.id.lblError);
    }

    public void onSignUpClickEvent(View v){
        FieldValidator validate = new FieldValidator(
                AppUtils.getStrValue(name),
                AppUtils.getStrValue(email),
                AppUtils.getStrValue(userName),
                AppUtils.getStrValue(password),
                AppUtils.getStrValue(confirmPassword)
        );
        if(validate.signUpFieldsAreValid()){
            DatabaseHelper dbHelper = new DatabaseHelper(this);
            Member member = new Member();
            member.setUsrName(AppUtils.getStrValue(name));
            member.setUsrEmail(AppUtils.getStrValue(email));
            member.setUsrLoginName(AppUtils.getStrValue(userName));
            member.setPassword(AppUtils.getStrValue(password));
            dbHelper.insertMember(member);
            Intent signUpAction = new Intent(this, MainActivity.class);
            signUpAction.putExtra("data", AppUtils.getStrValue(userName));
            setResult(Activity.RESULT_OK, signUpAction);
            finish();
        }
        else{
            Toast t = Toast.makeText(SignUpActivity.this, validate.getAllSignUpFormErrors(),
                    Toast.LENGTH_LONG);
            t.show();
            dataInputError.setText("Error: All fields must contain valid data");
            AppUtils.setTextColor(dataInputError, "#D91E18");
            AppUtils.clearField(name);
            AppUtils.clearField(email);
            AppUtils.clearField(userName);
            AppUtils.clearField(password);
            AppUtils.clearField(confirmPassword);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sign_up, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
