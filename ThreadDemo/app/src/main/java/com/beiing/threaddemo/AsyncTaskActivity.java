package com.beiing.threaddemo;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AsyncTaskActivity extends AppCompatActivity {

	private static int ID = 0;
	private static final int TASK_COUNT = 9;
	private static ExecutorService SINGLE_TASK_EXECUTOR;
	private static ExecutorService LIMITED_TASK_EXECUTOR;
	private static ExecutorService FULL_TASK_EXECUTOR;

    static {
            SINGLE_TASK_EXECUTOR = Executors.newSingleThreadExecutor();
            LIMITED_TASK_EXECUTOR = Executors.newFixedThreadPool(7);
            FULL_TASK_EXECUTOR = Executors.newCachedThreadPool();
         };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);
        ListView listView = (ListView) findViewById(R.id.task_list);
        listView.setAdapter(new AsyncTaskAdapter(this, TASK_COUNT));
    }



	private class AsyncTaskAdapter extends BaseAdapter {
	    private Context mContext;
	    private LayoutInflater mFactory;
	    private int mTaskCount;
	    List<SimpleAsyncTask> mTaskList;

        AsyncTaskAdapter(Context context, int taskCount) {
                mContext = context;
                mFactory = LayoutInflater.from(mContext);
                 mTaskCount = taskCount;
                 mTaskList = new ArrayList<>(taskCount);
        }

                        @Override
	            public int getCount() {
                            return mTaskCount;
                        }

                        @Override
	            public Object getItem(int position) {
                            return mTaskList.get(position);
                        }

                        @Override
	            public long getItemId(int position) {
                            return position;
                        }

                        @Override
	            public View getView(int position, View convertView, ViewGroup parent) {
                            if (convertView == null) {
                              convertView = mFactory.inflate(R.layout.asynctask_demo_item, parent, false);
                              SimpleAsyncTask task = new SimpleAsyncTask((TaskItem) convertView);

//                               task.execute();
                               // use AsyncTask#SERIAL_EXECUTOR is the same to #execute();
//                               task.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR);
                               // use AsyncTask#THREAD_POOL_EXECUTOR is the same to older version #execute() (less than API 11)
                               // but different from newer version of #execute()
//                               task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                               // one by one, same to newer version of #execute()
               //                task.executeOnExecutor(SINGLE_TASK_EXECUTOR);
                               // execute tasks at some limit which can be customized
                               task.executeOnExecutor(LIMITED_TASK_EXECUTOR);
                               // no limit to thread pool size, all tasks run simultaneously
//                               task.executeOnExecutor(FULL_TASK_EXECUTOR);

                               mTaskList.add(task);
                           }
                     return convertView;
                 }
	     }


	private class SimpleAsyncTask extends AsyncTask<Void, Integer, Void> {
	       private TaskItem mTaskItem;
	       private String mName;

                public SimpleAsyncTask(TaskItem item) {
                    mTaskItem = item;
                    mName = "Task #" + String.valueOf(++ID);
                }

             @Override
	 protected Void doInBackground(Void... params) {
                 int prog = 1;
                 while (prog < 101) {
          SystemClock.sleep(5);
                     publishProgress(prog);
                     prog++;
                 }
             return null;
         }

             @Override
	 protected void onPostExecute(Void result) {
             }

             @Override
	 protected void onPreExecute() {
                 mTaskItem.setTitle(mName);
             }

             @Override
	 protected void onProgressUpdate(Integer... values) {
                 mTaskItem.setProgress(values[0]);
             }
	     }





}
