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

import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class AuthViewModel extends ViewModel {

    private final AuthAPI authAPI ;

    private static final String TAG = "AuthViewModel";

    private MediatorLiveData<AuthResource<User>> authUser = new MediatorLiveData<>();


    public LiveData<AuthResource<User>> observeUser(){
        return authUser ;
    }

    public void authWithID(int userID ){
        // tell the ui ... an attempt is being made...
        authUser.setValue(AuthResource.loading((User)null));

        final LiveData<AuthResource<User>> source = LiveDataReactiveStreams.fromPublisher(
                authAPI.getUser(userID)
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

        authUser.addSource(source, new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> resource) {
                authUser.setValue(resource);
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
