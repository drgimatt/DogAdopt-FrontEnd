package com.doggo.dogadopt.activity;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.escandor.dogadopt.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class requstActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_user);


        TextInputLayout textInputLayout = findViewById(R.id.TextInputLayoutDogName);
        TextInputEditText textInputEditText = textInputLayout.findViewById(R.id.asoName);
        // Disable the TextInputEditText
        textInputEditText.setEnabled(false);

        TextInputLayout textInputLayoutUser = findViewById(R.id.TextInputLayoutDogName);
        TextInputEditText textInputEditTextUser = textInputLayout.findViewById(R.id.fullNameUser);
        // Disable the TextInputEditText
        textInputEditText.setEnabled(false);
    }
}
