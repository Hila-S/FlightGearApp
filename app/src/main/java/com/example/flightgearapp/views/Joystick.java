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

    private int outerCircleRadius;
    private double outerCircleCenterX;
    private double outerCircleCenterY;
    private double innerCircleCenterX;
    private double innerCircleCenterY;
    private int actualCenterX;
    private int actualCenterY;
    private boolean flag;
    private double distance;
    private boolean isPressed;
    private double tempX;
    private double tempY;
    private ViewModel vm;

    public Joystick(Context context) {
        super(context);
        setOnTouchListener(this);
        actualCenterX = getWidth();
        actualCenterY = getHeight();
        flag = true;

    }

    private void setOnTouchListener(Joystick joystick) {

    }

    public Joystick(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        actualCenterX = getWidth();
        actualCenterY = getHeight();
        flag = true;
    }

    public Joystick(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        actualCenterX = getWidth();
        actualCenterY = getHeight();
        flag = true;
    }

    public void setVM(ViewModel vm){
        this.vm = vm;
    }


    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (flag) {
            actualCenterX = getWidth();
            actualCenterY = getHeight();
            Log.i("TAG", actualCenterX+"");
            Log.i("TAG", actualCenterY+"");
            innerCircleCenterX = getWidth();
            innerCircleCenterY = getHeight();
            outerCircleRadius = 220;
            outerCircleCenterX = actualCenterX / 2;
            outerCircleCenterY = actualCenterY / 2;
            flag = false;
        }
        Paint paint = new Paint();
        paint.setARGB(255, 255, 213, 204);
        canvas.drawCircle(actualCenterX / 2, actualCenterY / 2, outerCircleRadius, paint);
        Paint paint2 = new Paint();
        paint2.setARGB(255, 255, 100, 150);
        canvas.drawCircle((int)innerCircleCenterX / 2, (int)innerCircleCenterY / 2, 100, paint2);
        invalidate();
    }

    public boolean isPressed(double touchedPosX, double touchedPosY){
        distance = Math.sqrt(Math.pow(outerCircleCenterX-touchedPosX, 2)+Math.pow(outerCircleCenterY-touchedPosY,2));
        return distance<outerCircleRadius;
    }

    public void setIsPressed(boolean bool){
        isPressed = bool;
    }

    public boolean getIsPressed(){
        return this.isPressed;
    }

    public void resetActurator(){
        innerCircleCenterX = actualCenterX;
        innerCircleCenterY = actualCenterY;
        //Log.i("TAG", "Middle X:    "+innerCircleCenterX);
        //Log.i("TAG", "Middle Y:    "+innerCircleCenterY);
    }

    public void setActurator(double touchedPosX, double touchedPosY){
        double deltaX = touchedPosX-outerCircleCenterX;
        double deltaY = touchedPosY-outerCircleCenterY;
        double deltaDist = Math.sqrt(Math.pow(deltaX,2)+Math.pow(deltaY,2));
        if(deltaDist<(outerCircleRadius)){
            //tempX = deltaX/outerCircleCenterX;
            //tempY = deltaY/outerCircleCenterY;
            //update();
            update(touchedPosX, touchedPosY);
        }
        else {
            //double dx = deltaX*(deltaDist-outerCircleRadius)/deltaDist;
            //double dy = deltaY*(deltaDist-outerCircleRadius)/deltaDist;
            //update(dx,dy);
            tempX = deltaX/deltaDist;
            tempY = deltaY/deltaDist;
            update();
        }

    }

    public void update(double x, double y){
        innerCircleCenterX = x+outerCircleCenterX;
        innerCircleCenterY = y+outerCircleCenterY;
        vm.setAileron(calculateChange(innerCircleCenterX));
        vm.setElevator(-1*calculateChange(innerCircleCenterY));
        Log.i("TAG", "X:    "+calculateChange(innerCircleCenterX));
        Log.i("TAG", "Y:    "+(-1*calculateChange(innerCircleCenterY)));
        //vm.setaileron(innerCircleCenterX);
        //vm.setElevator(innerCircleCenterY);


    }

    public void update(){
        innerCircleCenterX = outerCircleCenterX+tempX*outerCircleRadius+actualCenterX / 2;
        innerCircleCenterY = outerCircleCenterY+tempY*outerCircleRadius+actualCenterX / 2;
        vm.setAileron(calculateChange(innerCircleCenterX));
        vm.setElevator(calculateChange(innerCircleCenterY));
        //Log.i("TAG", "X:    "+x+"    " +calculateChange(x));
        //Log.i("TAG", "Y:    "+y+"   "+ calculateChange(y));
        //vm.setaileron(innerCircleCenterX);
        //vm.setElevator(innerCircleCenterY);
    }

    public double calculateChange(double touchedPos){
        //Log.i("TAG", "change:    "+touchedPos+"   "+ (touchedPos-actualCenterX)/outerCircleRadius);
        return (touchedPos-actualCenterX)/outerCircleRadius;
    }

}





/*

app:layout_constraintVertical_bias="0.714"
app:layout_constraintHorizontal_bias="0.666"
<!--
    <com.example.flightgearapp.views.Joystick
        android:id="@+id/Joystick"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
    />

            android:id="@+id/Joystick"
        android:layout_width="242dp"
        android:layout_height="240dp"
        android:orientation="vertical"
        tools:ignore="MissingConstraints"
        tools:layout_editor_absoluteX="113dp"
        tools:layout_editor_absoluteY="279dp" />

    <com.example.fgjoystick.view.Joystick
        android:id="@+id/joystick"
        android:layout_width="242dp"
        android:layout_height="240dp"
        android:orientation="vertical"
        tools:ignore="MissingConstraints"
        tools:layout_editor_absoluteX="113dp"
        tools:layout_editor_absoluteY="279dp" />

        =====

    -->
 */
