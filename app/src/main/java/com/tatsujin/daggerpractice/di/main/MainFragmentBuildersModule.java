package com.tatsujin.daggerpractice.di.main;


import com.tatsujin.daggerpractice.ui.main.post.PostsFragment;
import com.tatsujin.daggerpractice.ui.main.profile.ProfileFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract ProfileFragment contributeProfileFragment();


    @ContributesAndroidInjector
    abstract PostsFragment contributePostsFragment();

}
