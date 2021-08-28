/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package ejercicio_D;

import java.util.ArrayList;

/**
 *
 * @author Fernando
 */
public class BarrioAPP {

    public static void main(String[] args) {

        Habitacion hab1 = new Habitacion(420, "habitación small", 40);
        Habitacion hab2 = new Habitacion(620, "habitación medium", 60);
        Habitacion hab3 = new Habitacion(820, "habitación large", 80);
        ArrayList<Habitacion> habitaciones1 = new ArrayList();
        habitaciones1.add(hab1);
        habitaciones1.add(hab1);
        habitaciones1.add(hab2);
        habitaciones1.add(hab2);
        habitaciones1.add(hab3);

        ArrayList<Habitacion> habitaciones2 = new ArrayList();
        habitaciones2.add(hab2);
        habitaciones2.add(hab2);
        habitaciones2.add(hab2);
        habitaciones2.add(hab2);
        habitaciones2.add(hab3);
        habitaciones2.add(hab3);
        habitaciones2.add(hab3);

        Vivienda vivienda1 = new Vivienda(598, "Belgrano", 050, 50, habitaciones1);
        Vivienda vivienda2 = new Vivienda(599, "San Martín", 240, 800, habitaciones2);

        ArrayList<Vivienda> viviendas = new ArrayList();
        viviendas.add(vivienda1);
        viviendas.add(vivienda2);

        Barrio barrio1 = new Barrio(265, "La estancia", "SFO contrucciones", viviendas);

        System.out.println("------------SUPERFICIE TOTAL TERRENO-----------------");
        System.out.println("Superficie total de terreno: " + barrio1.getSuperficieTotalTerreno());
        System.out.println("\n--------VIVIENDAS-------------------");
        System.out.println("Cantidad de metros cuadrados cubiertos de vivienda 1: " + vivienda1.getMetrosCuadradosCubiertos());
        System.out.println("----------------------------");
        System.out.println("Cantidad de metros cuadrados cubiertos de vivienda 2: " + vivienda2.getMetrosCuadradosCubiertos());
        System.out.println("\n------------SUPERFICIE TOTAL CUBIERTA-----------------");
        System.out.println("Superficie total cubierta del barrio: " + barrio1.getSuperficieTotalCubierta());
    }

}
