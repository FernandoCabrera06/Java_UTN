/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package ejercicio_B;

import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Fernando
 */
public class AsistenciaAPP {
    
    public static void main(String[] args) {
        
        Empleado empleado1 = new Empleado();
        
        RegimenHorario regimen2021 = new RegimenHorario(2021, 8, 30, 13, 30, empleado1);
        empleado1.setRegimenHorario(regimen2021);
        
        Asistencia asistencia1 = new Asistencia(23, "E", (new Date(2021, 10, 8)), 8, 20);
        Asistencia asistencia2 = new Asistencia(24, "E", (new Date(2021, 10, 9)), 8, 50);
        Asistencia asistencia3 = new Asistencia(38, "E", (new Date(2021, 11, 14)), 8, 25);
        Asistencia asistencia4 = new Asistencia(0023, "E", (new Date(2021, 11, 15)), 8, 46);
        
        empleado1.addAsistencias(asistencia1);
        empleado1.addAsistencias(asistencia2);
        empleado1.addAsistencias(asistencia3);
        empleado1.addAsistencias(asistencia4);
        
        System.out.println("------ASISTENCIAS--------");
        
        Scanner entrada = new Scanner(System.in).useDelimiter("\n");
        
        System.out.print("Ingrese mes: ");
        int mes = entrada.nextInt();
        System.out.print("Ingrese año: ");
        int anio = entrada.nextInt();
        
        for (Asistencia asist : empleado1.getAsistenciaXMesXAnio(mes, anio)) {
            System.out.println(asist);
        }
        
        System.out.println("------TARDANZAS--------");
        
        System.out.print("Ingrese mes: ");
        mes = entrada.nextInt();
        System.out.print("Ingrese año: ");
        anio = entrada.nextInt();
        
        for (Tardanza diaTardanza : empleado1.getDiasConTardanza(mes, anio)) {
            System.out.println(diaTardanza);
        }
    }
    
}
