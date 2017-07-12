package com.beiing.threaddemo;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.beiing.threaddemo.R;

/**
 * Created by linechen on 2017/7/11.<br/>
 * 描述：
 * </br>
 */

public class TaskItem extends LinearLayout {
    private TextView mTitle;
    private ProgressBar mProgress;
    public TaskItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public TaskItem(Context context) {
        super(context);
    }
    public void setTitle(String title) {if (mTitle == null) {
        mTitle = (TextView) findViewById(R.id.task_name);
    }
        mTitle.setText(title);
    }

    public void setProgress(int prog) {
        if (mProgress == null) {
            mProgress = (ProgressBar) findViewById(R.id.task_progress);
        }

        mProgress.setProgress(prog);}
}