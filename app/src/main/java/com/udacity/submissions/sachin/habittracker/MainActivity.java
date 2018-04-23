package com.udacity.submissions.sachin.habittracker;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Spinner;
import android.widget.Toast;

import com.udacity.submissions.sachin.habittracker.data.ActivityContract;
import com.udacity.submissions.sachin.habittracker.data.ActivityContract.ActivityEntry;
import com.udacity.submissions.sachin.habittracker.data.ActivityDbHelper;

import java.sql.SQLDataException;

public class MainActivity extends AppCompatActivity {
    private Chronometer chronometer;
    private Button startButton;
    private Button stopButton;
    private Button resetButton;
    private Button historyButton;
    private Button saveButton;
    private Spinner spinner;
    private int activityDuration;
    private boolean isChronometerRunning;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findAllViews();
        setListeners();
    }

    //Finding the Views
    void findAllViews() {
        chronometer = (Chronometer) findViewById(R.id.chronometer);
        startButton = (Button) findViewById(R.id.start);
        stopButton = (Button) findViewById(R.id.stop);
        resetButton = (Button) findViewById(R.id.reset);
        saveButton = (Button) findViewById(R.id.save);
        spinner = (Spinner) findViewById(R.id.spinner1);
        historyButton = (Button) findViewById(R.id.viewHistory);
        //Disabling the buttons in the begining
        stopButton.setClickable(false);
        resetButton.setClickable(false);
    }

    //Setting up the Listeners
    void setListeners() {
        //Listener for Start
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.start();
                startButton.setClickable(false);
                spinner.setClickable(false);
                isChronometerRunning=true;
            }

        });
        //Listener for Stop
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chronometer.stop();
                startButton.setClickable(true);
                stopButton.setClickable(false);
                saveButton.setClickable(true);
                spinner.setClickable(true);
                long elapsedMillis = SystemClock.elapsedRealtime() - chronometer.getBase();
                Toast.makeText(MainActivity.this, getString(R.string.activityMessage) + elapsedMillis / 1000 + "seconds", Toast.LENGTH_SHORT).show();
                activityDuration = (int) elapsedMillis;
                isChronometerRunning=false;
            }
        });
        //Listener for Reset
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chronometer.stop();
                chronometer.setBase(SystemClock.elapsedRealtime());
                startButton.setClickable(true);
                stopButton.setClickable(true);
                spinner.setClickable(true);
                isChronometerRunning=false;
            }
        });

        //Lisetener for Save
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    String activityName = spinner.getSelectedItem().toString();
                    int duration = activityDuration / 1000;
                    ContentValues values = new ContentValues();
                    values.put(ActivityEntry.ACTIVITY_NAME, activityName);
                    values.put(ActivityEntry.ACTIVITY_DURATION, duration);
                    insert(values);
                isChronometerRunning=false;
                chronometer.stop();
                chronometer.setBase(SystemClock.elapsedRealtime());
                startButton.setClickable(true);
                stopButton.setClickable(true);
                spinner.setClickable(true);
                isChronometerRunning=false;
            }
        });
        //Listeners for History Button
        historyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isChronometerRunning) {
                    Toast.makeText(MainActivity.this, "Stop the Timer First ", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(MainActivity.this, ActivityHistory.class);
                    startActivity(intent);
                }
             }
        });
    }
    //Insert the values in the Database
    void insert(ContentValues values) {
        ActivityDbHelper activityDbHelper = new ActivityDbHelper(this);
        SQLiteDatabase db = activityDbHelper.getWritableDatabase();
        long id = db.insert(ActivityEntry.TABLE_NAME, null, values);
        if (id == -1) {
            Toast.makeText(this, getString(R.string.unsuccessfulInsert) +""  , Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this,getString(R.string.successfulInsert)+"", Toast.LENGTH_SHORT).show();
        }
    }
}
