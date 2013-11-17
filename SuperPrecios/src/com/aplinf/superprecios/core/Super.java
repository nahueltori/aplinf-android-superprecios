package com.aplinf.superprecios.core;

public class Super {

	private int codigo;
	private String nombre;
	private String zona;
	private String direccion;
	
	public Super(){
		this.codigo = 0;
		this.nombre = "";
		this.zona = "";
		this.direccion = "";
	}
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

}
