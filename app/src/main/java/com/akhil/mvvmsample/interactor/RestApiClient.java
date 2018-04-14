package com.akhil.mvvmsample.interactor;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.akhil.mvvmsample.model.Actor;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Akhil on 08-04-2018.
 */

public class RestApiClient {

    private final String TAG = RestApiClient.class.getSimpleName();
    private static final String BASE_URL = "https://simplifiedcoding.net/demos/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).
                    addConverterFactory(GsonConverterFactory.create()).
                    build();
        }
        return retrofit;
    }


    public LiveData<List<Actor>> getActorList() {
        final MutableLiveData<List<Actor>> liveData = new MutableLiveData<>();
        ApiInterface apiInterface = RestApiClient.getClient().create(ApiInterface.class);
        apiInterface.getTopActorList().enqueue(new Callback<List<Actor>>() {
            @Override
            public void onResponse(Call<List<Actor>> call, Response<List<Actor>> response) {
                Log.d(TAG, "size=" + response.body().size());
                liveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Actor>> call, Throwable t) {
                Log.d(TAG, t.getLocalizedMessage());
            }
        });
        return liveData;
    }
}
