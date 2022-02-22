package com.tatsujin.daggerpractice.ui.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.RequestManager;
import com.tatsujin.daggerpractice.R;
import com.tatsujin.daggerpractice.viewmodels.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerAppCompatActivity;

public class AuthActivity extends DaggerAppCompatActivity {

    private static final String TAG = "AuthActivity";
    private AuthViewModel viewModel ;

    @Inject
    ViewModelProviderFactory providerFactory ;
    @Inject
    Drawable logo ;

    @Inject
    RequestManager requestManager ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        viewModel = new ViewModelProvider(this, providerFactory).get(AuthViewModel.class);
        setLogo();




    }


    private void setLogo(){
        requestManager.load(logo).into((ImageView)findViewById(R.id.image));
    }



}