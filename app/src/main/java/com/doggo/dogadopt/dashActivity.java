package com.doggo.dogadopt;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.escandor.dogadopt.R;

public class dashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_admin);

        Button buttonOpenActivity = findViewById(R.id.login_button_dash);

        buttonOpenActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Set content view to the new XML layout
                setContentView(R.layout.activity_startup);
            }
        });
    }
}

