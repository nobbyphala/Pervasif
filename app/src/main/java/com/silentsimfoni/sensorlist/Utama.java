package com.silentsimfoni.sensorlist;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Utama extends AppCompatActivity {

    private SensorManager sm;
    private List<Sensor> sensor_list;
    private ListView lv;
    private SensorAdapter sensAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utama);

        lv = (ListView) findViewById(R.id.listview);
        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        ArrayList<SensList> list_item=new ArrayList<SensList>();

        sensAdapter=new SensorAdapter(this,0,list_item);

        ArrayAdapter<String> arr = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
        sensor_list = sm.getSensorList(Sensor.TYPE_ALL);

        for (Sensor sens:sensor_list
             ) {
            SensList tmp = new SensList(sens.getName(),sens.getType());
            sensAdapter.add(tmp);
        }

        lv.setAdapter(sensAdapter);

    }

    public void checkClick(View view)
    {
        Intent intent = new Intent(this,SensorReport.class);
        startActivity(intent);
    }
}
