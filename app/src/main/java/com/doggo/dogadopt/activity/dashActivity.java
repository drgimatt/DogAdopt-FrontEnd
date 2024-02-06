package com.doggo.dogadopt.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
import com.doggo.dogadopt.R;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class dashActivity extends AppCompatActivity {

    ListView lView;
    ListAdapter lAdapter;
    QueryProcessor processor = new QueryProcessor();
    Account account;
    private ActionBarDrawerToggle toggle;
    private DrawerLayout layout;
    private Toolbar toolbar;

    @Override
    protected void onRestart() {
        super.onRestart();
        LoadingDialog progress = new LoadingDialog(dashActivity.this);
        progress.startLoadingAnimation();
        processor.DogReadAll();
        processor.setCbs(new CallBack() {
            @Override
            public void returnResult(Object obj) {
                List<Dog> dogList = (List<Dog>) obj;
                lView = (ListView) findViewById(R.id.dogList);
                lAdapter = new ListAdapter(dashActivity.this, dogList.toArray(new Dog[0]),account.getRole().replace("\"", ""),account.getMyId());
                lView.setAdapter(lAdapter);
                progress.dismissAnimation();
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash);
        LoadingDialog progress = new LoadingDialog(dashActivity.this);
        progress.startLoadingAnimation();

        Intent intent = getIntent();
        account = (Account) intent.getSerializableExtra("accountDetails");
        processor.DogReadAll();
        processor.setCbs(new CallBack() {
            @Override
            public void returnResult(Object obj) {
                List<Dog> dogList = (List<Dog>) obj;
                lView = (ListView) findViewById(R.id.dogList);
                lAdapter = new ListAdapter(dashActivity.this, dogList.toArray(new Dog[0]),account.getRole().replace("\"", ""),account.getMyId());
                lView.setAdapter(lAdapter);
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
            menu.add("FAQ");
            menu.add("Logout");
        } else {
            menu.add("View Requests");
            menu.add("FAQ");
            menu.add("Logout");
        }


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    if (item.getTitle().equals("View Requests")){
                        Toast.makeText(dashActivity.this,"Assume that there is a view request function", Toast.LENGTH_SHORT).show();
                    }
                    else if(item.getTitle().equals("Add a Dog")){
                        layout.closeDrawers();
                        Intent i = new Intent(getApplicationContext(), addActivity.class);
                        startActivity(i);
                    }
                    else if (item.getTitle().equals("Logout")){
                        triggerLogout();
                    }
                    else if (item.getTitle().equals("FAQ")){
                        layout.closeDrawers();
                        Intent i = new Intent(getApplicationContext(), faqActivity.class);
                        startActivity(i);
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

    public void triggerLogout(){
        AlertDialog.Builder frameBuilder = new AlertDialog.Builder(this);
            frameBuilder.setCancelable(true);
            frameBuilder.setTitle("Logout");
            frameBuilder.setMessage("Do you want to logout?");
            frameBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent i = new Intent(getApplicationContext(), startupActivity.class);
                    startActivity(i);
                    finish();
                }
            });
            frameBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
        AlertDialog dialog = frameBuilder.create();
        dialog.show();
        layout.closeDrawers();
}

}
