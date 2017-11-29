package com.example.hp1.audi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CarsInfo extends AppCompatActivity {
    ListView lvDetails;
    ImageView carImage;
    ArrayList<String> info = new ArrayList<String>();

    InputStream is = null;
    InputStreamReader in;
    BufferedReader br;

    //parameters for file reading used in both methods
    String temp = "", all = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cars_info);
        lvDetails = (ListView) findViewById(R.id.lvDetails);
        carImage = (ImageView) findViewById(R.id.carImage);

        Intent i = getIntent();
        int car = i.getIntExtra("car", 0);
        updateInfo(car);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, info);
        lvDetails.setAdapter(adapter);
    }

    private void updateInfo(int car) {
        if (car == 0) {
            readInfo(R.raw.a1);
            carImage.setImageResource(R.drawable.a1);
        }
        if (car == 1) {
            readInfo(R.raw.tt);
            carImage.setImageResource(R.drawable.tts);
        }
    }

    private String readInfo(int file) {
        try {
            temp = "";
            all = "";
            //4. open the file for reading
            is = getResources().openRawResource(file);
            //open a channel for file reading
            in = new InputStreamReader(is);
            br = new BufferedReader(in);
            //while end of file not reached
            //readline() reads one line at a time
            while ((temp = br.readLine()) != null) {
                info.add(temp);
                all += temp + "\n";//concatinate all lines to a string
            }
            is.close();//file close
            return all;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
