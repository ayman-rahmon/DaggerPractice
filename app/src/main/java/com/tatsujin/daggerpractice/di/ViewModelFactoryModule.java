package com.tatsujin.daggerpractice.di;

import androidx.lifecycle.ViewModelProvider;

import com.tatsujin.daggerpractice.viewmodels.ViewModelProviderFactory;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class ViewModelFactoryModule {

    // provides a dependency without adding any logic to it
    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory modelProviderFactory);



    //provides a dependency with adding some kind od logic to it...
    @Provides
    static ViewModelProvider.Factory bindFactory(ViewModelProviderFactory factory){
        return factory ;
    }

}
