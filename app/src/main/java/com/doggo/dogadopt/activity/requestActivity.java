package com.doggo.dogadopt.activity;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.doggo.dogadopt.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class requestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_user);

        // DogName
        TextInputLayout textInputLayoutDogName = findViewById(R.id.TextInputLayoutDogName);
        TextInputEditText textInputEditTextDogName = textInputLayoutDogName.findViewById(R.id.asoName);
        // Disable the TextInputEditText
        textInputEditTextDogName.setEnabled(false);

        // FullName
        TextInputLayout textInputLayoutUserFullName = findViewById(R.id.TextInputLayoutUserFullName);
        TextInputEditText textInputEditTextUserFullName = textInputLayoutUserFullName.findViewById(R.id.fullNameUser);
        // Disable the TextInputEditText
        textInputEditTextUserFullName.setEnabled(false);
    }
}
