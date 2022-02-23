package com.tatsujin.daggerpractice.di;

import com.tatsujin.daggerpractice.di.auth.AuthModule;
import com.tatsujin.daggerpractice.di.auth.AuthViewModelsModule;
import com.tatsujin.daggerpractice.di.main.MainFragmentBuildersModule;
import com.tatsujin.daggerpractice.di.main.MainModule;
import com.tatsujin.daggerpractice.di.main.MainViewModelModule;
import com.tatsujin.daggerpractice.ui.auth.AuthActivity;
import com.tatsujin.daggerpractice.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {
         // all activities... this is a sub component...
    @ContributesAndroidInjector(
            modules = {AuthViewModelsModule.class , AuthModule.class}
    )
    abstract AuthActivity contributeAuthActivity();


    @ContributesAndroidInjector(
            modules = {MainFragmentBuildersModule.class , MainViewModelModule.class , MainModule.class}
    )
    abstract MainActivity contributeMainActivity();



}
