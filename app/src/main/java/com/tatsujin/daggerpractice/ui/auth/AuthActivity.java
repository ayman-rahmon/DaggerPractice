package com.tatsujin.daggerpractice.ui.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.RequestManager;
import com.tatsujin.daggerpractice.R;
import com.tatsujin.daggerpractice.models.User;
import com.tatsujin.daggerpractice.ui.main.MainActivity;
import com.tatsujin.daggerpractice.viewmodels.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerAppCompatActivity;

public class AuthActivity extends DaggerAppCompatActivity implements View.OnClickListener {

    private static final String TAG = "AuthActivity";
    private AuthViewModel viewModel ;

    private EditText userID ;
    private ProgressBar progressBar;
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
        progressBar = findViewById(R.id.progress_circular);
        setLogo();
        subscribeObservers();
        findViewById(R.id.loginbtn).setOnClickListener(this);


    }


    private void setLogo(){
        requestManager.load(logo).into((ImageView)findViewById(R.id.image));
    }

    private void subscribeObservers(){
        viewModel.observeAuthState().observe(this, new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {
                if(userAuthResource != null){
                    switch(userAuthResource.status){
                        case LOADING :{
                            showProgressbar(true);
                            break ;
                        }
                        case AUTHENTICATED: {
                            showProgressbar(false);
                            Log.d(TAG , "onChanged: login success with : " + userAuthResource.data.getEmail() );
                            onLoginSuccess() ;
                            break ;
                        }
                        case ERROR:{
                            showProgressbar(false);
                            Toast.makeText(AuthActivity.this , userAuthResource.message + "\n Did you enter a number between 1 and 10 ?" , Toast.LENGTH_SHORT).show();
                            break ;
                        }
                        case NOT_AUTHENTICATED: {
                            showProgressbar(false);
                            break ;
                        }
                    }
                }
            }
        });


    }


    private void onLoginSuccess() {
        Intent intent = new Intent(this , MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void showProgressbar(boolean show){
        if(show ){
            progressBar.setVisibility(View.VISIBLE);
        }else {
            progressBar.setVisibility(View.GONE);
        }

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