package com.aplinf.superprecios.core;

import java.util.List;

import android.content.Context;

import com.aplinf.superprecios.sql.PreciosDataSource;

public class SistemaPrecios {

	private ListaDePrecios sistema;
	
	private PreciosDataSource datasource;
	
	public SistemaPrecios(Context context){
		datasource = new PreciosDataSource(context);
		sistema = new ListaDePrecios(datasource);
	}
	
	public void agregarPrecio(Precio precio){
		sistema.agregarPrecio(precio);
	}
	
	public List<Precio> compararPrecio(Estrategia estrategia){
		return sistema.compararPrecio(estrategia);
	}
	
}
