package com.example.daniel.alarmclockapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class maths extends AppCompatActivity {
    TextView textout;
    String letter;

    @Override
    public void onBackPressed() {
    }

    public void changeTime() {
        Intent intent = new Intent(this, AlarmReceiver.class);
        PendingIntent pendingIntent =
                PendingIntent.getBroadcast(maths.this, 0, intent, 0);

        long newTime = SystemClock.currentThreadTimeMillis() + 5000;

        settingAlarm.alarmManager.set(AlarmManager.RTC, newTime, pendingIntent);
    }

    @Override
    //initial declaration of variables
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maths);

        Button snooze = (Button) findViewById(R.id.snooze);
        assert snooze != null;
        snooze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlarmService.mPlayer.stop();
                changeTime();

                Toast.makeText(getApplicationContext(), "Alarm Snoozed. You've got 5 minutes. ", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(Intent.ACTION_MAIN);
                i.addCategory(Intent.CATEGORY_HOME);
                startActivity(i);
            }
        });

        customView myView= (customView) findViewById(R.id.customView);
        myView.setCircleColour(Color.BLUE);
        myView.setCircleTextSize(50);
        myView.setCircleTextColour(Color.WHITE);

        findViewById(R.id.txt_maths_2).setOnLongClickListener(longListen);
        findViewById(R.id.txt_maths_5).setOnLongClickListener(longListen);
        findViewById(R.id.txt_maths_7).setOnLongClickListener(longListen);
        findViewById(R.id.txt_maths_times).setOnLongClickListener(longListen);
        findViewById(R.id.txt_maths_plus).setOnLongClickListener(longListen);
        findViewById(R.id.txtA1).setOnDragListener(dragListen1);
        findViewById(R.id.txtA2).setOnDragListener(dragListen2);
        findViewById(R.id.txtA3).setOnDragListener(dragListen3);
        findViewById(R.id.txtA4).setOnDragListener(dragListen4);
        findViewById(R.id.txtA5).setOnDragListener(dragListen5);


    }
    // method used to clear the text boxes
    public void clearBoxes(View view) {
        letter = "";
        textout = (TextView) findViewById(R.id.txtA1);
        TextView textout2 = (TextView) findViewById(R.id.txtA2);
        TextView textout3 = (TextView) findViewById(R.id.txtA3);
        TextView textout4 = (TextView) findViewById(R.id.txtA4);
        TextView textout5 = (TextView) findViewById(R.id.txtA5);


        if ((textout != null || !textout.getText().equals(""))
                && (textout2 != null || !textout2.getText().equals(""))
                && (textout3 != null || !textout3.getText().equals(""))
                && (textout4 != null || !textout4.getText().equals(""))
                && (textout5 != null || !textout5.getText().equals(""))
                ) {
            textout.setText(letter);
            textout2.setText(letter);
            textout3.setText(letter);
            textout4.setText(letter);
            textout5.setText(letter);
            Toast.makeText(getApplicationContext(), "Cleared", Toast.LENGTH_SHORT).show();
        }
    }
    //method used to clear the boxes one by one (a backspace button)
    public void back1(View view) {
        letter = "";
        textout = (TextView) findViewById(R.id.txtA1);
        TextView textout2 = (TextView) findViewById(R.id.txtA2);
        TextView textout3 = (TextView) findViewById(R.id.txtA3);
        TextView textout4 = (TextView) findViewById(R.id.txtA4);
        TextView textout5 = (TextView) findViewById(R.id.txtA5);

        if ((textout != null || !textout.getText().equals("")) && (textout2 == null || textout2.getText().equals(""))) {
            textout.setText(letter);
        }
        if ((textout != null || !textout.getText().equals("")) && (textout2 != null || !textout2.getText().equals("")) && (textout3 == null || textout3.getText().equals(""))) {
            textout2.setText(letter);
        }
        if ((textout != null || !textout.getText().equals("")) && (textout2 != null || !textout2.getText().equals("")) && (textout3 != null || !textout3.getText().equals(""))
                && (textout4 == null || textout4.getText().equals(""))) {
            textout3.setText(letter);
        }
        if ((textout != null || !textout.getText().equals("")) && (textout2 != null || !textout2.getText().equals("")) && (textout3 != null || !textout3.getText().equals(""))
                && (textout4 != null || !textout4.getText().equals("")) && (textout5 == null || textout5.getText().equals(""))) {
            textout4.setText(letter);
        }
        if ((textout != null || !textout.getText().equals("")) && (textout2 != null || !textout2.getText().equals("")) && (textout3 != null || !textout3.getText().equals(""))
                && (textout4 != null || !textout4.getText().equals("")) && (textout5 != null || !textout5.getText().equals(""))) {
            textout5.setText(letter);
        }
    }
    //setting up the drag & drop function
    View.OnLongClickListener longListen = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            DragShadow dragShadow = new DragShadow(v);
            ClipData data = ClipData.newPlainText("", "");
            v.startDrag(data, dragShadow, v, 0);
            return false;
        }
    };
    // dragging of each individual TextView
    View.OnDragListener dragListen1 = new View.OnDragListener() {
        @Override
        public boolean onDrag(View v, DragEvent event) {
            int dragEvent = event.getAction();
            switch (dragEvent) {
                case DragEvent.ACTION_DRAG_ENTERED:
                    //Log.i("Drag Event", "Entered");
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    //Log.i("Drag Event", "Exited");
                    break;
                case DragEvent.ACTION_DROP:
                    TextView target = (TextView) v;
                    TextView dragged = (TextView) event.getLocalState();
                    target.setText(dragged.getText());

                    break;
            }

            return true;
        }
    };

    View.OnDragListener dragListen2 = new View.OnDragListener() {
        @Override
        public boolean onDrag(View v, DragEvent event) {
            int dragEvent = event.getAction();
            switch (dragEvent) {
                case DragEvent.ACTION_DRAG_ENTERED:
                    //Log.i("Drag Event", "Entered");
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    //Log.i("Drag Event", "Exited");
                    break;
                case DragEvent.ACTION_DROP:
                    TextView target = (TextView) v;
                    TextView dragged = (TextView) event.getLocalState();
                    target.setText(dragged.getText());

                    break;
            }

            return true;
        }
    };

    View.OnDragListener dragListen3 = new View.OnDragListener() {
        @Override
        public boolean onDrag(View v, DragEvent event) {
            int dragEvent = event.getAction();
            switch (dragEvent) {
                case DragEvent.ACTION_DRAG_ENTERED:
                    //Log.i("Drag Event", "Entered");
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    //Log.i("Drag Event", "Exited");
                    break;
                case DragEvent.ACTION_DROP:
                    TextView target = (TextView) v;
                    TextView dragged = (TextView) event.getLocalState();
                    target.setText(dragged.getText());

                    break;
            }

            return true;
        }
    };

    View.OnDragListener dragListen4 = new View.OnDragListener() {
        @Override
        public boolean onDrag(View v, DragEvent event) {
            int dragEvent = event.getAction();
            switch (dragEvent) {
                case DragEvent.ACTION_DRAG_ENTERED:
                    //Log.i("Drag Event", "Entered");
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    //Log.i("Drag Event", "Exited");
                    break;
                case DragEvent.ACTION_DROP:
                    TextView target = (TextView) v;
                    TextView dragged = (TextView) event.getLocalState();
                    target.setText(dragged.getText());

                    break;
            }

            return true;
        }
    };

    View.OnDragListener dragListen5 = new View.OnDragListener() {
        @Override
        public boolean onDrag(View v, DragEvent event) {
            int dragEvent = event.getAction();
            switch (dragEvent) {
                case DragEvent.ACTION_DRAG_ENTERED:
                    //Log.i("Drag Event", "Entered");
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    //Log.i("Drag Event", "Exited");
                    break;
                case DragEvent.ACTION_DROP:
                    TextView target = (TextView) v;
                    TextView dragged = (TextView) event.getLocalState();
                    target.setText(dragged.getText());

                    break;
            }

            return true;
        }
    };
    // creating the shadow for the drag (user interface)
    private class DragShadow extends View.DragShadowBuilder {
        ColorDrawable geryBox;


        public DragShadow(View view) {
            super(view);
            geryBox = new ColorDrawable(Color.LTGRAY);
        }

        /*public DragShadow() {
            super();
        }*/

        @Override
        public void onDrawShadow(Canvas canvas) {
            geryBox.draw(canvas);

        }

        @Override
        public void onProvideShadowMetrics(Point shadowSize, Point shadowTouchPoint) {
            View v = getView();
            int height = 150;
            int width = 150;

            geryBox.setBounds(0, 0, width, height);
            shadowSize.set(width, height);
            shadowTouchPoint.set(width / 2, height / 2);
        }
    }

    // method to check the final answer
    public void check_ans(View view) {

        TextView txtout1 = (TextView) findViewById(R.id.txtA1);
        TextView txtout2 = (TextView) findViewById(R.id.txtA2);
        TextView txtout3 = (TextView) findViewById(R.id.txtA3);
        TextView txtout4 = (TextView) findViewById(R.id.txtA4);
        TextView txtout5 = (TextView) findViewById(R.id.txtA5);
        String txtA1 = txtout1.getText().toString();
        String txtA2 = txtout2.getText().toString();
        String txtA3 = txtout3.getText().toString();
        String txtA4 = txtout4.getText().toString();
        String txtA5 = txtout5.getText().toString();

        if (txtA1.equals("")) {
            customView myView= (customView) findViewById(R.id.customView);
            myView.setCircleColour(Color.RED);
            myView.setCircleTextSize(50);
            myView.setCircleTextColour(Color.WHITE);
            Toast.makeText(getApplicationContext(), "I mean, come on", Toast.LENGTH_SHORT).show();
        }
        else if (txtA1.equals("5") && txtA2.equals("*") && txtA3.equals("2") && txtA4.equals("+") && txtA5.equals("7")) {
            customView myView= (customView) findViewById(R.id.customView);
            myView.setCircleColour(Color.GREEN);
            myView.setCircleTextSize(50);
            myView.setCircleTextColour(Color.WHITE);
            AlarmService.mPlayer.stop();
            Intent intent = new Intent(this, settingAlarm.class);
            startActivity(intent);
        }
        else if (txtA1.equals("2") && txtA2.equals("*") && txtA3.equals("5") && txtA4.equals("+") && txtA5.equals("7")) {
            customView myView= (customView) findViewById(R.id.customView);
            myView.setCircleColour(Color.GREEN);
            myView.setCircleTextSize(50);
            myView.setCircleTextColour(Color.WHITE);
            AlarmService.mPlayer.stop();
            Intent intent = new Intent(this, settingAlarm.class);
            startActivity(intent);
        }
        else if (txtA1.equals("7") && txtA2.equals("+") && txtA3.equals("2") && txtA4.equals("*") && txtA5.equals("5")) {
            customView myView= (customView) findViewById(R.id.customView);
            myView.setCircleColour(Color.GREEN);
            myView.setCircleTextSize(50);
            myView.setCircleTextColour(Color.WHITE);
            AlarmService.mPlayer.stop();
            Intent intent = new Intent(this, settingAlarm.class);
            startActivity(intent);
        }
        else if (txtA1.equals("7") && txtA2.equals("+") && txtA3.equals("5") && txtA4.equals("*") && txtA5.equals("2")) {
            customView myView= (customView) findViewById(R.id.customView);
            myView.setCircleColour(Color.GREEN);
            myView.setCircleTextSize(50);
            myView.setCircleTextColour(Color.WHITE);
            AlarmService.mPlayer.stop();
            Intent intent = new Intent(this, settingAlarm.class);
            startActivity(intent);
        }
        else {
            customView myView= (customView) findViewById(R.id.customView);
            myView.setCircleColour(Color.RED);
            myView.setCircleTextSize(50);
            myView.setCircleTextColour(Color.WHITE);
            Toast.makeText(getApplicationContext(), "Incorrect answer, please try again", Toast.LENGTH_SHORT).show();
        }
    }
}
