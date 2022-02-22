package com.tatsujin.daggerpractice.di;

import android.app.Application;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
// anything that should stay static through the life of the whole application... other than activites...
    @Provides
    static String someString(){
        return "this is a test";
    }

    @Provides
    static boolean getApp(Application application){
        return application == null;
    }

}
