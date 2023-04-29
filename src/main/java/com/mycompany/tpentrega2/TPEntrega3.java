/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.tpentrega2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.ini4j.Ini;

/**
 *
 * @author grupo6
 */
public class TPEntrega3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {

        // Asignando variable a la ruta de acceso del archivo EQUIPOS.CSV
        String ubicacionArchivoEquipos = "C:\\tpintegradorgrupo6\\equipos.csv";
        // Trayendo el archivo EQUIPOS.CSV a través del método PATH
        Path pathEquipos = Paths.get(ubicacionArchivoEquipos);
        // Creando lista de equipos
        List<Equipos> listaEquipos = new ArrayList<>();
        // Leyendo cada línea del archivo EQUIPOS.CSV e instanciando objetos Equipos
        for (String equipo : Files.readAllLines(pathEquipos)) {
            Equipos equipos = new Equipos(equipo);
            listaEquipos.add(equipos);
        }
        //Asigno variables y rutas de acceso a resultados.csv
        String ruta2 = "C:\\tpintegradorgrupo6\\resultados.csv";
        Path archivo2 = Paths.get(ruta2);

//Verifica cantidad de campos del archivo resultados.csv, en caso de error, da aviso en pantalla y cierra el programa        
        for (String linea2 : Files.readAllLines(archivo2).subList(1, Files.readAllLines(archivo2).size())) {
            String[] split = linea2.split(";");
            if (split.length != 7) {
                System.out.println("ERROR: La cantidad de campos no es la correcta en al menos una de las líneas del archivo resultados.csv");
                return;
            }
        }
// Utilizo Try - Catch para el caso de que los goles de un equipo no sea nro.entero, al momento de leer resultados.csv
        for (String linea2 : Files.readAllLines(archivo2).subList(1, Files.readAllLines(archivo2).size())) {
            String[] split = linea2.split(";");
            try {
                int golesEquipo1 = Integer.parseInt(split[3]);
                int golesEquipo2 = Integer.parseInt(split[4]);
            } catch (java.lang.NumberFormatException e) {
                System.out.println("ERROR: Goles de al menos un equipo no es un nro. entero");
                return;

            }
        }

//Leyendo el archivo resultados.csv desde la segunda linea, para obtener la cantidad de rondas
        int cantidadRondas = 0;
        for (String linea2 : Files.readAllLines(archivo2).subList(1, Files.readAllLines(archivo2).size())) {
            String[] split = linea2.split(";");
            if (cantidadRondas < Integer.parseInt(split[0])) {
                cantidadRondas = Integer.parseInt(split[0]);
            }
        };
//Crea lista ronda
        List<Ronda> rondaLista = new ArrayList<>();
        //instanciando rondas
        for (int i = 1; i <= cantidadRondas; i++) {
            Ronda ronda = new Ronda(i);
            rondaLista.add(ronda);
        }
//Cargando partidos a Rondas
        for (Ronda iterar1 : rondaLista) {
            iterar1.cargarPartidos();
        }
//Leyendo el archivo resultados.csv desde la segunda linea, para obtener la cantidad de fases
        int cantidadFases = 0;
        for (String linea2 : Files.readAllLines(archivo2).subList(1, Files.readAllLines(archivo2).size())) {
            String[] split = linea2.split(";");
            if (cantidadFases < Integer.parseInt(split[6])) {
                cantidadFases = Integer.parseInt(split[6]);
            }
        }
//crea Lista Fases
        List<Fases> faseLista = new ArrayList<>();
        //instanciando fases, 
        for (int i = 1; i <= cantidadFases; i++) {
            Fases fase = new Fases(i);
            faseLista.add(fase);
        }
//Cargo Lista de Rondas a cada Fase. Verifico que la ronda no esté cargada previamente para evitar duplicaciones.    
        for (Fases iteraFase : faseLista) 
        {
            List<Ronda> rondaLista2 = new ArrayList<>();
            for (String linea4 : Files.readAllLines(archivo2).subList(1, Files.readAllLines(archivo2).size())) 
            {
                String[] split = linea4.split(";");
                if (Integer.parseInt(split[6]) == iteraFase.getNroFase()) 
                {
                    for (Ronda iteraRonda : rondaLista) 
                    {
                        if (Integer.parseInt(split[0]) == iteraRonda.getNroRonda()) 
                        {
                            boolean repetido = false;
                            for (Ronda itera2Ronda : rondaLista2) 
                            {
                                if (itera2Ronda.getNroRonda() == Integer.parseInt(split[0])) 
                                {
                                    repetido = true;
                                }
                            }
                            if (repetido == false) 
                            {
                                rondaLista2.add(iteraRonda);
                            }
                        }
                    }
                }
                iteraFase.setRondas(rondaLista2);
            }
        }

// Defino variables que representan la url BD, el Usuario BD, password BD;
//Obtengo los valores anteriores desde el archivo config.ini
        File fileToParse = new File("config.ini");
        Ini ini = new Ini(fileToParse);
        String url = ini.get("BD", "url");
        String usuario = ini.get("BD", "user");
        String pass = ini.get("BD", "pass");

//Conecto a la BD para leer los pronósticos
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, usuario, pass);
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from pronosticos");

