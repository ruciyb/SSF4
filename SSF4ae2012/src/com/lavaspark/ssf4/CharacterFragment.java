package com.lavaspark.ssf4;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.lavaspark.adapter.Move_attr;
import com.lavaspark.adapter.PopWindowListAdapter;
import com.lavaspark.asynctask.LoadImageViewAsyncTask;
import com.lavaspark.db.DBManager;
import com.lavaspark.db.EncryptionDecryption;
import com.lavaspark.service.SetPagedata;
import com.lavaspark.util.GlobalVariables;
import com.lavaspark.util.Utils;

import android.R.integer;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Debug;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class CharacterFragment extends android.support.v4.app.Fragment
implements OnClickListener, OnItemClickListener ,SetPagedata{
	public String[] characters;
	public CallbackDelegate delegate;
	private PopupWindow mPop;
	private View layout;
	private int characterflag = 0;
	private int characterIndex;
	private Button zhaoshi_btn;
	private SQLiteDatabase database;
	private ArrayList<Move_attr> arraymoveList = new ArrayList<Move_attr>();
	private ArrayList<String> frameKeyList;
	private HashMap<String,String> frameMap;
	private ArrayList<HashMap<String,String>> allFrameList;
	public ArrayList<String> nameList ;
	public ArrayList<Bitmap> fragment_bitmaps;
	public Integer[] resid;
	private JSONArray array;
	private ImageView zhaoshiImg;

	public interface CallbackDelegate {
		public void chooseCharacter();

		public void toZhaoShiActivity(Bundle s);

		public void toPanDingActivity(Bundle s);

		// public void toForumActivity(Bundle s);
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		delegate = (CallbackDelegate) getActivity();
		Log.i("lei", "onAttach()....");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		MainActivity.getFrameDataAsyncTask.setSetPagedata(this);
		globalVariable = ((GlobalVariables)getActivity().getApplicationContext());
		View view = inflater.inflate(R.layout.character_fragment, container,
				false);
		Bundle args = getArguments();
		characters = getActivity().getResources().getStringArray(
				R.array.character_name);
		characterIndex = args.getInt("position");
		TextView textView = (TextView) view.findViewById(R.id.textView1);
		zhaoshi_btn = (Button) view.findViewById(R.id.zhaoshi_btn);
		zhaoshiImg = (ImageView)view.findViewById(R.id.zhaoshiImg);
		ImageView set_icon = (ImageView) view.findViewById(R.id.imageView1);
		ImageView icon_bg = (ImageView) view.findViewById(R.id.imageView2);
		ImageView line = (ImageView) view.findViewById(R.id.imageView3);
		Button forum_btn = (Button) view.findViewById(R.id.forum_button);
		layout = View.inflate(getActivity(), R.layout.pop_window, null);
		listView = (ListView) layout.findViewById(R.id.listView1);
		LinearLayout allbg_layout = (LinearLayout)view.findViewById(R.id.fragment_main_linearlayout);
		LinearLayout command_layout = (LinearLayout)view.findViewById(R.id.command_linearlayout);

		resid =new Integer[]{R.drawable.head_able,R.drawable.head_adon,R.drawable.head_akuma,R.drawable.head_balrog,
				R.drawable.head_blanka,R.drawable.head_cammy,R.drawable.head_chun_li,R.drawable.head_cody,R.drawable.head_viper,R.drawable.head_dan,
				R.drawable.head_deejay,R.drawable.head_dhalsim,R.drawable.head_dudley,R.drawable.head_honda,R.drawable.head_el_fuerte,R.drawable.head_evil_ryu,R.drawable.head_fei_long,
				R.drawable.head_gen,R.drawable.head_gouken,R.drawable.head_guile,R.drawable.head_guy,R.drawable.head_hakan,
				R.drawable.head_ibuki,R.drawable.head_juri,R.drawable.head_ken,R.drawable.head_makoto,R.drawable.head_bison,R.drawable.head_oni,
				R.drawable.head_rose,R.drawable.head_rufus,R.drawable.head_ryu,R.drawable.head_sagat,R.drawable.head_sakura,R.drawable.head_seth,
				R.drawable.head_t_hawk,R.drawable.head_vega,R.drawable.head_yang,R.drawable.head_yun,R.drawable.head_zangief};

		allbg_layout.setBackgroundDrawable(getResources().getDrawable(R.drawable.all_bg));
		command_layout.setBackgroundDrawable(getResources().getDrawable(R.drawable.command_bg));


		//		set_icon.setImageDrawable(getResources().getDrawable(resid[characterIndex-1]));
		//		line.setImageDrawable(getResources().getDrawable(R.drawable.line));
		set_icon.setImageBitmap(Utils.readBitMap(getActivity(), resid[characterIndex]));
//		set_icon.setImageBitmap(Utils.readBitMap(getActivity(), resid[characterIndex-1]));
		line.setImageBitmap(Utils.readBitMap(getActivity(), R.drawable.line));

		//		setImageBitmap(set_icon,R.drawable.head_icon_list);
		//		setImageBitmap(line,R.drawable.line);

		//		zhaoshi_btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.menu_slide_pop));
		//		forum_btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.abbg));
		//		zhaoshiImg.setBackgroundDrawable(getResources().getDrawable(R.drawable.zhaoshi));
		zhaoshi_btn.setBackgroundDrawable(Utils.readDrawable(getActivity(), R.drawable.menu_slide_pop));
		forum_btn.setBackgroundDrawable(Utils.readDrawable(getActivity(), R.drawable.abbg));
		zhaoshiImg.setBackgroundDrawable(Utils.readDrawable(getActivity(), R.drawable.zhaoshi));

		icon_bg.setBackgroundColor(0x00EEEEEE);

		textView.setText(characters[characterIndex ]);
//		textView.setText(characters[characterIndex - 1]);
		//		set_icon.getDrawable().setLevel(characterIndex);
		set_icon.setOnClickListener(this);
		forum_btn.setOnClickListener(this);
		zhaoshi_btn.setOnClickListener(this);

		/* 导入布局 */
		layout.setFocusableInTouchMode(true);
		layout.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				Log.d("111", "touch clicked");
				if (mPop.isShowing()) {
					mPop.dismiss();
					mPop.setFocusable(false);
					mPop.setOutsideTouchable(false);
				}
				return false;
			}
		});
		layout.setOnKeyListener(new OnKeyListener() {

			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if (mPop.isShowing()) {
					mPop.dismiss();
					mPop.setFocusable(false);
					mPop.setOutsideTouchable(false);
				}
				return false;
			}
		});
		Log.i("lei", "Character characters[index] = " + characters[characterIndex]);
		if(MainActivity.threadflag){
			threadsetpagedata();
		}
		
		
		return view;
	}

	public void mm(int index){
		
	}

