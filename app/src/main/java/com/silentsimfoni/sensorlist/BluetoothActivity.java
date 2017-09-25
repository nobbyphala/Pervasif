package com.silentsimfoni.sensorlist;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class BluetoothActivity extends AppCompatActivity {

    private BluetoothAdapter BA;
    private ListView lv;
    private BroadcastReceiver BR;
    private ArrayList deviceFounded;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);

        BA = BluetoothAdapter.getDefaultAdapter();
        lv = (ListView) findViewById(R.id.lView);
        deviceFounded = new ArrayList();

    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        unregisterReceiver(BR);

    }

    private void reqPermission()
    {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH) != PackageManager.PERMISSION_GRANTED)
        {
            Toast.makeText(getBaseContext(), "Request Permission",Toast.LENGTH_LONG).show();
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.BLUETOOTH},1);


        }


    }

    public void reqVisible()
    {
        Intent getVisible = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        startActivityForResult(getVisible, 0);
    }

    public void btnSearchOnClick(View view)
    {
        reqPermission();

        if(!BA.isEnabled())
        {
            Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(turnOn, 0);

            reqVisible();
        }

        BR = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();

                if(BluetoothDevice.ACTION_FOUND.equals(action))
                {

                    BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

                    deviceFounded.add(device.getName());

                    final ArrayAdapter adapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1,deviceFounded);
                    lv.setAdapter(adapter);

                }
            }
        };

        BA.startDiscovery();

        IntentFilter discoverDevice = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(BR,discoverDevice);


    }
}

