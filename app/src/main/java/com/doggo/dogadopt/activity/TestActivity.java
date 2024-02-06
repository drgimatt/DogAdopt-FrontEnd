package com.doggo.dogadopt.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.doggo.dogadopt.ListAdapter;
import com.doggo.dogadopt.LoadingDialog;
import com.doggo.dogadopt.model.Account;
import com.doggo.dogadopt.model.Dog;
import com.doggo.dogadopt.retrofit.CallBack;
import com.doggo.dogadopt.retrofit.QueryProcessor;
import com.doggo.dogadopt.LoadingDialog;
import com.doggo.dogadopt.R;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class TestActivity extends AppCompatActivity {

    ListView lView;
    ListAdapter lAdapter;
    QueryProcessor processor = new QueryProcessor();
    Account account;
    private ActionBarDrawerToggle toggle;
    private DrawerLayout layout;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_final);
        LoadingDialog progress = new LoadingDialog(TestActivity.this);
        progress.startLoadingAnimation();

//        ProgressDialog progress = new ProgressDialog(this);
//        progress.setMessage("Please wait...");
//        progress.setCancelable(false);
//        progress.show();
        Intent intent = getIntent();
        account = (Account) intent.getSerializableExtra("accountDetails");
        processor.DogReadAll();
        processor.setCbs(new CallBack() {
            @Override
            public void returnResult(Object obj) {
                List<Dog> dogList = (List<Dog>) obj;
                lView = (ListView) findViewById(R.id.dogList);
                lAdapter = new ListAdapter(TestActivity.this, dogList.toArray(new Dog[0]),account.getRole().replace("\"", ""),account.getMyId());
                lView.setAdapter(lAdapter);
                //progress.dismiss();
                progress.dismissAnimation();
            }
        });

        layout = findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(this, layout,R.string.nav_open,R.string.nav_close);

        layout.addDrawerListener(toggle);
        toggle.syncState();

        toolbar = findViewById(R.id.toolbar_mainmenu);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        NavigationView navigationView = findViewById(R.id.nav_view);
        View header = navigationView.getHeaderView(0);
        LinearLayout sideNavLayout = (LinearLayout) header.findViewById(R.id.nav_header);
        TextView fullname = sideNavLayout.findViewById(R.id.fullname_menuLabel);
        TextView usertype = sideNavLayout.findViewById(R.id.usertype_menuLabel);
        fullname.setText(account.getFirstName() + " " + account.getLastName());
        usertype.setText(account.getRole());

        Menu menu = navigationView.getMenu();
        if (account.getRole().equals("ADMIN")){
            menu.add("View Requests");
            menu.add("Add a Dog");
            menu.add("Logout");
        } else {
            menu.add("View Requests");
            menu.add("Logout");
        }


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (account.getRole().equals("ADMIN")){
                    if (item.getTitle().equals("View Requests")){
                        Toast.makeText(TestActivity.this,"Assume that there is a view request function", Toast.LENGTH_SHORT).show();
                    }
                    else if(item.getTitle().equals("Add a Dog")){
                        Intent i = new Intent(getApplicationContext(), addActivity.class);
                        startActivity(i);
                        layout.closeDrawers();
                    }
                    else if (item.getTitle().equals("Logout")){
                        Toast.makeText(TestActivity.this,"Assume that there is a logout function", Toast.LENGTH_SHORT).show();
                    }else{

                    }
                }
                else{
                    if (item.getTitle().equals("View Requests")){
                        Toast.makeText(TestActivity.this,"Assume that there is a view request function", Toast.LENGTH_SHORT).show();
                    }
                    else if (item.getTitle().equals("Logout")){
                        Toast.makeText(TestActivity.this,"Assume that there is a logout function", Toast.LENGTH_SHORT).show();
                    }else{

                    }
                }
                return false;
            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }




}
