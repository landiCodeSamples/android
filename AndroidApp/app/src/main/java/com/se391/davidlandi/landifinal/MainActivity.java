package com.se391.davidlandi.landifinal;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    private boolean isLoggedIn;
    private TextView lblWelcome;
    private static final int LOGIN_REQUEST = 1;
    private static final int SIGNUP_REQUEST = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        isLoggedIn = false;
        lblWelcome = (TextView)findViewById(R.id.lblWelcome);

    }

    public void onMainLoginClick(View v){
        //Intent will send user to the login screen and wait for successful login.
        //If already logged in, a error message will be displayed
        if(isLoggedIn){
            Toast t = Toast.makeText(MainActivity.this, "Error: You have already logged in",
                    Toast.LENGTH_SHORT);
            t.show();
        }
        startActivityForResult(new Intent(this, LoginActivity.class), LOGIN_REQUEST);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        //On return from login activity the welcome label will change to welcome back with the username.
        //popup will display msg "admin dashboard now available"
        if(requestCode == LOGIN_REQUEST && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            String usrLoggedIn = extras.get("data").toString();
            lblWelcome.setText("Welcome Back " + usrLoggedIn);
            isLoggedIn = true;
            Toast t = Toast.makeText(MainActivity.this, "Admin Dashboard now available",
                    Toast.LENGTH_SHORT);
            t.show();
        }
        //On return from sign up activity.
        //Set msg to "Welcome username please sign in"
        //Pop up display "new member has been added"
         if(requestCode == SIGNUP_REQUEST && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            String msg = extras.get("data").toString();
            lblWelcome.setText("Welcome " + msg + " please login");
            isLoggedIn = false;
            Toast t = Toast.makeText(MainActivity.this, "New member has been added",
                    Toast.LENGTH_SHORT);
            t.show();
        }
    }

    public void onMainSignUpClick(View v){
        //If user is already logged in, display popup msg with error.
        //If the a user is not logged in, intent send to signup page.
        if(isLoggedIn){
            Toast t = Toast.makeText(MainActivity.this, "Error: You are already a member",
                    Toast.LENGTH_SHORT);
            t.show();
        }
        if(!isLoggedIn) {
            startActivityForResult(new Intent(this, SignUpActivity.class), SIGNUP_REQUEST);
        }
    }

    public void onMainDashboardClick(View v){
        if(isLoggedIn){
            Intent dashBoardIntent = new Intent(MainActivity.this, DashBoardActivity.class);
            startActivity(dashBoardIntent);
        }
        else {
            Toast t = Toast.makeText(MainActivity.this, "Error: Please login to gain access to the Dashboard",
                    Toast.LENGTH_SHORT);
            t.show();
        }
    }

    public void onBtnSignOutClick(View v){
        //When sign out button is clicked the application will restart clearing all static data
        Intent signOutAction = getBaseContext().getPackageManager()
                .getLaunchIntentForPackage( getBaseContext().getPackageName());
        signOutAction.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(signOutAction);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
