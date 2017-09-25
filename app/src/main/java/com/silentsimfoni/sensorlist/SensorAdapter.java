package com.silentsimfoni.sensorlist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.io.StringBufferInputStream;
import java.util.List;

/**
 * Created by nobbyphala on 2/28/17.
 */

public class SensorAdapter extends ArrayAdapter<SensList> {

    public SensorAdapter(Context context, int resource, List<SensList> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        SensList dtSens= getItem(position);

        if(convertView == null)
        {
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.sens_layout,parent,false);

        }

        TextView nama = (TextView) convertView.findViewById(R.id.nama);
        TextView sens_type = (TextView) convertView.findViewById(R.id.txtType);

        nama.setText(dtSens.getSensorName());
        sens_type.setText("Type: "+ String.valueOf(dtSens.getSensorType()));

        return convertView;
    }
}
