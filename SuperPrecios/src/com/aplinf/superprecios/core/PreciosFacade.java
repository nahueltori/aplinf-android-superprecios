package com.aplinf.superprecios.core;

import java.util.List;

public interface PreciosFacade {

	public void agregarPrecio(Precio precio);
	
	public List<Precio> compararPrecio(Estrategia estrategia);
	
}
