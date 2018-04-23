package com.example.hp1.audi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Hp1 on 28/09/2017.
 */

public class CustomAdapter extends ArrayAdapter<BookService> {
    ///
    private int resource;


    public CustomAdapter(Context context, int resource, List<BookService> objects) {
        super(context, resource, objects);
        this.resource = resource;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater imageInflater = LayoutInflater.from(getContext());
        View cuView = imageInflater.inflate(resource, parent, false );

        BookService bookedServices= getItem(position);
        TextView carNo = (TextView) cuView.findViewById(R.id.CN);
        TextView Date = (TextView) cuView.findViewById(R.id.Date);
        TextView Time = (TextView) cuView.findViewById(R.id.Time);

        carNo.setText(bookedServices.getCarnum());
        Date.setText(bookedServices.getDate());
        Time.setText(bookedServices.getTime());

        return cuView;
    }
}
