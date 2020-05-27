package com.example.asynctaskwithlistview;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MyTask extends AsyncTask<Void,String,String> {

    private ArrayAdapter<String> adapter;
    private ProgressBar progressBar;
    private ListView listView;
    private String[] names;
    private Context context;
    private int count;

    MyTask(Context context, String[] names, ListView listView, ProgressBar progressBar) {
        this.listView = listView;
        this.context = context;
        this.names = names;
        this.progressBar = progressBar;
    }

    @Override
    protected void onPreExecute() {
        adapter = (ArrayAdapter<String>) listView.getAdapter();
        progressBar.setMax(19);
        progressBar.setProgress(0);
        progressBar.setVisibility(View.VISIBLE);
        count=0;
    }

    @Override
    protected String doInBackground(Void... voids) {
        for (String name : names){
            publishProgress(name);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return "All the names are added successfully";
    }

    @Override
    protected void onProgressUpdate(String... values) {
        adapter.add(values[0]);
        count++;
        progressBar.setProgress(count);
    }

    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(context,result, Toast.LENGTH_SHORT).show();
        progressBar.setVisibility(View.INVISIBLE);
    }
}

