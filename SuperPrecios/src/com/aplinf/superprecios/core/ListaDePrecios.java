package com.aplinf.superprecios.core;

import java.util.ArrayList;
import java.util.List;

public class ListaDePrecios {

	List<Precio> lista;
	
	List<Producto> productos;
	
	public ListaDePrecios(){
		lista = new ArrayList<Precio>();
		productos = new ArrayList<Producto>();
		productos.add(new Producto(Double.valueOf("7794851001028"), "Plasticola Escolar 40 grs."));
		productos.add(new Producto(Double.valueOf("779000"), "Producto de prueba Nahuel"));
	}
	
	public List<Precio> getLista(){
		return lista;
	}
	
	/**
	 * Busca el producto en la base para traerse la descripción.
	 * Si no lo encuentra, lo agrega con una descripción estandar.
	 * @param producto
	 */
	private void buscarProducto(Producto producto){
		boolean encontrado = false;
		for(Producto p : productos){
			if(p.equals(producto)){
				producto.setDescripcion(p.getDescripcion());
				encontrado = true;
				break;
			}
		}
		if(!encontrado){
			producto.setDescripcion("Producto " + producto.getCodigo());
			productos.add(producto);
		}
	}
	
	public List<Precio> filtrarPrecios(Producto prod){
		List<Precio> filtrado = new ArrayList<Precio>();
		for(Precio p : lista){
			if(p.getProducto().equals(prod)){
				filtrado.add(p);
			}
		}
		return filtrado;
	}
	
	public void agregarPrecio(Precio precio){
		buscarProducto(precio.getProducto());
		lista.add(precio);
	}

	public List<Precio> compararPrecio(Estrategia estrategia) {
		estrategia.setSistema(this);
		List<Precio> resultados = estrategia.compararPrecio();
		return resultados;
	}

}