//Creando lista personas 
        List<Personas> listaPersonas = new ArrayList<>();
//Con la consulta SQL instanciamos Personas, verificando siempre que esa Persona no esté cargada previamente. 
        while (rs.next()) {
            boolean repetido = false;
            for (Personas iteraPersona : listaPersonas) {
                if (iteraPersona.getNombre().equals(rs.getString(2))) {
                    repetido = true;
                }
            }
            if (repetido == false) {
                Personas persona = new Personas(rs.getString(2));
                listaPersonas.add(persona);
            }
        }

//Creando lista de pronósticos 
        List<Pronosticos> listaPronosticos = new ArrayList<>();
//Con la consulta SQL  instanciamos los pronósticos y determina el resultado del mismo
        ResultSet rs2 = stmt.executeQuery("select * from pronosticos");
        while (rs2.next()) {
            if ("true".equals(rs2.getString(5))) {
                Pronosticos pronostico = new Pronosticos(rs2.getString(2), rs2.getString(3), Resultados.ResultadosEnum.GANA_EQUIPO_1);
                listaPronosticos.add(pronostico);
            } else if ("true".equals(rs2.getString(6))) {
                Pronosticos pronostico = new Pronosticos(rs2.getString(2), rs2.getString(3), Resultados.ResultadosEnum.EMPATE);
                listaPronosticos.add(pronostico);
            } else if ("true".equals(rs2.getString(7))) {
                Pronosticos pronostico = new Pronosticos(rs2.getString(2), rs2.getString(3), Resultados.ResultadosEnum.GANA_EQUIPO_2);
                listaPronosticos.add(pronostico);
            }
        }
//Cerramos conexión a BD
        con.close();

// Compara los objetos "pronosticos" con los objetos "partidos" y si el codigo es el mismo determina si hubo o no hubo acierto
        for (Pronosticos iteraPronosticos : listaPronosticos) {
            for (Ronda iteraRonda : rondaLista) {
                for (Partidos iteraPartido : iteraRonda.getPartidos()) {
                    if (iteraPronosticos.getCodigo().equals(iteraPartido.getCodigo())) {
                        iteraPronosticos.asignaAciertos(iteraPronosticos.getResultado(), iteraPartido.getResultado());
                    } else {
                    }
                }
            }
        }
