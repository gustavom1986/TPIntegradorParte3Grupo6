/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tpentrega2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author grupo6
 */
public class Ronda {
private int nroRonda;
private List<Partidos> partidos;

    public Ronda(int nroRonda) {
        this.nroRonda = nroRonda;
    }

    public int getNroRonda() {
        return nroRonda;
    }

    public void setNroRonda(int nroRonda) {
        this.nroRonda = nroRonda;
    }

    public List<Partidos> getPartidos() {
        return partidos;
    }
//método para instanciar los partidos en base al archivo correspondiente, y guardandolos dentro de la ronda asociada
    public void cargarPartidos() throws IOException {
        this.partidos = new ArrayList<>();
        //Asigno variables y rutas de acceso a resultados.csv
        String ruta2 = "C:\\tpintegradorgrupo6\\resultados.csv";
        Path archivo2 = Paths.get(ruta2);
        //Leyendo el archivo resultados.csv desde la segunda linea, instanciando Partidos, determina resultado
        for (String linea2 : Files.readAllLines(archivo2).subList(1, Files.readAllLines(archivo2).size())) {
            String[] split = linea2.split(";");
           if (Integer.parseInt(split[0]) == getNroRonda()) {  
            Partidos partido = new Partidos(split[1], split[2], split[5], Integer.parseInt(split[3]), Integer.parseInt(split[4]));
            this.partidos.add(partido);
        }
}
}
}