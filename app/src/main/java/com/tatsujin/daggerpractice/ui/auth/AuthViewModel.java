package com.tatsujin.daggerpractice.ui.auth;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.tatsujin.daggerpractice.network.auth.AuthAPI;

import javax.inject.Inject;

public class AuthViewModel extends ViewModel {

    private final AuthAPI authAPI ;

    private static final String TAG = "AuthViewModel";
    @Inject
    public AuthViewModel(AuthAPI authAPI){
        this.authAPI = authAPI ;
        Log.d(TAG , "AuthViewModel : viewmodel is working...");
        if(this.authAPI == null ){
            Log.d(TAG , "AuthViewModel : auth api is null...");
        }else {
            Log.d(TAG , "AuthViewModel : auth api  is not  null...");
        }
    }


}
