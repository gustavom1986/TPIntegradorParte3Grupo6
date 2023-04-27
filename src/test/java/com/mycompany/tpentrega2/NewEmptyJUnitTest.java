/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.tpentrega2;



import static org.junit.Assert.assertEquals;
import org.junit.Test;


/**
 *
 * @author grupo6
 */
public class NewEmptyJUnitTest {
    
    public NewEmptyJUnitTest() {

    }

//Evalua que el resultado sea Gana1Equipo1 cuando los goles1 es mayor a goles2. Espero Prueba Exitosa   
@Test
public void resultadoGana1A(){
Partidos partidoGana1 = new Partidos("QatarGA02", "Ecuador", "Qatar", 1, 0);
  assertEquals(Resultados.ResultadosEnum.GANA_EQUIPO_1, partidoGana1.decidirResultado(partidoGana1.getGolesEquipo1(),partidoGana1.getGolesEquipo2()));
}

//Evalua que el resultado sea Gana1Equipo1 cuando los goles1 es mayor a goles2. Espero Prueba Fallida 
@Test
public void resultadoGana1B(){
Partidos partidoGana1 = new Partidos("QatarGA02", "Ecuador", "Qatar", 1, 4);
  assertEquals(Resultados.ResultadosEnum.GANA_EQUIPO_1, partidoGana1.decidirResultado(partidoGana1.getGolesEquipo1(),partidoGana1.getGolesEquipo2()));
}
//Evalua que el resultado sea Gana1Equipo1 cuando los goles1 es mayor a goles2. Espero Prueba Fallida 
@Test
public void resultadoGana1C(){
Partidos partidoGana1 = new Partidos("QatarGA02", "Ecuador", "Qatar", 1, 1);
  assertEquals(Resultados.ResultadosEnum.GANA_EQUIPO_1, partidoGana1.decidirResultado(partidoGana1.getGolesEquipo1(),partidoGana1.getGolesEquipo2()));
}
//Evalua que el resultado sea EMPATE cuando los goles1 es igual a goles2    

@Test
public void resultadoEmpate(){
Partidos empate = new Partidos("QatarGA02", "Ecuador", "Qatar", 1, 1);
assertEquals(Resultados.ResultadosEnum.EMPATE,empate.decidirResultado(empate.getGolesEquipo1(), empate.getGolesEquipo2()));
}
//Evalua que el resultado sea Gana1Equipo2 cuando los goles2 es mayor a goles1    
@Test
public void resultadoGana2(){
Partidos partidoGana2 = new Partidos("QatarGA02", "Ecuador", "Qatar", 1, 2);
assertEquals(Resultados.ResultadosEnum.GANA_EQUIPO_2,partidoGana2.decidirResultado(partidoGana2.getGolesEquipo1(), partidoGana2.getGolesEquipo2()));
}

@Test
//Evalua que la suma de puntos de dos rondas consecutivas sea la esperada para un participante con 2 aciertos en ronda 1 y 3 aciertos en ronda 2
public void PuntosPersona(){
Personas Federico = new Personas("Pepe");
Federico.mapAciertosRonda.put(1,2);
Federico.mapAciertosRonda.put(2,3);
int puntosAcierto=2;
assertEquals(10,Federico.calculaPuntaje(Federico.mapAciertosRonda.get(1)+ Federico.mapAciertosRonda.get(2),puntosAcierto));
}

}

