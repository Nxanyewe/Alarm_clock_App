package com.example.alarm_clock;

public class Alarm {
    private int id;
    private int hour;
    private int minute;
    private String tone;

    public Alarm(int id, int hour, int minute, String tone) {
        this.id = id;
        this.hour = hour;
        this.minute = minute;
        this.tone = tone;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public String getTone() {
        return tone;
    }

    public void setTone(String tone) {
        this.tone = tone;
    }

    @Override
    public String toString() {
        return String.format("Alarm at %02d:%02d, Tone: %s", hour, minute, tone);
    }
}
