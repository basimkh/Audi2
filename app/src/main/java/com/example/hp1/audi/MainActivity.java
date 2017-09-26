package com.example.hp1.audi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button btcars,btlife,bttechno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btcars=(Button) findViewById(R.id.cars);
        btlife=(Button) findViewById(R.id.lifestyle);
        bttechno=(Button) findViewById(R.id.technology);
        btcars.setOnClickListener(this);
        btlife.setOnClickListener(this);
        bttechno.setOnClickListener(this);

    }


    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId())
        {
            case R.id.t1:
                Toast.makeText(getBaseContext(),"Hi", Toast.LENGTH_LONG).show();
                break;


        }

        return true;
    }
    public void onClick(View v) {
        if(v==btcars){
            Intent i = new Intent(this,Cars.class);
            startActivity(i);
        }
        if(v==btlife){
            Intent i = new Intent(this,Lifestyle.class);
            startActivity(i);
        }
        if(v==bttechno){
            Intent i = new Intent(this,Technology.class);
            startActivity(i);
        }

    }
}
