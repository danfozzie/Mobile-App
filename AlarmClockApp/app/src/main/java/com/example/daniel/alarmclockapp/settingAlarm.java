package com.example.daniel.alarmclockapp;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class settingAlarm extends AppCompatActivity {

    public static AlarmManager alarmManager;
    public static String selectedAlarm = "";
    public static String selectedTask = "";
    public static String time = "";
    public static String alarmTone = "";
    public static String task = "";
    public static SharedPreferences sharedPref = null;
    private SharedPreferences.Editor editor = null;

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_set);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        sharedPref = this.getSharedPreferences("Prefs", 0);
        editor = sharedPref.edit();

        // Spinner element
        final Spinner spinner = (Spinner) findViewById(R.id.alarmSpinner);

        // Spinner Drop down elements
        List<String> alarms = new ArrayList<String>();
        alarms.add("Random");
        alarms.add("Buzzer Alarm");
        alarms.add("Industrial Alarm");
        alarms.add("Police Siren");
        alarms.add("Tornado Siren");


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, alarms);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedAlarm = spinner.getSelectedItem().toString();
                editor.putString("alarm", selectedAlarm);
                editor.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }

        });

        // Spinner element
        final Spinner spinner2 = (Spinner) findViewById(R.id.taskSpinner);


        // Spinner Drop down elements
        List<String> tasks = new ArrayList<String>();
        tasks.add("Random");
        tasks.add("Name the Flag");
        tasks.add("Maths Challenge");


        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tasks);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(dataAdapter2);

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedTask = spinner2.getSelectedItem().toString();
                editor.putString("task", selectedTask);
                editor.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }

        });

    }

    public void customMessage(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final EditText newMsg = (EditText) findViewById(R.id.message_box);
        final EditText edittext = new EditText (this);

        builder.setView(edittext)
                .setMessage("Alarm Message")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if(edittext.length() <= 25) {
                            String userMsg = edittext.getText().toString();
                            editor.putString("message", userMsg);
                            editor.commit();

                            assert newMsg != null;
                            newMsg.setText(userMsg);
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Message too long. Please reduce to 25 or fewer characters.", Toast.LENGTH_SHORT).show();

                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        // Create the AlertDialog object and return it
        builder.show();
    }


    //Response to button
    @TargetApi(Build.VERSION_CODES.M)
    public void setAlarm(View view) {
        TimePicker timePicker = (TimePicker) findViewById(R.id.timePicker);
        timePicker.clearFocus();

        String formattedTime = "";

        int hour = timePicker.getHour();
        String sHour = "00";
        if(hour < 10){
            sHour = "0"+hour;
        } else {
            sHour = String.valueOf(hour);
        }

        int minute = timePicker.getMinute();
        String sMinute = "00";
        if(minute < 10){
            sMinute = "0"+minute;
        } else {
            sMinute = String.valueOf(minute);
        }

        formattedTime = sHour + ":" + sMinute;

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, timePicker.getHour());
        calendar.set(Calendar.MINUTE, timePicker.getMinute());

        Intent myIntent = new Intent(settingAlarm.this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(settingAlarm.this, 0, myIntent, 0);
        alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);

        Intent intent = new Intent(this, alarmInfo.class);

        time = formattedTime;
        intent.putExtra(time, formattedTime);

        startActivity(intent);
    }

}


