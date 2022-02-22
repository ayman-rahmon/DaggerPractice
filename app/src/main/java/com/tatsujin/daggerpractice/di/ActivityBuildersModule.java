package com.tatsujin.daggerpractice.di;

import com.tatsujin.daggerpractice.di.auth.AuthViewModelsModule;
import com.tatsujin.daggerpractice.ui.auth.AuthActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {
         // all activities...
    @ContributesAndroidInjector(
            modules = {AuthViewModelsModule.class}
    )
    abstract AuthActivity contributeAuthActivity();




}
