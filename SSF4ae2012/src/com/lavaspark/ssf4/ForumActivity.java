package com.lavaspark.ssf4;

import com.lavaspark.adapter.ForumAdapter;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class ForumActivity extends Activity implements OnItemClickListener,android.view.View.OnClickListener{
	public ListView listView;
	public ForumAdapter adapter; 
	public TextView textView;
	public  String [] characters;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.forum_layout);
		listView = (ListView) findViewById(R.id.listView1);
		textView=(TextView) findViewById(R.id.textView1);
		Bundle bundle = getIntent().getExtras();
		characters = getResources().getStringArray(R.array.character_name);
		String bundleString = bundle.getString("character");
		textView.setText(bundleString);
		textView.setOnClickListener(this);
		for (int i = 0; i < characters.length; i++) {
			String s = bundleString;
			s=s.concat("_"+characters[i]);
			characters[i]=s;
		}
		adapter = new ForumAdapter(ForumActivity.this, characters);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
	}
		

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.textView1:
			for (int i = 0; i < characters.length; i++) {
				Log.d("test", characters[i]);				
			}
			break;

		default:
			break;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View v, int position, long arg3) {
			String vsInfoString = characters[position]; 
			Log.d("test", vsInfoString);
			Intent intentanother = new Intent(ForumActivity.this, ForumDetailActivity.class);
			intentanother.putExtra("info", vsInfoString);
			startActivity(intentanother);
		}
	

}
