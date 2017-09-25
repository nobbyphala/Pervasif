package com.silentsimfoni.sensorlist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by nobbyphala on 3/22/17.
 */

public class Database {
    private SQLiteOpenHelper Opendb;
    private SQLiteDatabase dataDB;


    public Database(Context context) {
        Opendb = new SQLiteOpenHelper(context,"db.lite",null,1) {
            @Override
            public void onCreate(SQLiteDatabase db) {

            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            }
        };

        dataDB = Opendb.getWritableDatabase();

        dataDB.execSQL(("create table if not exists sensorLog(x_axis FLOAT, y_axis FLOAT, z_axis FLOAT, aktivitas INT);"));
    }

    public void simpan(float x_axis, float y_axis, float z_axis, int aktivitas)
    {
        ContentValues sidata = new ContentValues();

        sidata.put("x_axis",x_axis);
        sidata.put("y_axis",y_axis);
        sidata.put("z_axis",z_axis);
        sidata.put("aktivitas",aktivitas);

        dataDB.insert("sensorLog",null,sidata);
    }

    public Cursor ambilData()
    {
        Cursor cur = dataDB.rawQuery("SELECT * FROM sensorLog",null);

        return cur;
    }

    public void hapusData()
    {
        dataDB.execSQL("DELETE FROM sensorLog;");
    }
}
