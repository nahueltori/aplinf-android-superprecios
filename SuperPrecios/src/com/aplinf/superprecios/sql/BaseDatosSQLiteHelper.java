package com.aplinf.superprecios.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class BaseDatosSQLiteHelper extends SQLiteOpenHelper {

	public static final String TABLE_PRECIOS = "precios";
	public static final String COL_ID_PRECIO = "precio_id";
	public static final String COL_PRODUCTO = "producto";
	public static final String COL_IMPORTE = "importe";

	private static final String DATABASE_NAME = "superprecios.db";
	private static final int DATABASE_VERSION = 1;

	// Database creation sql statement
	private static final String DATABASE_CREATE = "create table "
			+ TABLE_PRECIOS + "(" + COL_ID_PRECIO + " integer primary key autoincrement, " 
			+ COL_PRODUCTO + " integer not null, "
			+ COL_IMPORTE + " real not null);";

	public BaseDatosSQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(BaseDatosSQLiteHelper.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRECIOS);
		onCreate(db);
	}
}
