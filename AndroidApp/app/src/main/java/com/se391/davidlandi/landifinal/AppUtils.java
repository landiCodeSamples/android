package com.se391.davidlandi.landifinal;

import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by David Landi on 5/25/2015.
 */
public class AppUtils {
    public static String getStrValue(EditText field){
        return field.getText().toString();
    }

    public static void clearField(EditText id){
        id.setText("");
    }

    public static void setTextColor(TextView id, String hex){
        id.setTextColor(android.graphics.Color.parseColor(hex));
    }

    public static void setLayoutColor(LinearLayout id, String hex){
        id.setBackgroundColor(android.graphics.Color.parseColor(hex));
    }

    public static String newline(){
        return System.getProperty("line.separator");
    }

    public static String getName(DatabaseHelper data, int count){
        return data.getAllMembers().get(count).getUsrName();
    }

    public static String getEmail(DatabaseHelper data, int count){
        return data.getAllMembers().get(count).getUsrEmail();
    }

    public static String getUserName(DatabaseHelper data, int count){
        return data.getAllMembers().get(count).getUsrLoginName();
    }

    public static String getMainActivityDoc(){
        String strDoc;

        strDoc = "MainActivity" + newline() +
                 "Acts as a main menu and is the first screen the user " + newline() +
                 "will see when the app is launched." + newline() + newline() +
                 "Events" + newline() +
                 "Login: Sends user to LoginActivity via intent." + newline() + newline() +
                 "SignUp: Sends user to SignUpActivity via intent. If user is already logged in " +
                 "a toast message will display error message." + newline() + newline() +
                 "Admin DashBoard: Sends user to DashBoardActivity via intent. If a user is not " +
                 "a toast message will display error message." + newline() + newline() +
                 "Sign Out: Clears login data back to initial state by setting intent flag to " +
                 "FLAG_ACTIVITY_CLEAR_TOP and then launching the intent.";

        return strDoc;
    }

    public static String getLoginActivityDoc(){
        String strDoc;
        strDoc = "LoginActivity" + newline() +
        "Displays a form that allows the user to enter username and password which will be used " +
        "to query the sql lite database search for a match. If at any id the username and password " +
        "matches the result will be a successful login.  Once a successful login has been made" +
        "the user will be directed back to the main activity via intent and take the username" +
        "along with it, to be displayed at the top of the screen. (Intent putExtra method)" +
        "this action also sets a boolean flag on the main activity which will enable the admin" +
        "DashBoard.  If the login is not successful or data in any field in invalid, an error message" +
        "will be displayed by way of toast";
        return strDoc;
    }

    public static String getSignUpActivityDoc(){
        String strDoc;
        strDoc = "SignUpActivity" + newline() +
                "Displays a form that allows the user to enter personal data that will be stored in " +
                "the database to create a new member. If all fields contain valid data the new member " +
                "is sent back to main activity and prompted to sign in.";
        return strDoc;
    }

    public static String getDashBoardActivityDoc(){
        String strDoc;
        strDoc = "DashBoardActivity";
        return strDoc;
    }


}
