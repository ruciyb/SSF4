package com.lavaspark.service;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import com.lavaspark.db.DBManager;
import com.lavaspark.ssf4.R;
import com.lavaspark.util.GlobalVariables;

import android.app.IntentService;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class MintentService extends IntentService{

	public  final String DB_NAME = "framedatabase.db"; // ��������ݿ��ļ���
	public  final String PACKAGE_NAME = "com.lavaspark.ssf4";
	public MintentService() {
		super("Test");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		// TODO Auto-generated method stub
		String mParameter = intent.getStringExtra("parameter");
		dosomethingfromParameter(mParameter);
	}

	private void dosomethingfromParameter(String parameter){
		if(parameter == null){
			Log.i("lei", "�������ݿ�Ĳ���Ϊ��...");
			return ;
		}
		if("createdb".equals(parameter)){
			String dbpath ="/data/data/"+PACKAGE_NAME+"/"+DB_NAME;
			if( (new File(dbpath)).exists() ){
				SQLiteDatabase sqldb = SQLiteDatabase.openOrCreateDatabase(dbpath, null);
				DBManager.database = sqldb;
			}else {
				InputStream resoucein =  getResources().openRawResource(R.raw.framedatabase);
				DBManager.getdbManger(getApplicationContext()).importdatabase(dbpath, resoucein);
				SQLiteDatabase sqldb = SQLiteDatabase.openOrCreateDatabase(dbpath, null);
				DBManager.database = sqldb;
			}
		
		}
	}

}
