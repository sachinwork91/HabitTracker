package com.udacity.submissions.sachin.habittracker;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Sachin on 2018-04-23.
 */

public class ActivityAdapter extends ArrayAdapter<HabitDetail>{

    Context ctx;

    public ActivityAdapter(@NonNull Context context, int resource, List<HabitDetail> data) {
        super(context, 0, data);
        this.ctx = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        HabitDetail habitDetail = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(ctx).inflate(R.layout.list_item_row , parent, false);
        }

        TextView activityName = (TextView) convertView.findViewById(R.id.activityName);
        TextView duration = (TextView) convertView.findViewById(R.id.activityDuration );
        activityName.setText(habitDetail.getActivityName());
        duration.setText(habitDetail.getDuration() +"" );

        return convertView;
    }
}
