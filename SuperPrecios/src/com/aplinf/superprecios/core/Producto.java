package com.aplinf.superprecios.core;

public class Producto {

	private double id;
	private String descripcion;
	private Precio precio;
	
	public Producto(double codigo){
		this.id = codigo;
		this.descripcion = "";
		this.precio = new Precio(0, this);
	}
	
	public Producto(double codigo, String descripcion){
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
	
	public double getCodigo(){
		return id;
	}
	
	public String getDescripcion(){
		return descripcion;
	}
	
	public float getImporte(){
		return precio.getImporte();
	}
	
	public boolean equals(Producto otro){
		return this.id == otro.id;
	}
	
}
