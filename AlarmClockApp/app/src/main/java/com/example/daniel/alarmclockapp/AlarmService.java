package com.example.daniel.alarmclockapp;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v4.app.NotificationCompat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class AlarmService extends IntentService{
    static MediaPlayer mPlayer;
    AudioManager mAudioManager;
    int originalVolume;

    public AlarmService() {
        super("AlarmService");
    }

    @Override
    public void onHandleIntent(Intent intent) {
        sendNotification("Snooze/Dismiss Alarm");
    }



    private void sendNotification(String msg) {
        NotificationManager alarmNotificationManager = (NotificationManager) this
                .getSystemService(Context.NOTIFICATION_SERVICE);

        PendingIntent contentIntent;

        switch (settingAlarm.selectedTask) {
            case "Name the Flag":
                contentIntent = PendingIntent.getActivity(this, 0,
                        new Intent(this, categories.class), 0);
                break;
            case "Maths Challenge":
                contentIntent = PendingIntent.getActivity(this, 0,
                        new Intent(this, maths.class), 0);
                break;
            default:
                List<Class> taskList = new ArrayList<>();
                taskList.add(categories.class);
                taskList.add(maths.class);

                int randomInt = new Random().nextInt(taskList.size());
                Class task = taskList.get(randomInt);

                contentIntent = PendingIntent.getActivity(this, 0,
                        new Intent(this, task), 0);
                break;
        }

        int sound;

        switch (settingAlarm.selectedAlarm) {
            case "Buzzer Alarm":
                sound = R.raw.alarm_buzzer;
                break;
            case "Industrial Alarm":
                sound = R.raw.industrial_alarm;
                break;
            case "Police Siren":
                sound = R.raw.police_siren;
                break;
            case "Tornado Siren":
                sound = R.raw.tornado_siren;
                break;
            default:
                List<Integer> soundList = new ArrayList<>();
                soundList.add(R.raw.alarm_buzzer);
                soundList.add(R.raw.industrial_alarm);
                soundList.add(R.raw.police_siren);
                soundList.add(R.raw.tornado_siren);

                int randomInt = new Random().nextInt(soundList.size());
                sound = soundList.get(randomInt);
                break;
        }

        mPlayer = MediaPlayer.create(this, sound);
        mAudioManager = (AudioManager) getSystemService(AUDIO_SERVICE);

        originalVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 4, 0);

        mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mPlayer.setLooping(true);
        mPlayer.start();

        NotificationCompat.Builder alarmNotificationBuilder = new NotificationCompat.Builder(
                this).setContentTitle("Alarm").setSmallIcon(R.drawable.ic_launcher)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(msg))
                .setContentText(msg)
                .setAutoCancel(true);


        alarmNotificationBuilder.setContentIntent(contentIntent);
        alarmNotificationManager.notify(1, alarmNotificationBuilder.build());
    }
}
