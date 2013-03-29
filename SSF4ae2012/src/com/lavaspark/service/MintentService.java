package com.lavaspark.service;

import java.io.File;
import java.io.InputStream;

import com.lavaspark.db.DBManager;
import com.lavaspark.ssf4.R;

import android.app.IntentService;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class MintentService extends IntentService{

	public  final String DB_NAME = "framedatabase.db"; // 保存的数据库文件名
	public  final String PACKAGE_NAME = "com.lavaspark.ssf4";
	public MintentService() {
		super("Test");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		// TODO Auto-generated method stub
		Log.i("lei","Current Thread is "+Thread.currentThread().getName());
		String mParameter = intent.getStringExtra("parameter");
		Log.i("lei","ACurrent Thread is "+mParameter);
		dosomethingfromParameter(mParameter);
	}
	
	private void dosomethingfromParameter(String parameter){
		if(parameter == null){
			Log.i("lei", "操作数据库的参数为空...");
			return ;
		}
		if("createdb".equals(parameter)){
			String dbpath ="/data/data/"+PACKAGE_NAME+"/"+DB_NAME;
			if( (new File(dbpath)).exists() ){
				Log.i("lei", "数据库已经导入过了");
				SQLiteDatabase sqldb = SQLiteDatabase.openOrCreateDatabase(dbpath, null);
				DBManager.database = sqldb;
				return ;
			}else {
				Log.i("lei", "操作数据库要导入了。");
				InputStream resoucein =  getResources().openRawResource(R.raw.framedatabase);
				DBManager.getdbManger(getApplicationContext()).importdatabase(dbpath, resoucein);
				SQLiteDatabase sqldb = SQLiteDatabase.openOrCreateDatabase(dbpath, null);
				DBManager.database = sqldb;
			}
		}
	}

}
