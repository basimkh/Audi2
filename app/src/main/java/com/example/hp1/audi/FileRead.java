package com.example.hp1.audi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileRead extends AppCompatActivity implements View.OnClickListener {
    InputStream is=null;
    InputStreamReader in;
    BufferedReader br;

    //parameters for file reading used in both methods
    String temp="", all="";
    int num;

    //general layout paramters
    TextView text;
    EditText write;
    Button btRead, btRead2, btWrite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_read);





        text=(TextView)findViewById(R.id.tvFileContent);
       // write = (EditText)findViewById(R.id.etText);

        //button for reading from a file in raw folder
        btRead=(Button)findViewById(R.id.btRead);
        btRead.setOnClickListener(this);

        //buttons for writing into hidden file and then reading it
        btWrite=(Button)findViewById(R.id.btWrite);
        btWrite.setOnClickListener(this);

        btRead2=(Button)findViewById(R.id.btRead2);
        btRead2.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        //reading from already existing file in raw
        if(v==btRead)
        {
            try{
                temp="";
                all="";
                //4. open the file for reading
                is=getResources().openRawResource(R.raw.myfile);
                //open a channel for file reading
                in= new InputStreamReader(is);
                br= new BufferedReader(in);
                //while end of file not reached
                //readline() reads one line at a time
                while((temp=br.readLine())!=null)
                    all+=temp+"\n";//concatinate all lines to a string
                text.setText(all);
                is.close();//file close
            }catch (FileNotFoundException e) {
                e.printStackTrace();
            }catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
