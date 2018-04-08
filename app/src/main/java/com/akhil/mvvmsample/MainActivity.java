package com.akhil.mvvmsample;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.akhil.mvvmsample.interactor.ApiInterface;
import com.akhil.mvvmsample.interactor.RestApiClient;
import com.akhil.mvvmsample.model.Actor;
import com.akhil.mvvmsample.viewmodel.ApiViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by e on 08-04-2018.
 */

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private ApiViewModel mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        mViewModel = ViewModelProviders.of(this).get(ApiViewModel.class);

       /* mViewModel.getTopActorList().observe(this, new Observer<List<Actor>>() {
            @Override
            public void onChanged(@Nullable List<Actor> actors) {

            }
        });*/
        mViewModel.getTopActorList().observe(this, new Observer<List<Actor>>() {
            @Override
            public void onChanged(@Nullable List<Actor> actors) {
                Log.d(TAG, "actor size=" + actors.size());
            }
        });
    }

    /**
     * initilized views
     */
    private void initView() {
    }
}
