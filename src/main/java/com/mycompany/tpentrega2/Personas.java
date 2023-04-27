/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tpentrega2;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author grupo6
 */
public class Personas {
private final String nombre;
private int aciertosTotales; 
private int puntosExtras;
Map<Integer, Integer> mapAciertosRonda = new HashMap<>();

    public int getPuntosExtras() {
        return puntosExtras;
    }

    public void setPuntosExtras(int puntosExtras) {
        this.puntosExtras = puntosExtras;
    }


    public Personas(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    
    public void setAciertosTotales(int puntaje) {
        this.aciertosTotales = puntaje;
    }

    public int getAciertosTotales() {
        return aciertosTotales;
    }

    public Map<Integer, Integer> getMap() {
        return mapAciertosRonda;
    }

  
  

 public int calculaPuntaje(int aciertos, int puntosPorAcierto) {
     int puntaje = aciertos *puntosPorAcierto;
     return puntaje;
 }   

 
 }