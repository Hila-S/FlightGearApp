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

    private Joystick joystick;
    private FGPlayer model;
    private SeekBar throttle;
    private SeekBar rudder;
    private ViewModel vm;

    private View.OnTouchListener handleTouch = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {

            int x = (int) event.getX();
            int y = (int) event.getY();

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    if (joystick.isPressed(event.getX(), event.getY())) {
                        joystick.setIsPressed(true);
                    }
                    return true;
                case MotionEvent.ACTION_MOVE:
                    if (joystick.getIsPressed()) {
                        joystick.setActurator((int) event.getX(), (int) event.getY());
                        //Log.i("TAG", event.getX()+"");
                        //Log.i("TAG", event.getY()+"");
                    }
                    return true;
                case MotionEvent.ACTION_UP:
                    joystick.setIsPressed(false);
                    joystick.resetActurator();
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
        //Joystick joystick = (Joystick) findViewById((R.id.Joystick));
        joystick = (Joystick) findViewById(R.id.Joystick);
        joystick.setVM(vm);
        throttle = (SeekBar) findViewById(R.id.throttle2);
        rudder = (SeekBar) findViewById(R.id.rudder);
        joystick.setOnTouchListener(handleTouch);
        vm.throttleSetting(throttle);
        vm.rudderSetting(rudder);

        // Connect
        Button ConnectBtn = (Button) findViewById(R.id.ConnectBtn);
        ConnectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("TAG", "in onClick");
                EditText IPeditText = (EditText) findViewById(R.id.ipEditText);
                EditText PortEditText = (EditText) findViewById(R.id.PortEditText);

                String IP = IPeditText.getText().toString();
                int Port = Integer.parseInt(PortEditText.getText().toString());
                try {
                    vm.connectToSocket(IP, Port);
                    Log.i("TAG", "in connect");
                } catch (InterruptedException e) {
                    Log.i("TAG", "in catch");
                    e.printStackTrace();
                }
            }
        });
    }

}