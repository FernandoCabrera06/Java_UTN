/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio_A;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Fernando
 */
public class ExpedienteAPP {

    public static void main(String[] args) {

        Expediente expediente1 = new Expediente();
        expediente1.setId(2);
        expediente1.setLetra("gg");
        expediente1.setNumero(323265);
        expediente1.setDescripcion("Acta multa automotor");
        expediente1.setTipo("Automotor");
        expediente1.setAmbito("Vial");

        //expediente padre
        expediente1.setExpPadre(new Expediente(1, "G"));
        
        //expedientes hijos
        ArrayList<Expediente> expHijos = new ArrayList();
        expHijos.add(new Expediente(3, "Expediente hijo 1"));
        expHijos.add(new Expediente(33, "Expediente hijo 2"));

        expediente1.setExpedientesHijos(expHijos);

        //controles
        ArrayList<Control> controles = new ArrayList();
        controles.add(new Control(11, "Revisión de multa", true, (new EstadoControl(22, false))));
        controles.add(new Control(21, "Revisión de pago", false, (new EstadoControl(32, false))));
        controles.add(new Control(31, "Revisión de nulidad", true, (new EstadoControl(42, true))));

        expediente1.setControles(controles);

        //Mostrar desde expediente
        System.out.println("Caratula del Expediente: " + expediente1.getCaratulaExpediente());
        
        System.out.println("Controles Obligatorios: " + expediente1.getControlesObligatorios());
        
        if (expediente1.getEstadoControles()) {
            System.out.println("Estados de controles: TODOS los controles obligatorios estan aprobados!");
        } else {
            System.out.println("Estados de controles: algunos controles NO estan aprobados.");
        }
        
        //lista de expedientes
        List<Expediente> listaExpe= expediente1.listaExpedientes();
        
        for (Expediente expediente : listaExpe) {
            System.out.println("Expediente: "+expediente.getDescripcion());
        }
    }

}
