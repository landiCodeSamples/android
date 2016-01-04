package com.se391.davidlandi.landifinal;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
/*
* Login activity.
* Inorder for the user to successfully login to the application, they will need to already be a
* a member.
* Member info will be stored in sql lite database.
* If the data entered by the user is incorrect, a error message will show in red above the submit
* btn.
* If login is successful redirect back to MainActivity and indicate that the user is login.
* */

public class LoginActivity extends ActionBarActivity {
    private EditText userName;
    private EditText password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userName = (EditText)findViewById(R.id.txtLoginName);
        password = (EditText)findViewById(R.id.txtPassword);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    public void onSubmitClickEvent(View v){
        DatabaseHelper dh = new DatabaseHelper(this);
        String queryResults;
        String un = AppUtils.getStrValue(userName);
        String p = AppUtils.getStrValue(password);
        FieldValidator loginData = new FieldValidator(un, p);
        if(loginData.loginFieldsAreValid()){
            queryResults = dh.queryPassword(p);
            if(p.equals(queryResults)){
                Intent i=new Intent(this, MainActivity.class);
                i.putExtra("data", un);
                setResult(Activity.RESULT_OK, i);
                finish();
            }
            else{
                Toast t = Toast.makeText(LoginActivity.this, "Username or Password does not exisit",
                        Toast.LENGTH_LONG);
                t.show();
            }
        }
        else{
            String msg = loginData.getAllLoginFromErrors();
            Toast t = Toast.makeText(LoginActivity.this, msg,
                    Toast.LENGTH_LONG);
            t.show();
        }
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
