package com.udacity.submissions.sachin.habittracker;

/**
 * Created by Sachin on 2018-04-23.
 */

public class HabitDetail {

    private String activityName;
    private int duration;

    public HabitDetail(String activityName, int duration) {
        this.activityName = activityName;
        this.duration = duration;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
