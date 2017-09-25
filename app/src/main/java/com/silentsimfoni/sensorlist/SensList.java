package com.silentsimfoni.sensorlist;

/**
 * Created by nobbyphala on 2/28/17.
 */

public class SensList {
    private String sensorName;

    public SensList(String sensorName, int sensorType) {
        this.sensorName = sensorName;
        this.sensorType = sensorType;
    }

    public int getSensorType() {
        return sensorType;
    }

    public void setSensorType(int sensorType) {
        this.sensorType = sensorType;
    }

    private int sensorType;

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }
}
