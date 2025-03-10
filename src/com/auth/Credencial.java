package com.auth;

public class Credencial {
	
	private String username;
	private String password;
	private static int secuencia=100;
	
	public Credencial(String nombre,String apellido,String password) {
		super();
		this.username=nombre.substring(nombre.length()-3)+apellido.substring(0,3)+secuencia;
		this.password = password;
		secuencia++;
	}
	
	public boolean comprobarPassword(String password) {
		boolean resultado=false;
		if (this.password != null &&
				password != null &&
				!this.password.isEmpty() &&
				!password.isEmpty()) {
			resultado=this.password.equals(password);
		}
		return resultado;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public boolean esPasswordSegura() {
		boolean tieneMayuscula=false;
		boolean tieneNumero=false;
		for (char charPassword:password.toCharArray()) {
			if (Character.isUpperCase(charPassword)) {
				tieneMayuscula=true;
			}else if(Character.isDigit(charPassword)){
				tieneNumero=true;
			}
		}
		return tieneMayuscula && tieneNumero && password.length()>8;
	}
	
	public void setPassword(String newpass) {
		this.password=newpass;
	}
	
	@Override
	public String toString() {
		String resultado = "username " + this.username + " y contraseña ";
		
		for (char charPassword:password.toCharArray()) {
			resultado+="*";
		}
		
		return resultado;
	}
	
	public static void setSecuencia(int secuencia) {
		secuencia=secuencia;
	}
}
