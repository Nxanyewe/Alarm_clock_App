package com.example.alarm_clock;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private TextView currentTime;
    private TextView currentDate;
    private Button setAlarmButton;
    private ListView alarmsListView;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentTime = findViewById(R.id.currentTime);
        currentDate = findViewById(R.id.currentDate);
        setAlarmButton = findViewById(R.id.setAlarmButton);
        alarmsListView = findViewById(R.id.alarmListView);
        db = new DatabaseHelper(this);

        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(() -> {
                    currentTime.setText(timeFormat.format(new Date()));
                    currentDate.setText(dateFormat.format(new Date()));
                });
            }
        }, 0, 1000);

        setAlarmButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SetAlarm.class);
            startActivity(intent);
        });

        loadAlarms();
    }

    private void loadAlarms() {
        List<Alarm> alarms = db.getAllAlarms();
        ArrayAdapter<Alarm> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alarms);
        alarmsListView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadAlarms(); // Refresh the alarm list when returning to the main activity
    }
}
