package com.auth;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import junit.framework.Assert;

class UsuarioTest {
	
	private static Usuario sut;

	@BeforeAll
	private static void setup() {
		Credencial.setSecuencia(100);
	}
	
	@DisplayName("Test HacerLogin(String username,String password)")
	@ParameterizedTest
	@MethodSource("testHacerLoginProvider")
	void testHacerLogin(boolean result,String nombre,String apellido,String password,String username,String passwordLogin) {
		sut=new Usuario(nombre, apellido, password);
		assertEquals(result, sut.hacerLogin(username, passwordLogin));
	}
	
	private static Stream<Arguments> testHacerLoginProvider(){
		return Stream.of(
					Arguments.of(true,"Paco","Candela","12345678N","acoCan100","12345678N"),
					Arguments.of(false,"Paco","Candela","12345678N","acoCan100","1234567N"),
					Arguments.of(true,"Paco","Candela","12345678N","acoCan100","12345678N"),
					Arguments.of(false,"Paco","Candela","12345678N","acoCan100","12345678N"),
					Arguments.of(false,"Paco","Candela","12345678N",null,"12345678N"),
					Arguments.of(false,"Paco","Candela","12345678N","acoCan100",null),
					Arguments.of(false,"Paco","Candela","12345678N",null,null),
					Arguments.of(false,"Paco","Candela","12345678N","","12345678N"),
					Arguments.of(false,"Paco","Candela","12345678N","acoCan100",""),
					Arguments.of(false,"Paco","Candela","12345678N","","")
					
				);
	}

	@DisplayName("Test ModificarPassword(String oldpass,String newpass,String newpassverif)")
	@ParameterizedTest
	@MethodSource("testModificarPasswordProvider")
	void testModificarPassword(boolean result,Usuario usuario,String oldpass,String newPass,String passVerification,String username,String password) {
		assertEquals(result, usuario.modificarPassword(oldpass, newPass, passVerification));
		assertEquals(true, usuario.hacerLogin(username, password));
	}
	
	private static Stream<Arguments> testModificarPasswordProvider(){
		return Stream.of(
					Arguments.of(true,new Usuario("Capitan", "Sparrow", "12345678L"),"12345678L","234657894pP","234657894pP","tanSpa100","234657894pP"),
					Arguments.of(false,new Usuario("Capitan", "Sparrow", "12345678L"),"12345678","234657894pP","234657894pP","tanSpa102","12345678L"),
					Arguments.of(false,new Usuario("Capitan", "Sparrow", "12345678L"),"12345678L","12345678L","12345678L","tanSpa103","12345678L"),
					Arguments.of(false,new Usuario("Capitan", "Sparrow", "12345678L"),"12345678L","234657894p","234657894pP","tanSpa104","12345678L"),
					Arguments.of(false,new Usuario("Capitan", "Sparrow", "12345678L"),"","234657894pP","234657894pP","tanSpa105","12345678L"),
					Arguments.of(false,new Usuario("Capitan", "Sparrow", "12345678L"),"12345678L","","234657894pP","tanSpa106","12345678L"),
					Arguments.of(false,new Usuario("Capitan", "Sparrow", "12345678L"),"12345678L","234657894pP","","tanSpa107","12345678L"),
					Arguments.of(false,new Usuario("Capitan", "Sparrow", "12345678L"),"","","","tanSpa108","12345678L"),
					Arguments.of(false,new Usuario("Capitan", "Sparrow", "12345678L"),null,"234657894pP","234657894pP","tanSpa109","12345678L"),
					Arguments.of(false,new Usuario("Capitan", "Sparrow", "12345678L"),"12345678",null,"234657894pP","tanSpa110","12345678L"),
					Arguments.of(false,new Usuario("Capitan", "Sparrow", "12345678L"),"12345678","234657894pP",null,"tanSpa111","12345678L"),
					Arguments.of(false,new Usuario("Capitan", "Sparrow", "12345678L"),null,null,null,"tanSpa112","12345678L")
				);
	}

	@DisplayName("Test EsPassSegura()")
	@ParameterizedTest
	@MethodSource("testEsPassSeguraProvider")
	void testEsPassSegura(boolean result,Usuario usuario) {
		assertEquals(result,usuario.esPassSegura());
	}
	
	private static Stream<Arguments> testEsPassSeguraProvider() {
		return Stream.of(
				Arguments.of(true,new Usuario("Pepe","Fiesta","65jdubhgF78")),
				Arguments.of(false,new Usuario("Pepe","Fiesta","65jdubhg78")),
				Arguments.of(false,new Usuario("Pepe","Fiesta","afesjdubhgFsgfd")),
				Arguments.of(false,new Usuario("Pepe","Fiesta","65jgF78")),
				Arguments.of(false,new Usuario("Pepe","Fiesta","65bhgF78"))
				);
	}

}
