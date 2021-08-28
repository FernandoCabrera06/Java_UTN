/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package ejercicio_C;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Fernando
 */
public class ActividadAPP {

    public static void main(String[] args) {
        //ACTIVIDADES y TIPO ACTIVIDAD 
        Actividad actividad1 = new Actividad((new Date(2018, 3, 4)), (new Date(2021, 7, 3)), "Ordenar", (new TipoActividad(304, "Controles", 80)));
        Actividad actividad2 = new Actividad((new Date(2019, 8, 4)), (new Date(2021, 9, 2)), "Limpiar", (new TipoActividad(304, "Controles", 80)));
        Actividad actividad3 = new Actividad((new Date(2018, 3, 4)), (new Date(2021, 7, 3)), "Listar", (new TipoActividad(320, "Controles", 80)));

        ArrayList<Actividad> actividades = new ArrayList();
        actividades.add(actividad1);
        actividades.add(actividad2);
        actividades.add(actividad3);

        //PERSONAS
        Persona persona1 = new Persona("Fernando", "DNI", 35546201, 2139899, "fernando_Cabrera@mail", "6622626", actividades);

        //SECTORES
        Sector sector1 = new Sector(26, "Taller");

        ArrayList<Sector> subSectores = new ArrayList();
        subSectores.add(new Sector(14, "Mantenimiento"));
        subSectores.add(new Sector(42, "Limpieza"));
        subSectores.add(new Sector(33, "Almacen"));
        subSectores.add(new Sector(25, "Pintura"));

        Sector sector2 = new Sector(6, "Hangar7", "A", sector1, subSectores);

        System.out.println("Total puntos asignados: " + persona1.totalPuntosAsignados());
        System.out.println("Total puntos asignados por codigo: " + persona1.totalPuntosAsignados(304));
        System.out.println("Total puntos asignados por codigo y año: " + persona1.totalPuntosAsignados(304, 2018));
        System.out.println("----------Total de sectores----------");

        for (Sector subSector : sector2.obtenerTotalSubsectores()) {
            System.out.println(subSector.getDenominacion());
        }
    }

}
