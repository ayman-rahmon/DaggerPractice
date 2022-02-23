package com.tatsujin.daggerpractice;


import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import com.tatsujin.daggerpractice.models.User;
import com.tatsujin.daggerpractice.ui.auth.AuthResource;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SessionManager {

    private static final String TAG = "SessionManager";

    // keep track of the Authenticated user... instead of the Auth View Model
    private MediatorLiveData<AuthResource<User>> cachedUser = new MediatorLiveData<>();


    @Inject
    public SessionManager() {
    }


    public void authenticateWithId(final LiveData<AuthResource<User>> source){
        if(cachedUser != null){
            cachedUser.setValue(AuthResource.loading((User)null));
            cachedUser.addSource(source, new Observer<AuthResource<User>>() {
                @Override
                public void onChanged(AuthResource<User> userAuthResource) {
                    cachedUser.setValue(userAuthResource);
                    cachedUser.removeSource(source);
                }
            });
        }else{
            Log.d(TAG , "authenticatedWithId: previous session detected. Retrieving user from cache.");
        }
    }


    public void logOut(){
        Log.d(TAG ,"logOut : logging out");
        cachedUser.setValue(AuthResource.logout());
    }

    public LiveData<AuthResource<User>> getAuthUser(){
        return cachedUser;
    }

}
