package com.example.daniel.alarmclockapp;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class alarmList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_list);

        Intent intent = getIntent();
        String message = intent.getStringExtra(settingAlarm.EXTRA_MESSAGE);
        String message2 = intent.getStringExtra(settingAlarm.selectedAlarm);
        String message3 = intent.getStringExtra(settingAlarm.selectedTask);

        TextView textView = (TextView) findViewById(R.id.alarmSetText);
        TextView textView2 = (TextView) findViewById(R.id.chosenAlarmText);
        TextView textView3 = (TextView) findViewById(R.id.chosenTaskText);

        assert textView != null;
        textView.setText(message);
        textView.setTextSize(32);

        assert textView2 != null;
        textView2.setText(message2);
        textView2.setTextSize(32);

        assert textView3 != null;
        textView3.setText(message3);
        textView3.setTextSize(32);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void changeAlarm(View view) {
        Intent intent2 = new Intent(this, settingAlarm.class);
        startActivity(intent2);
    }


}
