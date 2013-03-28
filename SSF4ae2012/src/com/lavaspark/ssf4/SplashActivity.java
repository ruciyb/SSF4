package com.lavaspark.ssf4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.lavaspark.db.DBManager;
import com.lavaspark.service.MintentService;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

public class SplashActivity extends Activity {
	private final int SPLASH_DISPLAY_LENGHT = 2000; 
	private final int BUFFER_SIZE = 400000;
	public static final String DB_NAME = "framedatabase.db"; // 保存的数据库文件名
	public static final String PACKAGE_NAME = "com.lavaspark.ssf4";
	public static final String DB_PATH = "/data";
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        ImportDatabase(DB_PATH + "/" + DB_NAME);
        Log.i("lei","Current Thread is "+Thread.currentThread().getName());
//        Intent intent = new Intent(this, MintentService.class);
//        intent.putExtra("parameter", "createdb");
//        startService(intent);
        
        /**
         * 使用handler来处理
         */
        new Handler().postDelayed(new Runnable(){

         @Override
         public void run() {
             Intent mainIntent = new Intent(SplashActivity.this,MainActivity.class); 
             SplashActivity.this.startActivity(mainIntent);
             SplashActivity.this.finish(); 
             
//             DBManager dbManager = new DBManager(getApplicationContext());
//     		dbManager.openDatabase();
//     		dbManager.database.close();
         }
           
        }, SPLASH_DISPLAY_LENGHT);
    }
    
    public void ImportDatabase(String dbfile) {
		Log.i("lei", "dbfile is = "+dbfile);
		File dbFile = new File(dbfile);
		try {
			if (!dbFile.exists()) {
				//dbFile.createNewFile();
				InputStream is = getResources().openRawResource(
						R.raw.framedatabase); // 欲导入的数据库
				FileOutputStream fos = new FileOutputStream(dbfile);
				byte[] buffer = new byte[BUFFER_SIZE];
				int count = 0;
				while ((count = is.read(buffer)) > 0) {
					fos.write(buffer, 0, count);
				}
				fos.close();
				is.close();
				SQLiteDatabase db =  SQLiteDatabase.openOrCreateDatabase(dbfile,
						null);
				DBManager.database = db;
				Log.i("lei", "db = "+db.getPath()+"     aaa ="+db);
				
//				return db;
			}else {
				SQLiteDatabase db =  SQLiteDatabase.openOrCreateDatabase(dbfile,
						null);
				DBManager.database = db;
				Log.i("lei", "db = "+db.getPath()+"     aaa ="+db);
			}

		} catch (FileNotFoundException e) {
			Log.e("Database", "File not found");
			e.printStackTrace();
		} catch (IOException e) {
			Log.e("Database", "IO exception");
			e.printStackTrace();
		}
//		return null;
	}
}
