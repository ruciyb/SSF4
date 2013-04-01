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

	public  final String DB_NAME = "framedatabase.db"; // 保存的数据库文件名
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
			Log.i("lei", "操作数据库的参数为空...");
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
			geteverycharacterdata();
		}
	}

	private void geteverycharacterdata(){
		GlobalVariables globalVariable = ((GlobalVariables)getApplicationContext());
		String[] allcharacter =  globalVariable.getcharacters();
		HashMap<String, ArrayList<String>> hashmap = globalVariable.getAllcharacters();
		for(String one: allcharacter){
			try {
				
				ArrayList<String> characterfromdb = (ArrayList<String>) DBManager.getdbManger(getApplicationContext()).querydata(one, "jsonPhaserName");
			//	HashMap<String, ArrayList<String>> hashmap = new HashMap<String, ArrayList<String>>();
				hashmap.put(one, characterfromdb);
				globalVariable.setAllcharacters(hashmap);
				Log.i("elfake", " have added one = "+one);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
