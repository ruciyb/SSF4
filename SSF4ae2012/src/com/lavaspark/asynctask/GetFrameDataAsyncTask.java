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

public class GetFrameDataAsyncTask extends AsyncTask<Integer, Integer, Integer>{
	
	Context context;
	String[] characters;
	SetPagedata setPagedata;
	
	public void setSetPagedata(SetPagedata setPagedata) {
		this.setPagedata = setPagedata;
	}

	public GetFrameDataAsyncTask(Context context) {
		super();
		this.context = context;
		GlobalVariables globalVariable = ((GlobalVariables)context.getApplicationContext());
		characters = globalVariable.getGcharacters();
	}

	@Override
	protected Integer doInBackground(Integer... params) {
		// TODO Auto-generated method stub
		Log.i("lei", "thread back params = "+params[0]);
		try {
			DBManager.getdbManger(context).querydata(characters[params[0] ], "jsonPhaserName");
			} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		GlobalVariables globalVariable = ((GlobalVariables)context.getApplicationContext());
		ArrayList<String> a = globalVariable.getNameList();
		Iterator b =  a.iterator();
		while (b.hasNext()) {
			Log.i("tang", "b = "+b.next());
		}
		Log.i("lei","没有异常，准备要求加载完毕....	");
		while(true){
			if(setPagedata != null){
				setPagedata.threadsetpagedata();
				break;
			}
		}
		Log.i("lei","加载完毕....	");
		return params[0];
	}

	@Override
	protected void onPostExecute(Integer result) {
		// TODO Auto-generated method stub
		Log.i("lei", "thread result params = "+result);
		MainActivity.fragment_last_flag = result;
		
		
	}
	

}
