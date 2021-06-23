package com.example.flightgearapp.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.flightgearapp.view_model.ViewModel;

public class Joystick extends View {

    private int bigRadius;
    private double bigCenterX;
    private double bigCenterY;
    private double smallCenterX;
    private double smallCenterY;
    private int width;
    private int height;
    private boolean initFlag;
    private double dist;
    private boolean isPressed;
    private double tmpX;
    private double tmpY;
    private ViewModel vm;

    public Joystick(Context context) {
        super(context);
        setOnTouchListener(this);
        width = getWidth();
        height = getHeight();
        initFlag = true;

    }

    private void setOnTouchListener(Joystick joystick) {

    }

    public Joystick(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        width = getWidth();
        height = getHeight();
        initFlag = true;
    }

    public Joystick(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        width = getWidth();
        height = getHeight();
        initFlag = true;
    }

    public void setVM(ViewModel vm){
        this.vm = vm;
    }


    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (initFlag) {
            width = getWidth();
            height = getHeight();
            //Log.i("TAG", width+"");
            //Log.i("TAG", height+"");
            smallCenterX = getWidth();
            smallCenterY = getHeight();
            bigRadius = 220;
            bigCenterX = width / 2;
            bigCenterY = height / 2;
            initFlag = false;
        }
        Paint paint = new Paint();
        paint.setARGB(255, 27, 28, 24);
        canvas.drawCircle(width / 2, height / 2, bigRadius, paint);
        Paint paint2 = new Paint();
        paint2.setARGB(255, 151, 156, 168);
        canvas.drawCircle((int)smallCenterX / 2, (int)smallCenterY / 2, 100, paint2);
        invalidate();
    }

    public boolean isPressed(double touchedPosX, double touchedPosY){
        dist = Math.sqrt(Math.pow(bigCenterX-touchedPosX, 2)+Math.pow(bigCenterY-touchedPosY,2));
        return dist<bigRadius;
    }

    public void setIsPressed(boolean isPressed){
        this.isPressed = isPressed;
    }

    public boolean getIsPressed(){
        return this.isPressed;
    }

    public void resetSmallCircle(){
        smallCenterX = width;
        smallCenterY = height;
        //Log.i("TAG", "Middle X:    "+innerCircleCenterX);
        //Log.i("TAG", "Middle Y:    "+innerCircleCenterY);
    }

    public void setSmallCircle(double touchedPosX, double touchedPosY){
        double distX = touchedPosX-bigCenterX;
        double distY = touchedPosY-bigCenterY;
        double dist = Math.sqrt(Math.pow(distX,2)+Math.pow(distY,2));
        if(dist<(bigRadius)){
            updateData(touchedPosX, touchedPosY);
        }
        else {
            tmpX = distX/dist;
            tmpY = distY/dist;
            updateData();
        }

    }

    public void updateData(double x, double y){
        smallCenterX = x+bigCenterX;
        smallCenterY = y+bigCenterY;
        vm.setDataAileron(calculatePosition(smallCenterX));
        vm.setDataElevator(-1*calculatePosition(smallCenterY));
        //Log.i("TAG", "X:    "+calculatePosition(smallCenterX));
        //Log.i("TAG", "Y:    "+(-1*calculatePosition(smallCenterY)));



    }

    public void updateData(){
        smallCenterX = bigCenterX+tmpX*bigRadius+width / 2;
        smallCenterY = bigCenterY+tmpY*bigRadius+width / 2;
        vm.setDataAileron(calculatePosition(smallCenterX));
        vm.setDataElevator(calculatePosition(smallCenterY));
        //Log.i("TAG", "X:    "+x+"    " +calculateChange(x));
        //Log.i("TAG", "Y:    "+y+"   "+ calculateChange(y));
    }

    public double calculatePosition(double touchedPosition){
        //Log.i("TAG", "change:    "+touchedPos+"   "+ (touchedPos-actualCenterX)/outerCircleRadius);
        return (touchedPosition-width)/bigRadius;
    }

}




