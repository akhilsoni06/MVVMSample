package com.akhil.mvvmsample.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.akhil.mvvmsample.interactor.RestApiClient;
import com.akhil.mvvmsample.model.Actor;

import java.util.List;


/**
 * Created by e on 08-04-2018.
 */

public class ApiViewModel extends ViewModel {
    private static final String TAG = ApiViewModel.class.getSimpleName();

    private RestApiClient mRestApiClient;
    private LiveData<List<Actor>> actorList;
    private MediatorLiveData<List<Actor>> mMediatorLiveData;

    public ApiViewModel() {
        mRestApiClient = new RestApiClient();
        mMediatorLiveData =new MediatorLiveData<>();
    }

    public LiveData<List<Actor>> getTopActorList() {
        mMediatorLiveData.addSource(mRestApiClient.getActorList(), new Observer<List<Actor>>() {
            @Override
            public void onChanged(@Nullable List<Actor> actors) {
                mMediatorLiveData.setValue(actors);
            }
        });
        return mMediatorLiveData;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
