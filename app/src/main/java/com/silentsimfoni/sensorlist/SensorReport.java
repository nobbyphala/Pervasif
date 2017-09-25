package com.silentsimfoni.sensorlist;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.StrictMode;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Console;
import java.lang.reflect.Array;

public class SensorReport extends AppCompatActivity {

    private float lastX,lastY,lastZ;
    private float deltaX = 0;
    private float deltaY = 0;
    private float deltaZ = 0;
    private TextView xAxis;
    private TextView yAxis;
    private TextView zAxis;
    private SensorManager sm;
    private SensorEventListener listener;
    private Sensor sensor;
    private int counter = 0;
    private int ccounter = 0;
    private float x_rata=0;
    private float y_rata=0;
    private float z_rata=0;
    private float cx_rata=0;
    private float cy_rata=0;
    private float cz_rata=0;
    private int activity_type=0;
    private boolean simpan_prox_flag=false;
    private boolean simpan_data_flag=false;
    //private KoneksiServer koneksiserver;
    private Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_report);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //koneksiserver = new KoneksiServer("192.168.0.12",10000);

        xAxis = (TextView) findViewById(R.id.xAxis);
        yAxis = (TextView) findViewById(R.id.yAxis);
        zAxis = (TextView) findViewById(R.id.zAxis);

        sm = (SensorManager) this.getSystemService(SENSOR_SERVICE);
        //sensor = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        //sm.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL);
        listener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                sensor = event.sensor;

                if(sensor.getType() == Sensor.TYPE_PROXIMITY)
                {
                    if(event.values[0]<5)
                    {
                        simpan_prox_flag=true;
                    }
                    else
                    {
                        simpan_prox_flag=false;
                    }
                }
                else
                if(sensor.getType() == Sensor.TYPE_ACCELEROMETER)
                {
                    cleanDisplay();
                    displayCurrentValue();

                    deltaX = Math.abs(lastX - event.values[0]);
                    deltaY = Math.abs(lastY - event.values[1]);
                    deltaZ = Math.abs(lastZ - event.values[2]);

                    //Menghindari noise
                    if(deltaX < 1)
                        deltaX=0;

                    if(deltaY < 1)
                        deltaY=0;

                    if(deltaZ < 1)
                        deltaZ=0;

                    lastX = event.values[0];
                    lastY = event.values[1];
                    lastZ = event.values[2];

                    if(simpan_prox_flag && simpan_data_flag)
                    {
                        if(counter<10)
                        {
                            x_rata+=lastX;
                            y_rata+=lastY;
                            z_rata+=lastZ;
                            counter++;
                        }
                        else
                        {
                            db.simpan(x_rata/10,y_rata/10,z_rata/10,activity_type);
                            x_rata=0;
                            y_rata=0;
                            z_rata=0;
                            counter=0;
                        }
                    }

                    if(ccounter<10)
                    {
                        cx_rata+=lastX;
                        cy_rata+=lastY;
                        cz_rata+=lastZ;
                        ccounter++;
                    }
                    else
                    {
                        //koneksiserver.sendM(String.valueOf(cx_rata/10)+";"+String.valueOf(cy_rata/10)+";"+String.valueOf(cz_rata/10));
                        cx_rata=0;
                        cy_rata=0;
                        cz_rata=0;
                        ccounter=0;
                    }

                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };

        sm.registerListener(listener, sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        sm.registerListener(listener, sm.getDefaultSensor(Sensor.TYPE_PROXIMITY), SensorManager.SENSOR_DELAY_NORMAL);
        db = new Database(this);
    }

    private void cleanDisplay()
    {
        xAxis.setText("0.0");
        yAxis.setText("0.0");
        zAxis.setText("0.0");
    }

    private void displayCurrentValue()
    {
        xAxis.setText(Float.toString(deltaX));
        yAxis.setText(Float.toString(deltaY));
        zAxis.setText(Float.toString(deltaZ));
    }


    public void lihatDataClick(View view)
    {
        Intent intent = new Intent(this, ListSensorData.class);
        startActivity(intent);
    }

    public void berdiriClick(View view)
    {
        Toast.makeText(this,"Terpencet",Toast.LENGTH_LONG).show();
        activity_type=1;
        simpan_data_flag=true;
    }

    public void berjalanClick(View view)
    {
        activity_type=2;
        simpan_data_flag=true;
    }

    public void berlariClick(View view)
    {
        activity_type=3;
        simpan_data_flag=true;
    }

    public void stopClick(View view)
    {
        simpan_data_flag=false;
        activity_type=0;
        counter=0;
    }
}
