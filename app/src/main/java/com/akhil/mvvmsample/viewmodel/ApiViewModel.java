package com.akhil.mvvmsample.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;

import com.akhil.mvvmsample.interactor.RestApiClient;
import com.akhil.mvvmsample.model.Movies;

import java.util.List;


/**
 * Created by Akhil on 08-04-2018.
 */

public class ApiViewModel extends AndroidViewModel {
    private RestApiClient mRestApiClient;
    private MediatorLiveData<List<Movies>> mMediatorLiveData;

    public ApiViewModel(Application application) {
        super(application);
        mRestApiClient = new RestApiClient();
        mMediatorLiveData = new MediatorLiveData<>();
    }

    public LiveData<List<Movies>> getTopActorList() {
        mMediatorLiveData.addSource(mRestApiClient.getActorList(), new Observer<List<Movies>>() {
            @Override
            public void onChanged(@Nullable List<Movies> movies) {
                mMediatorLiveData.setValue(movies);
            }
        });
        return mMediatorLiveData;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
