/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tpentrega2;

import java.util.List;

/**
 *
 * @author grupo6
 */
public class Fases {
    private int NroFase;
    private List<Ronda> rondas;

    public Fases(int NroFase) {
        this.NroFase = NroFase;
    }

    
    
    public int getNroFase() {
        return NroFase;
    }

    public void setNroFase(int NroFase) {
        this.NroFase = NroFase;
    }

    public List<Ronda> getRondas() {
        return rondas;
    }

    public void setRondas(List<Ronda> rondas) {
        this.rondas = rondas;
    }
    
    
}
