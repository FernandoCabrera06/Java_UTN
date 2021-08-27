/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio_A;

import java.util.ArrayList;

/**
 *
 * @author Fernando
 */
public class Control {

    private int id;
    private String denominacion;
    private boolean esObligatorio;

//relaciones
    private ArrayList<Expediente> expedientes;
    private EstadoControl estadoControl;

    public Control() {
    }

    public Control(int id, String denominacion, boolean esObligatorio, EstadoControl estadoControl) {
        this.id = id;
        this.denominacion = denominacion;
        this.esObligatorio = esObligatorio;
        this.estadoControl = estadoControl;
    }

    public ArrayList<Expediente> getExpedientes() {
        return expedientes;
    }

    public void setExpedientes(ArrayList<Expediente> expedientes) {
        this.expedientes = expedientes;
    }

    public EstadoControl getEstadoControl() {
        return estadoControl;
    }

    public void setEstadoControl(EstadoControl estadoControl) {
        this.estadoControl = estadoControl;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public boolean isEsObligatorio() {
        return esObligatorio;
    }

    public void setEsObligatorio(boolean esObligatorio) {
        this.esObligatorio = esObligatorio;
    }

}
