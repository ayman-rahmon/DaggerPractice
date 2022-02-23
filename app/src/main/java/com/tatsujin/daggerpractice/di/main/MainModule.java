package com.tatsujin.daggerpractice.di.main;

import com.tatsujin.daggerpractice.network.main.MainAPI;
import com.tatsujin.daggerpractice.ui.main.post.PostRecyclerAdapter;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class MainModule {

    @MainScope
    @Provides
    static MainAPI provideMainAPI(Retrofit retrofit){
        return retrofit.create(MainAPI.class);
    }
    
    @MainScope
    @Provides
    static PostRecyclerAdapter provideAdapter(){
        return new PostRecyclerAdapter();
    }

}
