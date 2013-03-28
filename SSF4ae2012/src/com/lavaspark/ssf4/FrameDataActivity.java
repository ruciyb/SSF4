package com.lavaspark.ssf4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.lavaspark.adapter.FrameListAdapter;
import com.lavaspark.adapter.Move_attr;
import com.lavaspark.adapter.mListAdapter;
import com.lavaspark.db.DBManager;
import com.lavaspark.db.EncryptionDecryption;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class FrameDataActivity extends Activity {
	private SQLiteDatabase database;
	private ArrayList<String> playerone_move_name_list = new ArrayList<String>();
	private TextView jsonText;
	private String tablename;
	public HashMap<String,String> frameMap;

	public ListView listView;
	private EncryptionDecryption encryptionDecryption;
	
	private String Deliver_str = "";
	
	private String character_name;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.framedata_list_layout);
		Log.i("ZL", "get databases data");
		ActionBar bar = getActionBar();
		bar.setDisplayHomeAsUpEnabled(true);
		Bundle bundle = getIntent().getExtras();
		character_name = bundle.getString("character");
		listView = (ListView) findViewById(R.id.move_listview);
		database = SQLiteDatabase.openOrCreateDatabase(DBManager.DB_PATH + "/"
				+ DBManager.DB_NAME, null);
		Log.i("Test", "database = "+database);
		database.close();
	//	mListAdapter adapter = new mListAdapter(this, arraymoveList);
	//	listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
//	//			System.out.println(" name 1 = "+nameList.get(arg2));
//				JSONObject jsonObject = null ;
//				try {
//	//				 jsonObject = array.getJSONObject(arg2);
//				} catch (JSONException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				Intent intent = new Intent(FrameDataActivity.this, EveryMoveattr.class);
//				intent.putExtra("number", arg2);
//				intent.putExtra("content", jsonObject.toString());
//				startActivity(intent);
			}
		});
	}



	

	
}