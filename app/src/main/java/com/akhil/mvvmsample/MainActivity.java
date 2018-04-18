package com.akhil.mvvmsample;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.akhil.mvvmsample.adapter.ListAdapter;
import com.akhil.mvvmsample.model.Movies;
import com.akhil.mvvmsample.viewmodel.ApiViewModel;

import java.util.List;

/**
 * Created by Akhil on 08-04-2018.
 */

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private ApiViewModel mViewModel;
    private RecyclerView mRecycleListView;
    private ListAdapter mListAdapter;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        mViewModel = ViewModelProviders.of(this).get(ApiViewModel.class);
        mViewModel.getTopActorList().observe(this, new Observer<List<Movies>>() {
            @Override
            public void onChanged(@Nullable List<Movies> movies) {
                Log.d(TAG, "actor size=" + movies.size());
                mProgressBar.setVisibility(View.GONE);
                setAdapter(movies);
            }
        });
    }

    private void initView() {
        mRecycleListView = (RecyclerView) findViewById(R.id.recycler_view);
        mProgressBar =(ProgressBar)findViewById(R.id.progress_bar);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(MainActivity.this);
        mRecycleListView.setLayoutManager(manager);
    }

    private void setAdapter(List<Movies> moviesList) {
        if(moviesList!=null && moviesList.size()>0)
        {
            mListAdapter = new ListAdapter(moviesList);
            mRecycleListView.setAdapter(mListAdapter);
        }
    }
}
