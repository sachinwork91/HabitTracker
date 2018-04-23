package com.udacity.submissions.sachin.habittracker.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.udacity.submissions.sachin.habittracker.HabitDetail;
import com.udacity.submissions.sachin.habittracker.data.ActivityContract.ActivityEntry;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Sachin on 2018-04-23.
 */

public class ActivityDbHelper extends SQLiteOpenHelper {

    private Context mContext;

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "HabitTracker.db";
    private static final String SQL_CREATE_ACTIVITY_TABLE = " CREATE TABLE " + ActivityEntry.TABLE_NAME + "( "
            + ActivityEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + ActivityEntry.ACTIVITY_NAME + " TEXT NOT NULL, "
            + ActivityEntry.ACTIVITY_DURATION + " INTEGER NOT NULL ) ;";

    public ActivityDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ACTIVITY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public ArrayList<HabitDetail> getHabitHistory() {
        SQLiteDatabase db = getReadableDatabase();
        //Setting up the query for Cursor
        String[] projection = {ActivityEntry.ACTIVITY_NAME, ActivityEntry.ACTIVITY_DURATION};
        String selection = null;
        String[] selectionArgs = null;
        String groupby = null;
        String having = null;
        String orderBy = null;

        Cursor cur = db.query(ActivityEntry.TABLE_NAME, projection, null, null, null, null, null);

        ArrayList<HabitDetail> data = new ArrayList<>();

        while (cur.moveToNext()) {

            int activityNameIndex = cur.getColumnIndex(ActivityEntry.ACTIVITY_NAME);
            int activityDurationIndex = cur.getColumnIndex(ActivityEntry.ACTIVITY_DURATION);

            data.add(new HabitDetail(cur.getString(activityNameIndex), cur.getInt(activityDurationIndex)));

        }

        return data;
    }

}
