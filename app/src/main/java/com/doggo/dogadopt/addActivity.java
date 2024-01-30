package com.doggo.dogadopt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.doggo.dogadopt.model.Dog;
import com.doggo.dogadopt.retrofit.RequestProcessor;
import com.escandor.dogadopt.R;
import com.google.android.material.textfield.TextInputEditText;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class addActivity extends AppCompatActivity {

    private ImageView dogPicPreview;
    private Button dogChoosePhoto;
    private EditText DName;
    private EditText DBreed;
    private EditText DAge;
    private Spinner DStatus;
    private Spinner DGender;
    private EditText dogDOAEditText;
    private TextInputEditText DogPersonal;
    private Button addDog_button;
    private Calendar calendar;
    int SELECT_PICTURE = 200;
    RequestProcessor processor = new RequestProcessor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        dogDOAEditText = findViewById(R.id.dogDOA);
        DName = findViewById(R.id.dogName);
        DGender = findViewById(R.id.dogGender);
        dogChoosePhoto = (Button) findViewById(R.id.dogChoosePhoto);
        dogPicPreview = findViewById(R.id.dogPhoto);
        DAge = findViewById(R.id.dogAge);
        DBreed = findViewById(R.id.dogBreed);
        DogPersonal = findViewById(R.id.dogPersonality);
        addDog_button = (Button) findViewById(R.id.addDog_button);
        DStatus = findViewById(R.id.dogStatus);
        calendar = Calendar.getInstance();

        dogChoosePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("HELLO", "Register Button Clicked");
                imageChooser();
            }
        });
        addDog_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                processor.DogAdd((byte[]) null,DName.getText().toString(),DBreed.getText().toString(),DAge.getText().toString(),dogDOAEditText.getText().toString(),DogPersonal.getText().toString(),DStatus.getSelectedItem().toString(),DGender.getSelectedItem().toString());

            }
        });


        // Optionally, set an initial date in the EditText
        updateDateInView();


    }

    public void showDatePickerDialog(View view) {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDay) {
                        calendar.set(Calendar.YEAR, selectedYear);
                        calendar.set(Calendar.MONTH, selectedMonth);
                        calendar.set(Calendar.DAY_OF_MONTH, selectedDay);

                        updateDateInView();
                    }
                },
                year, month, dayOfMonth);

        datePickerDialog.show();
    }

    private void updateDateInView() {
        String myFormat = "MM/dd/yyyy"; // Choose the format you desire
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        dogDOAEditText.setText(sdf.format(calendar.getTime()));
    }

    private void imageChooser(){

        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            if (requestCode == SELECT_PICTURE){
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri){
                    dogPicPreview.setImageURI(selectedImageUri);
                }
            }
        }

    }


    private void initializeButtons(){



    }
}