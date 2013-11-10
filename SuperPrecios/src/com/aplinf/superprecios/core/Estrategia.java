package com.aplinf.superprecios.core;

public abstract class Estrategia {

	protected ListaDePrecios sistema;
	
	public abstract Precio compararPrecio();
	
	public void setSistema(ListaDePrecios sistema){
		this.sistema = sistema;
	}
}
