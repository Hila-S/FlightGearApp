package com.example.flightgearapp.model;

import android.util.Log;
import android.widget.SeekBar;

import java.io.IOException;
//import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FGPlayer {
    private Socket mySocket;
    private PrintWriter out;
    private ExecutorService executor;
    //private OutputStream outStream;

    public FGPlayer(){
        this.executor = Executors.newSingleThreadExecutor();
    }

    public void connectToSocket(String ip, int port) {
        executor.execute(() -> {
            try {
                mySocket = new Socket(ip, port);
                Log.i("TAG","Before Connect");
                out = new PrintWriter(mySocket.getOutputStream(), true);
                Log.i("TAG","After Connect");
            } catch (IOException e) {
                Log.i("TAG","in catch FGP");
                e.printStackTrace();
            }
        });
    }

    public void setDataThrottle(double data){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                out.print("set /controls/engines/current-engine/throttle " + data + "\r\n");
                //Log.i("TAG","throttle " + data + "\n");
                out.flush();
            }
        });
    }

    public void setDataAileron(double data){
        executor.execute( new Runnable() {
            @Override
            public void run() {
                out.print("set /controls/flight/aileron "+data+"\r\n");
                //Log.i("TAG","aileron" + data + "\n");
                out.flush();
            }
        });
    }

    public void setDataRudder(double data){
        executor.execute( new Runnable() {
            @Override
            public void run() {
                out.print("set /controls/flight/rudder "+data+"\r\n");
                //Log.i("TAG","rudder" + data + "\n");

                out.flush();
            }
        });

    }

    public void setDataElevator(double data){
        executor.execute( new Runnable() {
            @Override
            public void run() {
                out.print("set /controls/flight/elevator "+data+"\r\n");
                //Log.i("TAG","elevator" + data + "\n");
                out.flush();
            }
        });
    }

    public void throttleSettings(SeekBar throttle){
        throttle.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                double result = (double)progress/100;
                setDataThrottle(result);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void rudderSettings(SeekBar rudder){
        rudder.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                double result = (double)(progress-100)/100;
                setDataRudder(result);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


}
