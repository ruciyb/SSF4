package com.lavaspark.ssf4;

import com.lavaspark.db.DBManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends Activity {
	private final int SPLASH_DISPLAY_LENGHT = 2000; 
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        /**
         * 使用handler来处理
         */
        new Handler().postDelayed(new Runnable(){

         @Override
         public void run() {
             Intent mainIntent = new Intent(SplashActivity.this,MainActivity.class); 
             SplashActivity.this.startActivity(mainIntent);
             SplashActivity.this.finish(); 
             DBManager dbManager = new DBManager(getApplicationContext());
     		dbManager.openDatabase();
     		dbManager.database.close();
         }
           
        }, SPLASH_DISPLAY_LENGHT);
    }
}
