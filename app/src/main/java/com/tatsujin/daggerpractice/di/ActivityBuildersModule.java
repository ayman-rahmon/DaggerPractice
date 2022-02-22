package com.tatsujin.daggerpractice.di;

import com.tatsujin.daggerpractice.AuthActivity;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {
         // all activities...
    @ContributesAndroidInjector
    abstract AuthActivity contributeAuthActivity();




}