//	Handler mHandler = new Handler () {
//		@Override
//		public void handleMessage(Message msg) {
//			// TODO Auto-generated method stub
//			if(MainActivity.fragment_last_flag != MainActivity.fragment_cur_flag){
//				Log.i("lei", "qian fragment_last_flag = "+MainActivity.fragment_last_flag);
//				Log.i("lei", "qian fragment_cur_flag = "+MainActivity.fragment_cur_flag);
//				mHandler.sendEmptyMessage(0);
//			}else {
//				Log.i("lei", "hou fragment_last_flag = "+MainActivity.fragment_last_flag);
//				Log.i("lei", "hou fragment_cur_flag = "+MainActivity.fragment_cur_flag);
//
//				ArrayList<String> arraylist = globalVariable.getNameList();
//				PopWindowListAdapter adapter = new PopWindowListAdapter(
//						(Context) getActivity(),globalVariable.getNameList());
//				listView.setAdapter(adapter);
//				listView.setOnItemClickListener(CharacterFragment.this);
//				mHandler.removeMessages(0);
//			}
//		}
//
//	};
//
//	Runnable mRunnable = new Runnable() {
//
//		@Override
//		public void run() {
//			// TODO Auto-generated method stub
//			mHandler.sendEmptyMessage(0);
//		}
//	};
	private ListView listView;
	private GlobalVariables globalVariable;

	private void setImageBitmap(ImageView imageView ,int resid){
		LoadImageViewAsyncTask task = new LoadImageViewAsyncTask(getActivity(),resid,imageView);
		task.execute();
	}

	/* 初始化一个弹窗 */
	private void initPopWindow() {
		if (mPop == null) {
			mPop = new PopupWindow(layout, LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT);
		}
		if (mPop.isShowing()) {
			mPop.dismiss();
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		// case R.id.frame_button:
		// Bundle bundle = new Bundle();
		// bundle.putString("character",
		// characters[MainActivity.character_index]);
		// delegate.toFrameDataActivity(bundle);
		// break;
		case R.id.imageView1:
			if (!MainActivity.IS_PAD) {
				delegate.chooseCharacter();
			}
			break;
		case R.id.zhaoshi_btn:
			initPopWindow();
			mPop.setFocusable(true);
			mPop.setAnimationStyle(R.style.AnimationFade);
			// mPop.showAtLocation(
			// getActivity().findViewById(R.id.animation_layout_content),
			// Gravity.TOP|Gravity.RIGHT, 0, 0);
			mPop.showAsDropDown(zhaoshi_btn, 0, 24);
			mPop.setOutsideTouchable(true);
			// Bundle bundle1 = new Bundle();
			// delegate.toZhaoShiActivity(bundle1);
			break;
			//		case R.id.panding_btn:
			//			Bundle bundle2 = new Bundle();
			//			delegate.toPanDingActivity(bundle2);
			//			break;
		case R.id.forum_button:
			Bundle bundle3 = new Bundle();
			bundle3.putString("character",
					characters[MainActivity.character_index]);
			// delegate.toForumActivity(bundle3);
			break;
		default:
			break;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Log.d("test", "item  = " + Integer.valueOf(position).toString());
		Bitmap mBitmap = Utils.readBitMap(getActivity(), R.drawable.head_blanka);
		zhaoshiImg.setImageBitmap(mBitmap);
		initPopWindow();
	}

	@Override
	public void threadsetpagedata() {
		// TODO Auto-generated method stub
		Log.i("lei", "I had reviced a message from interface...");
		Log.i("lei", " characters[index] = " + characters[characterIndex ]+" MainActivity.threadflag = "+MainActivity.threadflag);
		ArrayList<String> arraylist = globalVariable.getDeliverycharacter();
		Iterator b =  arraylist.iterator();
		while (b.hasNext()) {
			Log.i("lava", "b = "+b.next());
		}
		PopWindowListAdapter adapter = new PopWindowListAdapter((Context) getActivity(),globalVariable.getDeliverycharacter());
		listView.setAdapter(adapter);
		adapter.notifyDataSetChanged();
		listView.setOnItemClickListener(this);
	}



}
