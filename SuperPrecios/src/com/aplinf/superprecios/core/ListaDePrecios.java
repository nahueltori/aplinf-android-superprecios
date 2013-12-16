package com.aplinf.superprecios.core;

import java.util.ArrayList;
import java.util.List;

import com.aplinf.superprecios.sql.PreciosDataSource;

public class ListaDePrecios {

	List<Precio> lista;
	
	List<Producto> productos;
	
	PreciosDataSource datos;
	
	public ListaDePrecios(PreciosDataSource datos){
		this.datos = datos;
		lista = new ArrayList<Precio>();
		productos = new ArrayList<Producto>();
		productos.add(new Producto(Long.valueOf("7795735000069"), "Pan dulce Don Satur con frutas"));
		productos.add(new Producto(Long.valueOf("7791813420521"), "Sprite 500ml."));
		productos.add(new Producto(Long.valueOf("7790639001365"), "Cunnington Lima Limon 2.25L"));
		productos.add(new Producto(Long.valueOf("7794635004795"), "Domino de animales 28 fichas"));
	}
	
	public List<Precio> getLista(){
		return lista;
	}
	
	/**
	 * Busca el producto en la base para traerse la descripción.
	 * Si no lo encuentra, lo agrega con una descripción estandar.
	 * @param producto
	 * @return 
	 */
	public void buscarProducto(Producto producto){
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
		datos.open();
		List<Precio> filtrado = datos.getPrecios(prod);
		datos.close();
		return filtrado;
	}
	
	public void agregarPrecio(Precio precio){
		buscarProducto(precio.getProducto());
		lista.add(precio);
		datos.open();
		datos.crearPrecio(precio);
		datos.close();
	}

	public List<Precio> compararPrecio(Estrategia estrategia) {
		estrategia.setSistema(this);
		List<Precio> resultados = estrategia.compararPrecio();
		return resultados;
	}

}
