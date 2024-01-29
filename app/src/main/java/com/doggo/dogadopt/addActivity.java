package com.doggo.dogadopt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.escandor.dogadopt.R;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class addActivity extends AppCompatActivity {

    private EditText dogBirthDateEditText;
    private EditText DogName;
    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        dogBirthDateEditText = findViewById(R.id.dogDOA);
        DogName = findViewById(R.id.dogName);
        calendar = Calendar.getInstance();

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

        dogBirthDateEditText.setText(sdf.format(calendar.getTime()));
    }
}