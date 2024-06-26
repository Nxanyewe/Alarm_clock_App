package com.example.alarm_clock;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;

import java.util.Calendar;

public class AlarmActivity extends Activity {

    private Ringtone ringtone;
    private Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        Button snoozeButton = findViewById(R.id.snoozeButton);
        Button dismissButton = findViewById(R.id.dismissButton);

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator != null) {
            vibrator.vibrate(1000);
        }

        ringtone = RingtoneManager.getRingtone(this, RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM));
        ringtone.play();

        snoozeButton.setOnClickListener(v -> snoozeAlarm());
        dismissButton.setOnClickListener(v -> dismissAlarm());
    }

    private void snoozeAlarm() {
        if (ringtone != null) {
            ringtone.stop();
        }
        if (vibrator != null) {
            vibrator.cancel();
        }

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 10); // Snooze for 10 minutes

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        finish();
    }

    private void dismissAlarm() {
        if (ringtone != null) {
            ringtone.stop();
        }
        if (vibrator != null) {
            vibrator.cancel();
        }
        finish();
    }
}