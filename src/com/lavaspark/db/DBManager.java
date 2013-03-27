package com.lavaspark.db;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import com.lavaspark.ssf4.R;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

public class DBManager {
	private final int BUFFER_SIZE = 400000;
	public static final String DB_NAME = "framedatabase.db"; // 保存的数据库文件名
	public static final String PACKAGE_NAME = "com.lavaspark.ssf4";
	public static final String DB_PATH = "/data"
			+ Environment.getDataDirectory().getAbsolutePath() + "/"
			+ PACKAGE_NAME; // 在手机里存放数据库的位置
	public SQLiteDatabase database;
	private Context context;
	public DBManager(Context context) {
		this.context = context;
	}

	public void openDatabase() {
		this.database = this.openDatabase(DB_PATH + "/" + DB_NAME);
		Log.d("DB_PATH + DB_NAME", DB_PATH + "/" + DB_NAME);
	}

	public SQLiteDatabase openDatabase(String dbfile) {
		try {
			if (!(new File(dbfile).exists())) {
				InputStream is = this.context.getResources().openRawResource(
						R.raw.framedatabase); // 欲导入的数据库
				FileOutputStream fos = new FileOutputStream(dbfile);
				byte[] buffer = new byte[BUFFER_SIZE];
				int count = 0;
				while ((count = is.read(buffer)) > 0) {
					fos.write(buffer, 0, count);
				}
				fos.close();
				is.close();
			}
			SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(dbfile,
					null);
			return db;
		} catch (FileNotFoundException e) {
			Log.e("Database", "File not found");
			e.printStackTrace();
		} catch (IOException e) {
			Log.e("Database", "IO exception");
			e.printStackTrace();
		}
		return null;
	}
}