//Para cada Persona, calcula los aciertos totales y los aciertos de cada ronda guardandolo con Hashmap
        for (Personas iteraPersona : listaPersonas) {
            int aciertosTotales = 0;
            int aciertos = 0;
            String nombrePersona = iteraPersona.getNombre();
            for (Ronda iteraRonda : rondaLista) {
                int nroRonda = iteraRonda.getNroRonda();
                for (Pronosticos iteraPronostico : listaPronosticos) {
                    for (Partidos iteraPartido : iteraRonda.getPartidos()) {
                        if ((nombrePersona.equals(iteraPronostico.getParticipante())) && (iteraPronostico.getCodigo().equals(iteraPartido.getCodigo()))) {
                            aciertos = aciertos + iteraPronostico.getAciertos();
                            aciertosTotales = aciertosTotales + iteraPronostico.getAciertos();
                        }
                    }
                }
                iteraPersona.mapAciertosRonda.put(nroRonda, aciertos);
                aciertos = 0;
            }
            iteraPersona.setAciertosTotales(aciertosTotales);
        }
        // Declaro variable que representa la cantidad de Puntos Extra a partir del archivo de configuracion config.ini
        String puntosExtraString = ini.get("Puntos", "extra");
        int PuntosExtraConfig = Integer.parseInt(puntosExtraString);
// Determina los Puntos Extra de un Participante que acierta todos los partidos de una Ronda
        for (Personas iteraPersona : listaPersonas) {
            int puntosExtra = 0;
            for (Ronda iteraRonda : rondaLista) {
                int nroRonda = iteraRonda.getNroRonda();
                int cantidadPartidos = iteraRonda.getPartidos().size();
                if (iteraPersona.mapAciertosRonda.get(nroRonda) == cantidadPartidos) {
                    puntosExtra = puntosExtra + PuntosExtraConfig;
                }
                iteraPersona.setPuntosExtras(puntosExtra);
            }
        }
        // Determina los Puntos Extra de un Participante que acierta todos los partidos de una Fase
        for (Personas iteraPersona : listaPersonas) {
            int puntosExtra = 0;
            for (Fases iteraFases : faseLista) {
                int cantidadPartidos = 0;
                int cantidadAciertos = 0;
                for (Ronda iteraRonda : iteraFases.getRondas()) {
                    cantidadPartidos = cantidadPartidos + iteraRonda.getPartidos().size();
                    cantidadAciertos = cantidadAciertos + iteraPersona.mapAciertosRonda.get(iteraRonda.getNroRonda());
                }
                if (cantidadPartidos == cantidadAciertos) {
                    puntosExtra = puntosExtra + PuntosExtraConfig;
                }
            }
            int puntosExtraTotal = iteraPersona.getPuntosExtras() + puntosExtra;
            iteraPersona.setPuntosExtras(puntosExtraTotal);
        }

//A partir del archivo de configuracion config.ini, tomamos el valor de puntos por acierto 
        String puntosAciertoString = ini.get("Puntos", "porAcierto");
        int puntosAcierto = Integer.parseInt(puntosAciertoString);

//Imprime en pantalla los participantes junto con la cantidad de aciertos y puntaje de cada ronda, el puntaje extra y el puntaje total
        for (Personas iteraPersona : listaPersonas) {
            System.out.println("El/La participante " + iteraPersona.getNombre() + " obtuvo el siguiente puntaje:");
            for (Fases iteraFase : faseLista) {
                for (Ronda iteraRonda : iteraFase.getRondas()) {
                    System.out.println("Fase:" + iteraFase.getNroFase() + ", Ronda:" + iteraRonda.getNroRonda() + ", Aciertos:" + iteraPersona.mapAciertosRonda.get(iteraRonda.getNroRonda()) + ", Puntos:" + iteraPersona.calculaPuntaje(iteraPersona.mapAciertosRonda.get(iteraRonda.getNroRonda()), puntosAcierto));
                }
                System.out.println("Puntos Extra:" + iteraPersona.getPuntosExtras());
                System.out.println("El puntaje total es " + (iteraPersona.getPuntosExtras() + iteraPersona.calculaPuntaje(iteraPersona.getAciertosTotales(), puntosAcierto)));

            }
        }
    }
}
