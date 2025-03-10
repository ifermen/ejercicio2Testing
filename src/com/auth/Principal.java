package com.auth;

public class Principal {
	
	public static void main(String[] args) {
		
		Usuario ivan=new Usuario("Ivan", "Fernandez", "AA12345");
		Usuario alvaro=new Usuario("Alvaro", "Aranda","alvaroaranda@gmail.com", "12");
		System.out.println("Crear usuarios:");
		System.out.println(ivan);
		System.out.println(alvaro);
		System.out.println("--------------------------------");
		
		System.out.println("Intentos de cambiar la contraseña:");
		System.out.println(ivan.modificarPassword("12", "bB98654", "bB98654"));
		System.out.println(ivan.modificarPassword("AA12345", "bB98654", "12"));
		System.out.println(ivan.modificarPassword("AA12345", "bB98654losahfoaisfh", "bB98654losahfoaisfh"));
		System.out.println(ivan);
		System.out.println("--------------------------------");
		
		System.out.println("Intentos de logeos hasta bloquear:");
		System.out.println(alvaro.hacerLogin("aroAra101", ""));
		System.out.println(alvaro.hacerLogin("aroAra101", ""));
		System.out.println(alvaro.hacerLogin("aroAra101", ""));
		System.out.println(alvaro);
		System.out.println("--------------------------------");
		
		System.out.println("Login Exitosos:");
		System.out.println(ivan.hacerLogin("vanFer100", "bB98654losahfoaisfh"));
		System.out.println("--------------------------------");
		
		System.out.println("Comprobar contraseña segura:");
		System.out.println(ivan.esPassSegura());
		System.out.println(alvaro.esPassSegura());
		System.out.println("--------------------------------");
	}
}
