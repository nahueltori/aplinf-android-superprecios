package com.aplinf.superprecios.core;

import java.util.List;

public abstract class Estrategia {

	protected ListaDePrecios sistema;
	
	public abstract List<Precio> compararPrecio();
	
	public void setSistema(ListaDePrecios sistema){
		this.sistema = sistema;
	}
}
