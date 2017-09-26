package com.example.hp1.audi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class CarsInfo extends AppCompatActivity {
    TextView carInfo;
    ImageView carImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cars_info);
        carInfo = (TextView) findViewById(R.id.tvCar);
        carImage = (ImageView) findViewById(R.id.carImage);

        Intent i = getIntent();
        int car = i.getIntExtra("car", 0);
        updateInfo(car);
    }

    private void updateInfo(int car) {


        if (car == 0)

        {
            carInfo.setText("Audi A1 2018 Price : 25k");
            carImage.setImageResource(R.drawable.a1);


        }
    }
}
