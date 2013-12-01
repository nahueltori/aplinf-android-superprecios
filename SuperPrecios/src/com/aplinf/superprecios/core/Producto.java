package com.aplinf.superprecios.core;

public class Producto {

	private long id;
	private String descripcion;
	private Precio precio;
	private Super superMarket;
	
	public Producto(long codigo){
		this.id = codigo;
		this.descripcion = "";
		this.superMarket = new Super();
		this.precio = new Precio(0, this);
	}
	
	public Producto(long codigo, String descripcion){
		this.id = codigo;
		this.descripcion = descripcion;
		this.precio = new Precio(0, this);
	}
	
	public void setDescripcion(String descr){
		this.descripcion = descr;
	}
	
	public void setPrecio(float importe){
		this.precio = new Precio(importe, this);
	}
	
	public long getCodigo(){
		return id;
	}
	
	public String getDescripcion(){
		return descripcion;
	}
	
	public double getImporte(){
		return precio.getImporte();
	}
	
	public boolean equals(Producto otro){
		return this.id == otro.id;
	}
	
}
