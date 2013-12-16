package com.aplinf.superprecios.core;

import java.util.ArrayList;
import java.util.List;

public class EstrategiaMinMax extends Estrategia {

	public static final int ESTRAT_MINMAX = 2;

	List<Precio> precios;
	
	/**
	 * Busca el precio mínimo y el máximo para el producto especificado.
	 * @param producto
	 */
	public EstrategiaMinMax(){
	}

	@Override
	public List<Precio> compararPrecio() {
		precios = sistema.filtrarPrecios(producto);
		Precio minimo = new Precio(99999999, producto);
		Precio maximo = new Precio(0, producto);
		for(Precio p : precios){
			if(p.menor(minimo))
				minimo = p;
			if(p.mayor(maximo))
				maximo = p;
		}
		minimo.setDescripcion("Mejor precio encontrado");
		minimo.setProducto(producto);
		maximo.setDescripcion("Precio más caro");
		maximo.setProducto(producto);
		ArrayList<Precio> resultado = new ArrayList<Precio>();
		resultado.add(minimo);
		resultado.add(maximo);
		return resultado;
	}

}
