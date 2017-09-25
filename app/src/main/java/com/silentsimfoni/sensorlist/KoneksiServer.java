package com.silentsimfoni.sensorlist;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * Created by nobbyphala on 4/19/17.
 */

public class KoneksiServer {
    private Socket s;
    private String ip;
    private int port;
    private BufferedWriter bout;

    public KoneksiServer(String ip, int port) {
        this.ip = ip;
        this.port = port;

        try {
            s = new Socket(this.ip,this.port);
            this.bout = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendM(String msg)
    {
        try {
            this.bout.write(msg);
            this.bout.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
