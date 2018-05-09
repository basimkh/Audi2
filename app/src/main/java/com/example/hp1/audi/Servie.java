package com.example.hp1.audi;

import android.app.DatePickerDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import static com.example.hp1.audi.R.id.time;
import static com.example.hp1.audi.R.id.tvTime;


public class Servie extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private TextView mDisplayDate, DisplayTime;
    private TimePickerDialog.OnTimeSetListener TimeP;
    private DatePickerDialog.OnDateSetListener mDatesetListener;
    Button bt1 ;
    DBHandling myDB;
    String time,date,CarNum;
    TextView tv1;

    NotificationCompat.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servie);

        bt1=(Button) findViewById(R.id.button);
        bt1.setOnClickListener(this);

        mDisplayDate = (TextView) findViewById(R.id.tvDate);//givethe textview an id
        mDisplayDate.setOnClickListener(this);

        DisplayTime = (TextView) findViewById(R.id.tvTime);
        DisplayTime.setOnClickListener(this);//make the textview clickable

         myDB=new DBHandling(this);

        mDatesetListener = new DatePickerDialog.OnDateSetListener() {



            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {//so the user can pick te date on the datePicker dialog
                month += 1;//becuase the month starts at zero so we increase it by 1
                int selectDay = day;//make the selected day
                int selectYear = year;//make the selected year
                int selectMonth = month;//make the selected month
                 date="Date:" + day + "/" + month + "/" + year;
                mDisplayDate.setText(date);



            }
        };


        TimeP = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                int selectHour = hourOfDay;//make the selected day
                int selectMinute = minute;//make the selected year
                 time="Time:"+selectHour+":"+selectMinute;
                DisplayTime.setText(time);

            }
        };

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
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
        if(v==DisplayTime)
        {
            Calendar cal=Calendar.getInstance();
            int hour=cal.get(Calendar.HOUR_OF_DAY);
            int minute=cal.get(Calendar.MINUTE);
            TimePickerDialog dialog=new TimePickerDialog(this,TimeP,hour,minute,true);
            dialog.getWindow();
            dialog.show();
        }
        if(bt1==v){
            setNotification();
            BookService bookService = new BookService("Haifa",CarNum,time,date);
            myDB.insertData(bookService);
            NotificationManager manager = (NotificationManager) getSystemService(getApplicationContext().NOTIFICATION_SERVICE);
            manager.notify(0, builder.build());
            Intent i = new Intent(this, BookedServices.class);
            startActivity(i);

        }
    }
    public void setNotification() {

        //create builder object
        builder = new NotificationCompat.Builder(this);

        //customize the builder
        builder.setSmallIcon(R.drawable.papar);
        builder.setContentTitle("SERVICE");
        builder.setContentText("It was saved successfully");

        //
        Intent bIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, bIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);



    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Servie Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

}