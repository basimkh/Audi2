package com.example.hp1.audi;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, DialogInterface.OnClickListener {
    Button btcars, btlife, bttechno ,btnCamera, btCA;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btcars = (Button) findViewById(R.id.cars);
        btlife = (Button) findViewById(R.id.Engine);
        bttechno = (Button) findViewById(R.id.Service);
        btcars.setOnClickListener(this);
        btlife.setOnClickListener(this);
        bttechno.setOnClickListener(this);

        btnCamera=(Button) findViewById(R.id.btnCamera);
        btnCamera.setOnClickListener(this);






    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem About) {
        switch (About.getItemId()) {
            case R.id.t1:
                Intent j = new Intent(this, About.class);
                startActivity(j);
                break;


        }

        return true;
    }

    public void onClick(View v) {
        if (v == btcars) {
            Intent i = new Intent(this, Cars.class);
            startActivity(i);
        }
        if (v == btlife) {
            Intent i = new Intent(this, Engine.class);
            startActivity(i);
        }
        if (v == bttechno) {
            Intent i = new Intent(this, Servie.class);
            startActivity(i);
        }
        if (v==btnCamera) {
            Intent i = new Intent(this, CameraActivity.class);
            startActivity(i);
        }

    }



    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MainActivity.this.finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if (which == dialog.BUTTON_POSITIVE) {
            super.onBackPressed();
            dialog.cancel();

            if (which == dialog.BUTTON_NEGATIVE) {
                dialog.cancel();
            }
        }
    }


}