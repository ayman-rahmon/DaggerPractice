package com.tatsujin.daggerpractice;

import android.content.Intent;
import android.os.Bundle;
import android.se.omapi.Session;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.tatsujin.daggerpractice.models.User;
import com.tatsujin.daggerpractice.ui.auth.AuthActivity;
import com.tatsujin.daggerpractice.ui.auth.AuthResource;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public abstract class BaseActivity extends DaggerAppCompatActivity {


    private static final String TAG = "BaseActivity";

    @Inject
    public SessionManager sessionManager ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        subscribeObservers();

    }
    private void subscribeObservers(){
        sessionManager.getAuthUser().observe(this, new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {
                if(userAuthResource != null){
                    switch(userAuthResource.status){
                        case LOADING :{
                            break ;
                        }
                        case AUTHENTICATED: {
                            Log.d(TAG , "onChanged: login success with : " + userAuthResource.data.getEmail() );
                            break ;
                        }
                        case ERROR:{
                            Toast.makeText(BaseActivity.this , userAuthResource.message + "\n Did you enter a number between 1 and 10 ?" , Toast.LENGTH_SHORT).show();
                            break ;
                        }
                        case NOT_AUTHENTICATED: {
                            navLoginScreen();
                            break ;
                        }
                    }
                }

            }
        });
    }

    private void navLoginScreen(){
        Intent intent = new Intent(this , AuthActivity.class);
        startActivity(intent);
        finish();
    }

}
