package com.tatsujin.daggerpractice.ui.auth;

import android.se.omapi.Session;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.tatsujin.daggerpractice.SessionManager;
import com.tatsujin.daggerpractice.models.User;
import com.tatsujin.daggerpractice.network.auth.AuthAPI;

import javax.inject.Inject;

import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class AuthViewModel extends ViewModel {
    private static final String TAG = "AuthViewModel";


    // Inject
    private final AuthAPI authAPI ;
    private SessionManager sessionManager ;

    @Inject
    public AuthViewModel(AuthAPI authAPI , SessionManager sessionManager){
        this.authAPI = authAPI ;
        this.sessionManager = sessionManager ;
        Log.d(TAG , "AuthViewModel : viewmodel is working...");
    }



    public LiveData<AuthResource<User>> observeAuthState(){
        return sessionManager.getAuthUser() ;
    }

    public void authWithID(int userID ){
        Log.d(TAG , "authWithID: Attempting to loging");
        sessionManager.authenticateWithId(queryUserId(userID));
    }

    private LiveData<AuthResource<User>> queryUserId(int userId){
        return LiveDataReactiveStreams.fromPublisher(
                authAPI.getUser(userId)
                        // if an error happens...
                        .onErrorReturn(new Function<Throwable, User>() {
                            @Override
                            public User apply(Throwable throwable) throws Throwable {
                                User errorUser = new User();
                                errorUser.setId(-1);
                                return errorUser ;
                            }
                        })
                        // gets the user or the error user
                        .map(new Function<User, AuthResource<User>>() {
                            @Override
                            public AuthResource<User> apply(User user) throws Throwable {
                                if(user.getId() == -1){
                                    return AuthResource.error("Could not authenticate" , (User) null);
                                }
                                return AuthResource.authenticated(user);
                            }
                        }).subscribeOn(Schedulers.io()));

    }



}
