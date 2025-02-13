package org.example.SmartHome;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private long time = 0;
    private long tempTime = 0;
    private int sec, min, hour = 0;
    private int day = 1;
    private final List<Time> followers = new ArrayList<>();
    private boolean flag = false;

    private void update(long time) throws IOException {
        this.time += time;
        this.tempTime += time;
        if (this.tempTime > 1000000000) {
            sec += this.tempTime / 1000000000;
            this.tempTime = this.tempTime % 1000000000;
        }
        if (sec >= 60) {
            min += sec / 60;
            sec = sec % 60;
        }
        if (min >= 60) {
            hour += min / 60;
            min = min % 60;
        }
        if (hour >= 24) {
            day += hour / 24;
            hour = hour % 24;
        }
        for (Time follower : this.followers) {
            follower.update(time);
        }
    }
    public void start(int speed, long timeToSimulate) {
        flag = false;
        new Thread(() -> {
            long lastUpdate = System.nanoTime();
            while (!flag && timeToSimulate > time) {
                long currentTime = System.nanoTime();
                if (currentTime - lastUpdate > (1000000000 / 60)) {
                    long delta = speed * (currentTime - lastUpdate);
                    lastUpdate = currentTime;
                    try {
                        update(delta);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();
    }
    public void stop() {
        flag = true;
    }

    public long getTime() {
        return time;
    }

    public int getMin() {
        return min;
    }

    public int getHour() {
        return hour;
    }

    public int getDay() {
        return day;
    }

    public String gettime() {
        return String.format("min: %s, hour: %s,  day: %s", min, hour, day);

    }

    public void follow(Time follower) {
        this.followers.add(follower);
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMin(int min) {
        this.min = min;
    }
}
