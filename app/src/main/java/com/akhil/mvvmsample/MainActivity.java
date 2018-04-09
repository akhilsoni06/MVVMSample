package com.akhil.mvvmsample;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.akhil.mvvmsample.adapter.ListAdapter;
import com.akhil.mvvmsample.model.Actor;
import com.akhil.mvvmsample.viewmodel.ApiViewModel;

import java.util.List;

/**
 * Created by e on 08-04-2018.
 */

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private ApiViewModel mViewModel;
    private RecyclerView mRecycleListView;
    private ListAdapter mListAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        mViewModel = ViewModelProviders.of(this).get(ApiViewModel.class);
        mViewModel.getTopActorList().observe(this, new Observer<List<Actor>>() {
            @Override
            public void onChanged(@Nullable List<Actor> actors) {
                Log.d(TAG, "actor size=" + actors.size());
                setAdapter(actors);
            }
        });
    }

    private void initView() {
        mRecycleListView = (RecyclerView) findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(MainActivity.this);
        mRecycleListView.setLayoutManager(manager);
    }

    private void setAdapter(List<Actor> actorList) {
        mListAdapter = new ListAdapter(actorList);
        mRecycleListView.setAdapter(mListAdapter);
    }
}
