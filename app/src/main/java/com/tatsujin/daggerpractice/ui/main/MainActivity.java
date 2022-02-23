package com.tatsujin.daggerpractice.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.tatsujin.daggerpractice.BaseActivity;
import com.tatsujin.daggerpractice.R;
import com.tatsujin.daggerpractice.ui.main.post.PostsFragment;
import com.tatsujin.daggerpractice.ui.main.profile.ProfileFragment;

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG , "MainActivity: logged in successfully" );
        testFragment();
    }


    private void testFragment(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_container, new PostsFragment()).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater =  getMenuInflater();
        inflater.inflate(R.menu.main_menu , menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.logout :{
                sessionManager.logOut();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
