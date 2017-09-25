package com.silentsimfoni.sensorlist;

/**
 * Created by nobbyphala on 3/22/17.
 */

public class dataAccel {
    private float x_axis;
    private float y_axis;
    private float z_axis;

    public int getActivity_type() {
        return activity_type;
    }

    public void setActivity_type(int activity_type) {
        this.activity_type = activity_type;
    }

    private int activity_type;

    public dataAccel(float x_axis, float y_axis, float z_axis,int activity_type) {
        this.x_axis = x_axis;
        this.y_axis = y_axis;
        this.z_axis = z_axis;
        this.activity_type = activity_type;
    }

    public float getX_axis() {
        return x_axis;
    }

    public void setX_axis(float x_axis) {
        this.x_axis = x_axis;
    }

    public float getY_axis() {
        return y_axis;
    }

    public void setY_axis(float y_axis) {
        this.y_axis = y_axis;
    }

    public float getZ_axis() {
        return z_axis;
    }

    public void setZ_axis(float z_axis) {
        this.z_axis = z_axis;
    }
}
