package com.aplinf.superprecios.core;

import java.util.List;

public class EstrategiaPromedio extends Estrategia {

	Producto producto;
	List<Precio> precios;
	
	/**
	 * La estrategia promedio realiza un promedio de todos los precios del producto establecido,
	 * y devuelve ese valor.
	 * @param producto
	 */
	public EstrategiaPromedio(Producto producto){
		this.producto = producto;
	}

	@Override
	public Precio compararPrecio() {
		precios = sistema.filtrarPrecios(producto);
		float promedio = 0;
		float total = 0;
		for(Precio p : precios){
			total += p.getImporte();
		}
		promedio = total / precios.size();
		Precio precioProm = new Precio(promedio, producto);
		precioProm.setDescripcion("Precio promedio");
		return precioProm;
	}

}
