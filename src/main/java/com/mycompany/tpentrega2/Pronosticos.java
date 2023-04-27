/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tpentrega2;

/**
 *
 * @author grupo6
 */
class Pronosticos {
private String participante;
private String codigo;
private Resultados.ResultadosEnum resultado;
private int aciertos;


    public Pronosticos(String participante, String codigo, Resultados.ResultadosEnum resultado) {
        this.participante = participante;
        this.codigo = codigo;
        this.resultado = resultado;
    }

    public String getParticipante() {
        return participante;
    }

    public void setParticipante(String participante) {
        this.participante = participante;
    }

    
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Resultados.ResultadosEnum getResultado() {
        return resultado;
    }

    public void setResultado(Resultados.ResultadosEnum resultado) {
        this.resultado = resultado;
    }

    public int getAciertos() {
        return aciertos;
    }


//sumará un acierto en caso de que el resultado del pronóstico coincida con el resultado real    
public void asignaAciertos (Resultados.ResultadosEnum pronostico, Resultados.ResultadosEnum real) {
if (pronostico == real) {
  this.aciertos = 1;  }
else { this.aciertos = 0;}
}

}        
    

