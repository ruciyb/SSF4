package com.lavaspark.service;

import com.lavaspark.db.DBManager;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class MintentService extends IntentService{

	public   String DB_NAME = "framedatabase.db"; // 保存的数据库文件名
	public   String PACKAGE_NAME = "com.lavaspark.ssf4";
	public   String DB_PATH = "/data";
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
			Log.i("lei", "操作数据库要导入了。");
		//	DBManager.getdbManger(getApplicationContext()).ImportDatabase(DB_PATH + "/" + DB_NAME);
		}
	}

}
