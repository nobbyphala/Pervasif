package com.silentsimfoni.sensorlist;

import android.database.Cursor;
import android.os.Environment;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ListSensorData extends AppCompatActivity {

    private ListView lv;
    private Database db;
    private Cursor cur;
    private dataAccelAdapter listAccelAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_sensor_data);

        lv = (ListView) findViewById(R.id.lv);
        db = new Database(this);

        ArrayList<dataAccel> listdataAccel = new ArrayList<dataAccel>();
        listAccelAdapter = new dataAccelAdapter(this,0,listdataAccel);

        lv.setAdapter(listAccelAdapter);

        updateList();
    }

    public void updateList()
    {
        cur = db.ambilData();

        if(cur.getCount()>0)
        {

            while(cur.moveToNext())
            {
                dataAccel data = new dataAccel(cur.getFloat(0),cur.getFloat(1),cur.getFloat(2),cur.getInt(3));
                listAccelAdapter.add(data);
            }
        }
        else
            listAccelAdapter.clear();
    }

    public void simpanCSV() throws IOException {
        String basedir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
        String filePath = basedir + File.separator + "dataAccel.csv";

        //File f = new File(filePath);

        CSVWriter writer = new CSVWriter(new FileWriter(filePath));
        String[] judul = {"No","x_axis","y_axis","z_axis","aktivitas"};

        writer.writeNext(judul);

        int counter=1;
        if(cur.getCount() >0)
        {
            cur .moveToFirst();

            String[] tmp1 = {String.valueOf(counter),cur.getString(0),cur.getString(1),cur.getString(2),cur.getString(3)};
            writer.writeNext(tmp1);

            while(cur.moveToNext())
            {
                counter+=1;
                String[] tmp2 = {String.valueOf(counter),cur.getString(0),cur.getString(1),cur.getString(2),cur.getString(3)};
                writer.writeNext(tmp2);
            }
            writer.close();

            Toast.makeText(this,"Data berhasil disimpan",Toast.LENGTH_LONG).show();

        }
    }

    public void hapusButtonClick(View view)
    {



        db.hapusData();
        updateList();
    }

    public void simpanCSVButtonClick(View view)
    {
        try {
            simpanCSV();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
