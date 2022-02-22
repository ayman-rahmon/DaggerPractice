package com.tatsujin.daggerpractice.network.auth;


import com.tatsujin.daggerpractice.models.User;

import io.reactivex.rxjava3.core.Flowable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AuthAPI {


//    @GET
//    Flowable<ResponseBody> getFakeStuff();
    @GET("users/{id}")
    Flowable<User> getUser(@Path("id") int id);

}
