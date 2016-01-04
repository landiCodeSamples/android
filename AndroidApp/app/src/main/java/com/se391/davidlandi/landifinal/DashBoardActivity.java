package com.se391.davidlandi.landifinal;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class DashBoardActivity extends ActionBarActivity {
    private Switch swcThemeMenu;
    private Switch swcQueryMenu;
    private Switch swcMainMenu;
    private Spinner ddmTheme;
    private Spinner ddmQueryOptions;
    private Spinner ddmQueryResults;
    private  LinearLayout mainLayout;
    private LinearLayout viewThemeMenu;
    private LinearLayout viewQueryMenu;
    private LinearLayout viewMainMenu;
    private LinearLayout menuOptLayout;
    private TextView displayDoc;
    private int docClick = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_alt);
        swcThemeMenu = (Switch)findViewById(R.id.swcTheme);
        swcQueryMenu = (Switch)findViewById(R.id.swcQuery);
        swcMainMenu = (Switch)findViewById(R.id.swcMainMenu);
        ddmTheme = (Spinner)findViewById(R.id.spnThemeDDM);
        ddmQueryOptions = (Spinner)findViewById(R.id.spnQueryDDM);
        ddmQueryResults = (Spinner)findViewById(R.id.spnQueryResults);
        mainLayout = (LinearLayout)findViewById(R.id.llMain);
        viewThemeMenu = (LinearLayout)findViewById(R.id.llTM);
        viewQueryMenu = (LinearLayout)findViewById(R.id.llQM);
        viewMainMenu = (LinearLayout)findViewById(R.id.llMM);
        displayDoc = (TextView)findViewById(R.id.tvDisplayDoc);
        menuOptLayout = (LinearLayout) findViewById(R.id.llMenuSelection);
        setDefaultDashboardView();
        setThemeSpinner();
        setQuerySpinnerOptions();
        setThemeSwitchLogic();
        setQuerySwitchLogic();
        setMainMenuSwitchLogic();
    }

    public void onSetThemeClick(View v){
        //Theme menu view submit button click event
        String msg = "No Theme Selected";
        String selectedTheme = ddmTheme.getSelectedItem().toString();
        menuOptLayout.setBackgroundResource(R.drawable.transovrlay);
        viewThemeMenu.setBackgroundResource(R.drawable.transovrlay);
        viewQueryMenu.setBackgroundResource(R.drawable.transovrlay);
        viewMainMenu.setBackgroundResource(R.drawable.transovrlay);
        if(selectedTheme.equals("Mesh Background")){
            mainLayout.setBackgroundResource(R.drawable.meshbg);
            swcThemeMenu.setChecked(false);
        }
        else if(selectedTheme.equals("Wood Background")){
            mainLayout.setBackgroundResource(R.drawable.woodbg);
            swcThemeMenu.setChecked(false);
        }
        else if(selectedTheme.equals("Android Background")){
            mainLayout.setBackgroundResource(R.drawable.androidthemebg);
            swcThemeMenu.setChecked(false);
        }
        else{
            Toast t = Toast.makeText(DashBoardActivity.this, msg, Toast.LENGTH_SHORT);
            t.show();
        }
    }

    public void onSetQueryClick(View v){
        //Query menu view submit button click event
        String msg = "No Query Selected";
        String selectedQuery = ddmQueryOptions.getSelectedItem().toString();
        DatabaseHelper data = new DatabaseHelper(this);
        ddmQueryOptions.setVisibility(View.GONE);


        if(selectedQuery.equals("Show All Usernames")){
            setQuerySpinnerResults("username");
            ddmQueryResults.setVisibility(View.VISIBLE);
        }
        else if(selectedQuery.equals("Show All Email Address")){
            setQuerySpinnerResults("email");
            ddmQueryResults.setVisibility(View.VISIBLE);
        }
        else if(selectedQuery.equals("Show All Members Names")){
            setQuerySpinnerResults("name");
            ddmQueryResults.setVisibility(View.VISIBLE);
        }
        else if(selectedQuery.equals("Show Total Members")){
            ddmQueryResults.setVisibility(View.GONE);
            String ttl = data.queryTotalMembers();
            Toast t = Toast.makeText(DashBoardActivity.this, ttl, Toast.LENGTH_LONG);
            t.show();
        }
        else{
            ddmQueryResults.setVisibility(View.GONE);
            Toast t = Toast.makeText(DashBoardActivity.this, msg, Toast.LENGTH_SHORT);
            t.show();
        }
    }

    public void onBackToMainClick(View v){
        //Main Menu view back to main button click event
        Intent signOutAction = getBaseContext().getPackageManager()
                .getLaunchIntentForPackage( getBaseContext().getPackageName());
        signOutAction.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(signOutAction);
    }

    public void onDisplayDocClick(View v){
        //Main Menu view documentation button click event
        docClick += 1;
        if(docClick == 1){
            displayDoc.setText(AppUtils.getMainActivityDoc());
        }
        else if(docClick == 2){
            displayDoc.setText(AppUtils.getLoginActivityDoc());
        }
        else if(docClick == 3){
            displayDoc.setText(AppUtils.getSignUpActivityDoc());
        }
        else if(docClick == 4){
            displayDoc.setText(AppUtils.getDashBoardActivityDoc());
        }
        else{docClick = 0;}

    }

    public void setThemeSwitchLogic(){
        swcThemeMenu.setChecked(false);
        swcThemeMenu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    String msg = "Now Displaying Theme Menu";
                    viewThemeMenu.setVisibility(View.VISIBLE);
                    viewQueryMenu.setVisibility(View.GONE);
                    viewMainMenu.setVisibility(View.GONE);
                    Toast t = Toast.makeText(DashBoardActivity.this, msg, Toast.LENGTH_SHORT);
                    t.show();
                    swcQueryMenu.setChecked(false);
                    swcMainMenu.setChecked(false);
                } else {
                    viewThemeMenu.setVisibility(View.GONE);
                }
            }
        });
    }

    public void setDefaultDashboardView(){
        viewThemeMenu.setVisibility(View.GONE);
        viewQueryMenu.setVisibility(View.GONE);
        viewMainMenu.setVisibility(View.GONE);
    }

    public void setThemeSpinner(){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.theme_names, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ddmTheme.setAdapter(adapter);
    }

    public void setQuerySpinnerOptions(){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.query_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ddmQueryOptions.setAdapter(adapter);

    }

    public void setQuerySpinnerResults(String type){
        ArrayAdapter<String> adapter;
        List<String> list;
        DatabaseHelper data = new DatabaseHelper(this);
        if(type.equals("name")){
            list = new ArrayList<String>();
            for(int i=0; i < data.getAllMembers().size(); i++ ){
                list.add(AppUtils.getName(data, i));
            }
            adapter = new ArrayAdapter<String>(getApplicationContext(),
                    android.R.layout.simple_spinner_item, list);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            ddmQueryResults.setAdapter(adapter);
        }
        else if(type.equals("email")){
            list = new ArrayList<String>();
            for(int i=0; i < data.getAllMembers().size(); i++ ){
                list.add(AppUtils.getEmail(data, i));
            }
            adapter = new ArrayAdapter<String>(getApplicationContext(),
                    android.R.layout.simple_spinner_item, list);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            ddmQueryResults.setAdapter(adapter);
        }
        else if(type.equals("username")){
            list = new ArrayList<String>();
            for(int i=0; i < data.getAllMembers().size(); i++ ){
                list.add(AppUtils.getUserName(data, i));
            }
            adapter = new ArrayAdapter<String>(getApplicationContext(),
                    android.R.layout.simple_spinner_item, list);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            ddmQueryResults.setAdapter(adapter);
        }
        else {
            //could throw error if needed
        }
    }

    public void setQuerySwitchLogic(){
        swcQueryMenu.setChecked(false);
        swcQueryMenu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    String msg = "Now Displaying Query Menu";
                    ddmQueryResults.setVisibility(View.GONE);
                    ddmQueryOptions.setVisibility(View.VISIBLE);
                    viewThemeMenu.setVisibility(View.GONE);
                    viewQueryMenu.setVisibility(View.VISIBLE);
                    viewMainMenu.setVisibility(View.GONE);
                    swcThemeMenu.setChecked(false);
                    swcMainMenu.setChecked(false);
                    Toast t = Toast.makeText(DashBoardActivity.this, msg, Toast.LENGTH_SHORT);
                    t.show();
                }
                else{
                    viewQueryMenu.setVisibility(View.GONE);
                }
            }
        });
    }

    public void setMainMenuSwitchLogic(){
        swcMainMenu.setChecked(false);
        swcMainMenu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    String msg = "Now Displaying Main Menu";
                    viewThemeMenu.setVisibility(View.GONE);
                    viewQueryMenu.setVisibility(View.GONE);
                    viewMainMenu.setVisibility(View.VISIBLE);
                    swcQueryMenu.setChecked(false);
                    swcThemeMenu.setChecked(false);
                    Toast t = Toast.makeText(DashBoardActivity.this, msg, Toast.LENGTH_SHORT);
                    t.show();
                }
                else{
                    viewMainMenu.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dash_board, menu);
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
