package com.udacity.submissions.sachin.habittracker.data;

import android.provider.BaseColumns;

/**
 * Created by Sachin on 2018-04-23.
 */

public class ActivityContract {

    public ActivityContract() {
    }

    public final static class ActivityEntry implements BaseColumns {
        public static final String TABLE_NAME = "activityDetails";
        //List of Columns
        public static final String _ID = BaseColumns._ID;
        public static final String ACTIVITY_NAME = "Activity_Name";
        public static final String ACTIVITY_DURATION = "Duration";
    }
}
