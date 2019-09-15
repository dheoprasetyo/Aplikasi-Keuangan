package com.destiny.pos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                changeActivity();
                finish();
            }
        }, 0); //3000 L = 3 detik
    }
    private void changeActivity(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
