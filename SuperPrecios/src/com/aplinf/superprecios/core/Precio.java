package com.aplinf.superprecios.core;

import java.util.Date;

public class Precio {

	private int id;
	private Producto producto;
	private float importe;
	private Date fecha;
	private String descripcion;
	
	public Precio(float importe, Producto prod){
		this.id = 0;
		this.producto = prod;
		this.importe = importe;
		this.fecha = new Date();
		this.descripcion = "";
	}
	
	public void setProducto(Producto prod){
		this.producto = prod;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public void setDescripcion(String descr){
		this.descripcion = descr;
	}
	
	public int getId(){
		return id;
	}
	
	public Producto getProducto(){
		return producto;
	}

	public float getImporte(){
		return importe;
	}
	
	public Date getFecha(){
		return fecha;
	}
	
	public String getDescripcion(){
		return descripcion;
	}
	
}
