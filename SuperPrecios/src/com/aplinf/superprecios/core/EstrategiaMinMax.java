package com.aplinf.superprecios.core;

import java.util.ArrayList;
import java.util.List;

public class EstrategiaMinMax extends Estrategia {

	Producto producto;
	List<Precio> precios;
	
	/**
	 * Busca el precio m�nimo y el m�ximo para el producto especificado.
	 * @param producto
	 */
	public EstrategiaMinMax(Producto producto){
		this.producto = producto;
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
		maximo.setDescripcion("Precio m�s caro");
		ArrayList<Precio> resultado = new ArrayList<Precio>();
		resultado.add(minimo);
		resultado.add(maximo);
		return resultado;
	}

}
