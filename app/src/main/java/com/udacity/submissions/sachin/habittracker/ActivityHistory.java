package com.udacity.submissions.sachin.habittracker;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;

import com.udacity.submissions.sachin.habittracker.data.ActivityDbHelper;

import java.util.ArrayList;

public class ActivityHistory extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_activity_history);
        listView = (ListView) findViewById(R.id.listview);
       ActivityDbHelper activityDbHelper = new ActivityDbHelper(this);
        ArrayList<HabitDetail> data = activityDbHelper.getHabitHistory();
       ActivityAdapter activityAdapter = new ActivityAdapter(this, 0,data );
       listView.setAdapter(activityAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
}
