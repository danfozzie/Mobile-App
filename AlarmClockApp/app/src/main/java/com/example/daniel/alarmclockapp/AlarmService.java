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
    private NotificationManager alarmNotificationManager;
    static MediaPlayer mPlayer;
    AudioManager mAudioManager;
    int originalVolume;

    public AlarmService() {
        super("AlarmService");
    }

    @Override
    public void onHandleIntent(Intent intent) {
        sendNotification("Click me to turn off alarm");
    }



    private void sendNotification(String msg) {
        alarmNotificationManager = (NotificationManager) this
                .getSystemService(Context.NOTIFICATION_SERVICE);

        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, categories.class), 0);


        List<Integer> soundList = new ArrayList<Integer>();
        soundList.add(R.raw.alarm_buzzer);
        soundList.add(R.raw.industrial_alarm);
        soundList.add(R.raw.police_siren);
        soundList.add(R.raw.tornado_siren);

        int randomInt = new Random().nextInt(soundList.size());
        int sound = soundList.get(randomInt);


        mPlayer = MediaPlayer.create(this, sound);
        mAudioManager = (AudioManager) getSystemService(AUDIO_SERVICE);

        originalVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 8, 0);

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
