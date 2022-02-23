package com.tatsujin.daggerpractice.ui.main.profile;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.tatsujin.daggerpractice.SessionManager;
import com.tatsujin.daggerpractice.models.User;
import com.tatsujin.daggerpractice.ui.auth.AuthResource;

import javax.inject.Inject;

public class ProfileViewModel extends ViewModel {
    private static final String TAG = "ProfileViewModel";


    private final SessionManager sessionManager ;


    @Inject
    public ProfileViewModel(SessionManager sessionManager) {
        Log.d(TAG , "ProfileViewModel: viewmodel is ready...");
        this.sessionManager = sessionManager ;
    }

    public LiveData<AuthResource<User>> getAuthenticatedUser() {
        return sessionManager.getAuthUser();
    }


}
