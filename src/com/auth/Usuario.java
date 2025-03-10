package com.auth;

public class Usuario {
	
	private String nombre;
	private String apellido;
	private String email;
	private Credencial credencial;
	private static final int MAX_INTENTOS=2;
	private int intentos;
	
	public Usuario(String nombre,String apellido,String password) {
		this.nombre=nombre;
		this.apellido=apellido;
		this.credencial=new Credencial(nombre, apellido, password);
	}
	
	public Usuario(String nombre,String apellido,String email,String password) {
		this.nombre=nombre;
		this.apellido=apellido;
		this.email=email;
		this.credencial=new Credencial(nombre, apellido, password);
	}
	
	public boolean esCuentaBloqueada() {
		return intentos>MAX_INTENTOS;
	}
	
	public boolean modificarPassword(String oldpass,String newpass,String newpassverif) {
		boolean resultado=false;
		if (this.credencial.comprobarPassword(oldpass)
				&& newpass.equals(newpassverif) && !newpass.equals(oldpass)) {
			this.credencial.setPassword(newpass);
			resultado=true;
		}
		return resultado;
	}
	
	public boolean esPassSegura() {
		return this.credencial.esPasswordSegura();
	}
	
	public boolean hacerLogin(String username,String password) {
		boolean resultado=!esCuentaBloqueada();
		if ( username==null || password==null) {
			resultado=false;
		}else if(resultado && username.equals(credencial.getUsername()) && credencial.comprobarPassword(password)) {
			resultado=true;
			intentos=0;
		}else {
			resultado=false;
			intentos+=1;
		}
		
		return resultado;
		
	}
	
	@Override
	public String toString() {
		String resultado="Cuenta Bloqueada";
		if (!esCuentaBloqueada()) {
			resultado="Usuari@: " + this.nombre + " "
		+ this.apellido + " con email " + this.email + ", " + credencial.toString();
		}
		return resultado;
	}
}
