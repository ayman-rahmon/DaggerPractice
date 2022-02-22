package com.tatsujin.daggerpractice.di.auth;

import androidx.lifecycle.ViewModel;

import com.tatsujin.daggerpractice.di.ViewModelKey;
import com.tatsujin.daggerpractice.ui.auth.AuthViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class AuthViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel.class)
    public abstract ViewModel bindViewModel(AuthViewModel viewModel);



}
