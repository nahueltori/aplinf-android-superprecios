package com.aplinf.superprecios.core;

import java.util.ArrayList;
import java.util.List;

public class EstrategiaPromedio extends Estrategia {

	public static final int ESTRAT_PROMEDIO = 1;

	List<Precio> precios;
	
	/**
	 * La estrategia promedio realiza un promedio de todos los precios del producto establecido,
	 * y devuelve ese valor.
	 * @param producto
	 */
	public EstrategiaPromedio(){
	}

	@Override
	public List<Precio> compararPrecio() {
		precios = sistema.filtrarPrecios(producto);
		float promedio = 0;
		float total = 0;
		for(Precio p : precios){
			total += p.getImporte();
		}
		promedio = total / precios.size();
		Precio precioProm = new Precio(promedio, producto);
		precioProm.setDescripcion("Precio promedio");
		ArrayList<Precio> resultado = new ArrayList<Precio>();
		resultado.add(precioProm);
		return resultado;
	}

}
