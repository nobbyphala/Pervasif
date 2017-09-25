package com.silentsimfoni.sensorlist;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by nobbyphala on 3/22/17.
 */

public class dataAccelAdapter extends ArrayAdapter<dataAccel> {
    public dataAccelAdapter(Context context, int resource, List<dataAccel> objects) {
        super(context, resource, objects);

    }

    public View getView(int position, View ConvertView, ViewGroup parent)
    {
        dataAccel data = getItem(position);

        if(ConvertView == null)
        {
            ConvertView = LayoutInflater.from(getContext()).inflate(R.layout.list_dataaccel,parent,false);
        }

        TextView txtNo = (TextView) ConvertView.findViewById(R.id.txtNo);
        TextView txtX = (TextView) ConvertView.findViewById(R.id.txtX);
        TextView txtY = (TextView) ConvertView.findViewById(R.id.txtY);
        TextView txtZ = (TextView) ConvertView.findViewById(R.id.txtZ);
        TextView txtA = (TextView) ConvertView.findViewById(R.id.txtA);

        txtNo.setText(String.valueOf(position));
        txtX.setText(String.valueOf(data.getX_axis()));
        txtY.setText(String.valueOf(data.getY_axis()));
        txtZ.setText(String.valueOf(data.getZ_axis()));
        txtA.setText(String.valueOf(data.getActivity_type()));

        return ConvertView;
    }
}
