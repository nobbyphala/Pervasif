package com.silentsimfoni.sensorlist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.silentsimfoni.sensorlist.R;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }


    public void btnBluetoothClick(View view)
    {
        startActivity(new Intent(this,BluetoothActivity.class));
    }

    public void btnSensorClick(View view)
    {
        startActivity(new Intent(this, Utama.class));
    }
}
