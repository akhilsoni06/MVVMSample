package com.akhil.mvvmsample.interactor;

import com.akhil.mvvmsample.model.Actor;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by e on 08-04-2018.
 */

public interface ApiInterface {

    @GET("marvel")
    Call<List<Actor>> getTopActorList();
}
