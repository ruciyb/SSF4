package com.lavaspark.asynctask;

import java.util.ArrayList;
import java.util.Iterator;

import com.lavaspark.db.DBManager;
import com.lavaspark.service.SetPagedata;
import com.lavaspark.ssf4.MainActivity;
import com.lavaspark.util.GlobalVariables;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class GetFrameDataAsyncTask extends AsyncTask<String, Integer, String>{
	
	Context context;
	String[] characters;
	SetPagedata setPagedata;
	int nex = 0;
	
	public void setSetPagedata(SetPagedata setPagedata) {
		if( nex == 0){
			this.setPagedata = setPagedata;
			nex = 1;
		}
		
	}

	public GetFrameDataAsyncTask(Context context) {
		super();
		this.context = context;
		GlobalVariables globalVariable = ((GlobalVariables)context.getApplicationContext());
		characters = globalVariable.getcharacters();
	}

	@Override
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub
		GlobalVariables globalVariable = ((GlobalVariables)context.getApplicationContext());
		try {
			ArrayList<String> arraylist  =  (ArrayList<String>) DBManager.getdbManger(context).querydata(params[0], "jsonPhaserName");
			globalVariable.setDeliverycharacter(arraylist);
			} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Log.i("lei","没有异常，准备要求加载完毕....	");
		while(true){
			if(setPagedata != null){
				setPagedata.threadsetpagedata();
				break;
			}
		}
		nex = 0;
		Log.i("lei","加载完毕....	");
		return params[0];
	}

	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
	}
	

}
