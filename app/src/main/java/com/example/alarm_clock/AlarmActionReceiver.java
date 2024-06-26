package com.example.alarm_clock;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.alarm_clock.AlarmReceiver;

public class AlarmActionReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if ("DISMISS_ALARM".equals(action)) {
            dismissAlarm(context);
        } else if ("SNOOZE_ALARM".equals(action)) {
            snoozeAlarm(context);
        }
    }

    private void dismissAlarm(Context context) {
        AlarmReceiver.stopAlarmSound();

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(AlarmReceiver.NOTIFICATION_ID);

        Toast.makeText(context, "Alarm dismissed", Toast.LENGTH_SHORT).show();
    }

    private void snoozeAlarm(Context context) {
        AlarmReceiver.stopAlarmSound();

        long snoozeTime = System.currentTimeMillis() + 5 * 60 * 1000; // Snooze for 5 minutes
        Intent intent = new Intent(context, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        if (alarmManager != null) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, snoozeTime, pendingIntent);
        }

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(AlarmReceiver.NOTIFICATION_ID);

        Toast.makeText(context, "Alarm snoozed for 5 minutes", Toast.LENGTH_SHORT).show();
    }
}
