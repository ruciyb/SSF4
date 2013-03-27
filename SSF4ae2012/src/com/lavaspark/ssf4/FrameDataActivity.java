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
	public List<String> nameList ;
	public List<String> frameKeyList;
	public List<HashMap<String, String>> allFrameList;
	public ListView listView;
	private EncryptionDecryption encryptionDecryption;
	private ArrayList<Move_attr> arraymoveList = new ArrayList<Move_attr>();
	private String Deliver_str = "";
	private JSONArray array;
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
		try {
			query();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		database.close();
		mListAdapter adapter = new mListAdapter(this, arraymoveList);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				System.out.println(" name 1 = "+nameList.get(arg2));
				JSONObject jsonObject = null ;
				try {
					 jsonObject = array.getJSONObject(arg2);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Intent intent = new Intent(FrameDataActivity.this, EveryMoveattr.class);
				intent.putExtra("number", arg2);
				intent.putExtra("content", jsonObject.toString());
				startActivity(intent);
			}
		});
	}

	public void query() throws Exception {
		Cursor cursor_1 = database.rawQuery("select * from framedata where name = ?"
				,new String[]{character_name});
		Log.i("Test", "database1 = "+database);
		while(cursor_1.moveToNext()){
			String cursorString = cursor_1.getString(cursor_1.getColumnIndex("frameinfo"));
			Log.d("ZL","cursorString = "+cursorString);
			EncryptionDecryption encryptionDecryption = new EncryptionDecryption("lavaspark");
			String decryptString= encryptionDecryption.decrypt(cursorString);
			Log.d("Test","jsonString = " + decryptString);
			jsonPhaser(decryptString);
		}
		cursor_1.close();
	}

	public void jsonPhaser(String jsonString){
		try {
			array = new JSONArray(jsonString);
			nameList = new ArrayList<String>();
			allFrameList = new ArrayList<HashMap<String,String>>();

			for (int i = 0; i < array.length(); i++) {
				JSONObject object = array.getJSONObject(i);
				//				Log.d("lavaspark",object.toString());
				for (Iterator<String> iterator = object.keys(); iterator.hasNext();) {
					Move_attr move_attr = new Move_attr();

					String key = iterator.next();
					nameList.add(key);
					JSONArray frameaArray = object.getJSONArray(key);
					frameKeyList = new ArrayList<String>();
					frameMap = new HashMap<String, String>();
					for (int j = 0; j < frameaArray.length(); j++) {
						JSONObject lastdata = frameaArray.getJSONObject(j);
						for (Iterator<String> iteratorNext = lastdata.keys(); iteratorNext.hasNext();) {
							String frameKey = iteratorNext.next();
							frameKeyList.add(frameKey);
							frameMap.put(frameKey, lastdata.getString(frameKey));
							if("Moves".equals(frameKey)){
								move_attr.setmMove_Name(lastdata.getString(frameKey));
							}else if("Startup".equals(frameKey)){
								move_attr.setmStartup(lastdata.getString(frameKey));
							}else if("FrameAdvHit".equals(frameKey)){
								move_attr.setmOnHit(lastdata.getString(frameKey));
							}else if("FrameAdvBlock".equals(frameKey)){
								move_attr.setmOnGruard(lastdata.getString(frameKey));
							}
						}	
					}
					arraymoveList.add(move_attr);
					allFrameList.add(frameMap);
				}				
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		Log.d("lavaspark", nameList.toString());
		Log.d("lavaspark", frameKeyList.toString());
		Log.d("lavaspark", allFrameList.toString());
		Deliver_str = allFrameList.toString();

	}

	
}