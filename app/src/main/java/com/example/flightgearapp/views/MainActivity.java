package com.example.flightgearapp.views;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.flightgearapp.R;
import com.example.flightgearapp.model.FGPlayer;
import com.example.flightgearapp.view_model.ViewModel;

public class MainActivity extends AppCompatActivity {
    private ViewModel vm;
    private FGPlayer model;
    private Joystick joystick;
    private SeekBar throttle;
    private SeekBar rudder;


    private View.OnTouchListener handleTouch = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    if (joystick.isPressed(event.getX(), event.getY())) {
                        joystick.setIsPressed(true);
                    }
                    return true;
                case MotionEvent.ACTION_MOVE:
                    if (joystick.getIsPressed()) {
                        joystick.setSmallCircle((int) event.getX(), (int) event.getY());
                    }
                    return true;
                case MotionEvent.ACTION_UP:
                    joystick.setIsPressed(false);
                    joystick.resetSmallCircle();
                    return true;
            }
            return MainActivity.super.onTouchEvent(event);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.model =  new FGPlayer();
        this.vm = new ViewModel(this.model);
        setContentView(R.layout.activity_main);
        joystick = findViewById(R.id.Joystick);
        joystick.setVM(vm);
        throttle = findViewById(R.id.throttle2);
        rudder = findViewById(R.id.rudder);
        joystick.setOnTouchListener(handleTouch);
        vm.rudderSetting(rudder);
        vm.throttleSetting(throttle);


        // Connect
        Button ConnectButtton = findViewById(R.id.ConnectBtn);
        ConnectButtton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Log.i("TAG", "in onClick");
                EditText myIPText = (EditText) findViewById(R.id.ipEditText);
                EditText myPortText = (EditText) findViewById(R.id.PortEditText);

                String myIP = myIPText.getText().toString();
                int myPort = Integer.parseInt(myPortText.getText().toString());
                try {
                    vm.connectToSocket(myIP, myPort);
                 //   Log.i("TAG", "in connect");
                } catch (InterruptedException e) {
                   // Log.i("TAG", "in catch");
                    e.printStackTrace();
                }
            }
        });
    }

}