package com.ibm.academia.apirest;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
class ApplicationTests {

	Calculadora calculadora = new Calculadora();
	@Test
	void sumaValores() {
		// Given --> efine el contexto o las precondiciones
		Integer valorA = 2;
		Integer valorB = 3;
		// When -> Ejecuta la acciòn, lo que quiere probar
		Integer expected = calculadora.sumar(valorA, valorB);
		// Then -> Validar que lo que se prueba funciona correctamente
		Integer resultadoEsperado = 5;
		assertThat(expected).isEqualTo(resultadoEsperado);
	}

	class Calculadora{
		Integer sumar(Integer a, Integer b){
			return a+b;
		}
	}

}
