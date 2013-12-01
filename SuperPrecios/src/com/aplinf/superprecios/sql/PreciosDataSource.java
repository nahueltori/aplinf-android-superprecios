package com.aplinf.superprecios.sql;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.aplinf.superprecios.core.Precio;
import com.aplinf.superprecios.core.Producto;

public class PreciosDataSource {

	// Database fields
	private SQLiteDatabase database;
	private BaseDatosSQLiteHelper dbHelper;
	private String[] allColumns = { 
			BaseDatosSQLiteHelper.COL_ID_PRECIO,
			BaseDatosSQLiteHelper.COL_PRODUCTO, 
			BaseDatosSQLiteHelper.COL_IMPORTE};

	public PreciosDataSource(Context context) {
		dbHelper = new BaseDatosSQLiteHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public boolean crearPrecio(Precio nuevoPrecio) {
		ContentValues values = new ContentValues();
		values.put(BaseDatosSQLiteHelper.COL_PRODUCTO, nuevoPrecio.getProducto().getCodigo());
		values.put(BaseDatosSQLiteHelper.COL_IMPORTE, nuevoPrecio.getImporte());
		long insertId = database.insert(BaseDatosSQLiteHelper.TABLE_PRECIOS, null,
				values);
		nuevoPrecio.setId(insertId);
		Cursor cursor = database.query(BaseDatosSQLiteHelper.TABLE_PRECIOS,
				allColumns, BaseDatosSQLiteHelper.COL_ID_PRECIO + " = " + insertId, null,
				null, null, null);
		boolean resultado = cursor.moveToFirst();
		cursor.close();
		return resultado;
	}

	public void deleteComment(Precio precio) {
		long id = precio.getId();
		System.out.println("Comment deleted with id: " + id);
		database.delete(BaseDatosSQLiteHelper.TABLE_PRECIOS, BaseDatosSQLiteHelper.COL_ID_PRECIO
				+ " = " + id, null);
	}

	public List<Precio> getPrecios(Producto producto) {
		List<Precio> precios = new ArrayList<Precio>();
		String[] args = new String[] {Long.toString(producto.getCodigo())};
		Cursor cursor = database.query(BaseDatosSQLiteHelper.TABLE_PRECIOS,
				allColumns, BaseDatosSQLiteHelper.COL_PRODUCTO + "=?", args, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Precio precio = cursorToPrecio(cursor);
			precios.add(precio);
			cursor.moveToNext();
		}
		// make sure to close the cursor
		cursor.close();
		return precios;
	}

	private Precio cursorToPrecio(Cursor cursor) {
		long producto = cursor.getLong(1);
		double importe = cursor.getDouble(2);
		Precio precio = new Precio(importe, new Producto(producto));
		precio.setId(cursor.getLong(0));
		return precio;
	}
}
