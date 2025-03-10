package com.auth;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CredencialTest {

	private static Credencial sut;
	
	@BeforeAll
	private static void setup() {
		sut=new Credencial("Ivan", "Fernandez", "Contrasena1");
	}
	
	@DisplayName("Test comprobarPassword True")
	@ParameterizedTest
	@MethodSource("providerComprobarPasswordTestTrue")
	void comprobarPasswordTestTrue(String passwordCredencial,String passwordNew) {
		this.sut.setPassword(passwordCredencial);
		assertTrue(sut.comprobarPassword(passwordNew));
	}
	
	private static Stream<Arguments> providerComprobarPasswordTestTrue(){
		return Stream.of(
					Arguments.of("Contrasena1","Contrasena1"),
					Arguments.of("C","C")
				);
	}
	
	@DisplayName("Test comprobarPassword False")
	@ParameterizedTest
	@MethodSource("providerComprobarPasswordTestFalse")
	void comprobarPasswordTestFalse(String passwordCredencial,String passwordNew) {
		this.sut.setPassword(passwordCredencial);
		assertFalse(sut.comprobarPassword(passwordNew));
	}
	
	private static Stream<Arguments> providerComprobarPasswordTestFalse(){
		return Stream.of(
					Arguments.of("Contrasena1",""),
					Arguments.of("C","Contrasena1"),
					Arguments.of("","A"),
					Arguments.of(null,""),
					Arguments.of("",""),
					Arguments.of(null,null),
					Arguments.of(null,"C"),
					Arguments.of("C",null)
				);
	}
	
	@DisplayName("Test esPasswordSegura True")
	@ParameterizedTest
	@MethodSource("providerEsPasswordSeguraTestTrue")
	void esPasswordSeguraTestTrue(String passwordCredencial) {
		this.sut.setPassword(passwordCredencial);
		assertTrue(sut.esPasswordSegura());
	}
	
	private static Stream<Arguments> providerEsPasswordSeguraTestTrue(){
		return Stream.of(
					Arguments.of("Contrasena1"),
					Arguments.of("Palanca1224"),
					Arguments.of("Contrase1")
				);
	}
	
	@DisplayName("Test esPasswordSegura False")
	@ParameterizedTest
	@MethodSource("providerEsPasswordSeguraTestFalse")
	void esPasswordSeguraTestFalse(String passwordCredencial) {
		this.sut.setPassword(passwordCredencial);
		assertFalse(sut.esPasswordSegura());
	}
	
	private static Stream<Arguments> providerEsPasswordSeguraTestFalse(){
		return Stream.of(
					Arguments.of("Con1"),
					Arguments.of("Contra1"),
					Arguments.of("Contrasena"),
					Arguments.of("12345678")
				);
	}
	
	@DisplayName("Test toString")
	@ParameterizedTest
	@MethodSource("providerToStringTest")
	void toStringTest(String nombre,String apellido,String password,String result) {
		this.sut=new Credencial(nombre, apellido, password);
		assertEquals(result, sut.toString());
	}
	
	private static Stream<Arguments> providerToStringTest(){
		return Stream.of(
					Arguments.of("Ivan","Fernandez","1234", "username vanFer101 y contraseña ****"),
					Arguments.of("Daniel","Gutierrez","Alcachofa1234", "username ielGut102 y contraseña *************")
				);
	}
}
