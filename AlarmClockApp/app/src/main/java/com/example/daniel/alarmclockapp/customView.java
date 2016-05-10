package com.example.daniel.alarmclockapp;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class customView extends View {

    private String circleText;
    private int circleColour;
    private int circleTextColour;
    private float circleTextSize;
    private Paint paint = new Paint();

    public customView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.customView,0,0);

        try{
            circleText = a.getString(R.styleable.customView_circleText);
            circleColour = a.getInteger(R.styleable.customView_circleColor, 0);
            circleTextColour = a.getInteger(R.styleable.customView_circleTextColor,0);
            circleTextSize = a.getFloat(R.styleable.customView_circleTextSize, 20);

        }finally {
            a.recycle();
        }
    }

    @Override
    protected void onDraw(Canvas canvas){
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setColor(circleColour);

        int centerX = this.getMeasuredWidth()/2;
        int centerY = this.getMeasuredHeight()/2;
        int radius = 100;
        canvas.drawCircle(centerX,centerY,radius,paint);

        paint.setColor(circleTextColour);
        paint.setTextAlign(Paint.Align.CENTER);

        canvas.drawText(circleText, centerX, centerY, paint);
    }

    public void setCircleColour(int newCircleColour) {
        circleColour = newCircleColour;
        invalidate();
        requestLayout();
    }

    public void setCircleTextColour(int newCircleTextColour) {
        circleTextColour = newCircleTextColour;
        invalidate();
        requestLayout();
    }

    public void setCircleText(String newCircleText) {
        circleText = newCircleText;
        invalidate();
        requestLayout();
    }

    public void setCircleTextSize(float newCircleTextSize) {
        paint.setTextSize(newCircleTextSize);
        invalidate();
        requestLayout();
    }
}
