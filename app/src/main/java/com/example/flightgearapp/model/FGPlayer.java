package com.example.flightgearapp.model;

import android.util.Log;
import android.widget.SeekBar;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FGPlayer {
    private Socket socket;
    private PrintWriter out;
    private ExecutorService executor; // = Executors.newSingleThreadExecutor();
    private OutputStream outStream;

    public FGPlayer(){
        this.executor = Executors.newSingleThreadExecutor();
    }

    public void connectToSocket(String ip, int port) {
        executor.execute( new Runnable() {
            @Override
            public void run() {
                try {
                   // socket = new Socket(ip, port);
                    //InetAddress serverAdds = InetAddress.getByName(ip);
                    //socket = new Socket();
                    //Wait until the connection are done.
                    //socket.connect(new InetSocketAddress(serverAdds, port),15*1000);
                    //outStream = socket.getOutputStream();

                    //Log.i("TAG","in run");

                    socket = new Socket(ip, port);

                    Log.i("TAG","Before Connect");
                    out = new PrintWriter(socket.getOutputStream(), true);
                    Log.i("TAG","After Connect");
                } catch (IOException e) {
                    Log.i("TAG","in catch FGP");
                    e.printStackTrace();
                }
            }
        });
    }

    public void setThrottle(double data){
        executor.execute(new Runnable() {
            @Override
            public void run() {

                out.print("set /controls/engines/current-engine/throttle " + data + "\r\n");
                //Log.i("TAG","throttle " + data + "\n");
                out.flush();
            }
        });
    }

    public void setAileron(double data){
        executor.execute( new Runnable() {
            @Override
            public void run() {
                out.print("set /controls/flight/aileron "+data+"\r\n");
                //Log.i("TAG","aileron" + data + "\n");
                out.flush();
            }
        });
    }

    public void setRudder(double data){
        executor.execute( new Runnable() {
            @Override
            public void run() {
                out.print("set /controls/flight/rudder "+data+"\r\n");
                //Log.i("TAG","rudder" + data + "\n");

                out.flush();
            }
        });

    }

    public void setElevator(double data){
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
                setThrottle(result);
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
                setRudder(result);
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
