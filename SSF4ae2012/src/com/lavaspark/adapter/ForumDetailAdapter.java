package com.lavaspark.adapter;

import java.util.ArrayList;
import java.util.HashMap;

import com.lavaspark.asynctask.PutEvaluateAsyncTask;
import com.lavaspark.ssf4.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ForumDetailAdapter extends BaseAdapter implements OnClickListener{
	public Context context;
	public HashMap<String, String> videoMap;
	public ArrayList<HashMap<String, String>> itemlist;
	public String infoString;
	public ForumDetailAdapter(Context context,ArrayList<HashMap<String, String>> list,String infoString) {
		this.context = context;
		this.itemlist = list;
		this.infoString = infoString;
	}

	@Override
	public int getCount() {
		return itemlist.size();
	}

	@Override
	public HashMap<String, String> getItem(int position) {
		return itemlist.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Viewholder holder = null;
		if (convertView == null) {
		convertView  = LayoutInflater.from(context).inflate(R.layout.forum_detail_list_layout, null);
		holder = new Viewholder();
		holder.image = (ImageView) convertView.findViewById(R.id.imageView1);
		holder.text1 = (TextView)convertView.findViewById(R.id.textView1);
		holder.text2 = (TextView)convertView.findViewById(R.id.textView2);
		holder.text3 = (TextView)convertView.findViewById(R.id.textView3);
		holder.text4 = (TextView)convertView.findViewById(R.id.textView4);
		holder.text5 = (TextView)convertView.findViewById(R.id.textView5);
		holder.text6 = (TextView)convertView.findViewById(R.id.textView6);

		holder.btn1 = (Button)convertView.findViewById(R.id.button1);
		holder.btn2 = (Button)convertView.findViewById(R.id.button2);
		convertView.setTag(holder);
	}else {
		holder = (Viewholder)convertView.getTag();
	}
		holder.text1.setText(getItem(position).get("title"));
		holder.text2.setText(getItem(position).get("content"));
		holder.text3.setText(getItem(position).get("poster"));
		holder.text4.setText(getItem(position).get("datetime"));
		holder.text5.setText("good:"+getItem(position).get("good"));
		holder.text6.setText("bad:"+getItem(position).get("bad"));
		holder.image.setImageDrawable(context.getResources().getDrawable(R.drawable.forum));
		holder.btn1.setOnClickListener(this);
		holder.btn2.setOnClickListener(this);
		return convertView;
	}

	public static class Viewholder{
		private ImageView image;
		private TextView text1;
		private TextView text2;
		private TextView text3;
		private TextView text4;
		private TextView text5;
		private TextView text6;
		private Button btn1;
		private Button btn2;


	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button1:
			Toast.makeText(context, "good", Toast.LENGTH_SHORT).show();
			PutEvaluateAsyncTask task = new PutEvaluateAsyncTask();
			task.execute(infoString,"1");
			break;
		case R.id.button2:
			Toast.makeText(context, "bad", Toast.LENGTH_SHORT).show();
			PutEvaluateAsyncTask task2 = new PutEvaluateAsyncTask();
			task2.execute(infoString,"0");
			break;

		default:
			break;
		}
	}

}
