package com.aplinf.superprecios.core;

import java.util.List;

public class SistemaPrecios {

	private ListaDePrecios sistema = new ListaDePrecios();
	
	public void agregarPrecio(Precio precio){
		sistema.agregarPrecio(precio);
	}
	
	public List<Precio> compararPrecio(Estrategia estrategia){
		return sistema.compararPrecio(estrategia);
	}
	
}
