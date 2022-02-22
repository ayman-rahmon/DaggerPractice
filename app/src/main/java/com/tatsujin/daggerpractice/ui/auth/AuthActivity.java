package com.tatsujin.daggerpractice.ui.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.RequestManager;
import com.tatsujin.daggerpractice.R;
import com.tatsujin.daggerpractice.models.User;
import com.tatsujin.daggerpractice.viewmodels.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerAppCompatActivity;

public class AuthActivity extends DaggerAppCompatActivity implements View.OnClickListener {

    private static final String TAG = "AuthActivity";
    private AuthViewModel viewModel ;

    private EditText userID ;
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
        userID = findViewById(R.id.user_id_input);
        setLogo();
        subscribeObservers();
        findViewById(R.id.loginbtn).setOnClickListener(this);


    }


    private void setLogo(){
        requestManager.load(logo).into((ImageView)findViewById(R.id.image));
    }

    private void subscribeObservers(){
        viewModel.observeUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                if(user != null){
                    Log.d(TAG , "onChanged(AuthActivity): the user's email is "  + user.getEmail());
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.loginbtn:{
                attemptLogin();
                break ;
            }

        }



    }


    private void attemptLogin(){
        if(TextUtils.isEmpty(userID.getText().toString())){
            return ;
        }
        viewModel.authWithID(Integer.parseInt(userID.getText().toString()));
    }

}