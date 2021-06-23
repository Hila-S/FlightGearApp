package com.example.flightgearapp.view_model;

import android.widget.SeekBar;

import com.example.flightgearapp.model.FGPlayer;

public class ViewModel {

    private FGPlayer model;

    public ViewModel(FGPlayer fgPlayer){
            this.model = fgPlayer;
        }

        public void setDataAileron(double smallCircleCenterX){
            model.setDataAileron(smallCircleCenterX);

        }

        public void setDataElevator(double smallCircleCenterY){
            model.setDataElevator(smallCircleCenterY);

        }


        public void rudderSetting( SeekBar rudder){
            model.rudderSettings(rudder);
        }

        public void throttleSetting( SeekBar throttle){
            model.throttleSettings(throttle);
        }

        public void connectToSocket(String myIP, int myPort) throws InterruptedException {
            model.connectToSocket(myIP, myPort);
        }
    }

