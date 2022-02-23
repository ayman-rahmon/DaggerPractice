package com.tatsujin.daggerpractice.network.main;

import com.tatsujin.daggerpractice.models.Post;
import com.tatsujin.daggerpractice.ui.main.Resource;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MainAPI {

    // posts?userId=1
    @GET("posts")
    Flowable<List<Post>> getPostsFromUser(@Query("userId") int id);






}
