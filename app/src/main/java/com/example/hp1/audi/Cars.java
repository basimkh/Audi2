package com.example.hp1.audi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Cars extends AppCompatActivity implements AdapterView.OnItemClickListener{
    ListView listView ;
    ArrayAdapter<String> adapter;
    ArrayList<String> cars = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cars);
        cars.add("A1");
        cars.add("TT");
        cars.add("A3");
        cars.add("A4");
        cars.add("A5");
        cars.add("A6");
        cars.add("A7");
        cars.add("A8");
        cars.add("Q2");
        cars.add("Q3");
        cars.add("Q5");
        cars.add("Q7");
        listView = (ListView) findViewById(R.id.LvCars);
        adapter=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,cars);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);


    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent i = new Intent(this, CarsInfo.class);
        i.putExtra("car",position);
        startActivity(i);
    }



}

