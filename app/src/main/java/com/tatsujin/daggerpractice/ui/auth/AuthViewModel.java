package com.tatsujin.daggerpractice.ui.auth;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.tatsujin.daggerpractice.models.User;
import com.tatsujin.daggerpractice.network.auth.AuthAPI;

import javax.inject.Inject;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class AuthViewModel extends ViewModel {

    private final AuthAPI authAPI ;

    private static final String TAG = "AuthViewModel";

    private MediatorLiveData<User> authUser = new MediatorLiveData<>();


    public LiveData<User> observeUser(){
        return authUser ;
    }

    public void authWithID(int userID ){
        final LiveData<User> source  = LiveDataReactiveStreams.fromPublisher(
                authAPI.getUser(userID)
                .subscribeOn(Schedulers.io())
        );
        authUser.addSource(source, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                authUser.setValue(user);
                authUser.removeSource(source);
            }
        });

    }

    @Inject
    public AuthViewModel(AuthAPI authAPI){
        this.authAPI = authAPI ;
        Log.d(TAG , "AuthViewModel : viewmodel is working...");

    }


}
