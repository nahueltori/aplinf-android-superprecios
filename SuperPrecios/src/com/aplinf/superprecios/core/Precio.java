package com.aplinf.superprecios.core;

import java.util.Date;

public class Precio {

	private long id;
	private Producto producto;
	private double importe;
	private Date fecha;
	private String descripcion;
	private Super supermercado;
	
	public Precio(double importe, Producto prod){
		this.id = 0;
		this.producto = prod;
		this.importe = importe;
		this.fecha = new Date();
		this.descripcion = "";
		this.supermercado = new Super();
	}
	
	public void setProducto(Producto prod){
		this.producto = prod;
	}
	
	public void setId(long id){
		this.id = id;
	}
	
	public void setDescripcion(String descr){
		this.descripcion = descr;
	}
	
	public long getId(){
		return id;
	}
	
	public Producto getProducto(){
		return producto;
	}

	public double getImporte(){
		return importe;
	}
	
	public Date getFecha(){
		return fecha;
	}
	
	public String getDescripcion(){
		return descripcion;
	}
	
	public boolean menor(Precio p){
		return this.importe < p.importe;
	}
	
	public boolean mayor(Precio p){
		return this.importe > p.importe;
	}
	
	public boolean igual(Precio p){
		return this.importe == p.importe;
	}
	
}
