package com.example.hp1.audi;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;


public class Servie extends AppCompatActivity implements View.OnClickListener {
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDatesetListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servie);
        mDisplayDate = (TextView) findViewById(R.id.tv2);//givethe textview an id
        mDisplayDate.setOnClickListener(this);//make the textview clickable
        mDatesetListener = new DatePickerDialog.OnDateSetListener( ) {

            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {//so the user can pick te date on the datePicker dialog
                month += 1;//becuase the month starts at zero so we increase it by 1
                mDisplayDate.setText("Date:" + day + "/" + month + "/" + year);//display the chosen date on textview
                int selectDay = day;//make the selected day
                int selectYear = year;//make the selected year
                int selectMonth = month;//make the selected month
            }
        };
    }

    @Override
    public void onClick(View v) {
    if (v == mDisplayDate) {//if the user clicked on the textview mDisplayDate
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);//set the year as the current year
        int month = cal.get(Calendar.MONTH);//set the month as the current month
        int day = cal.get(Calendar.DAY_OF_MONTH);//set the day as the current day
        DatePickerDialog dialog = new DatePickerDialog(this, android.R.style.Theme_Holo_Light_DarkActionBar, mDatesetListener, year, month, day);//set the datePicker dialog appearence
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//set the background color
        dialog.show();//show the datePicker dialog
    }



    }
}