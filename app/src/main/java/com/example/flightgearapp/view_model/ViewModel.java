package com.example.flightgearapp.view_model;

import android.widget.SeekBar;

import com.example.flightgearapp.model.FGPlayer;

public class ViewModel {

    private FGPlayer model;

    public ViewModel(FGPlayer model){
            this.model = model;
        }

        public void setAileron(double innerCircleCenterX){
            model.setAileron(innerCircleCenterX);

        }

        public void setElevator(double innerCircleCenterY){
            model.setElevator(innerCircleCenterY);

        }


        public void setRudder(double data){
            model.setRudder(data);

        }

        public void setThrottle(double data){
            model.setThrottle(data);

        }

        public void rudderSetting( SeekBar rudder){
            model.rudderSettings(rudder);
        }

        public void throttleSetting( SeekBar throttle){
            model.throttleSettings(throttle);
        }

        public void connectToSocket(String ip, int port) throws InterruptedException {
            model.connectToSocket(ip, port);
        }
    }

