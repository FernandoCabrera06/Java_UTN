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
public class Expediente {

    private int id;
    private String letra;
    private int numero;
    private String descripcion;
    private String tipo;
    private String ambito;

    //relaciones
    ArrayList<Control> controles;

    //relación nreflexiva
    Expediente ex;
    ArrayList<Expediente> expedientes;

    public Expediente() {
    }

    public Expediente(int id, String letra) {
        this.id = id;
        this.letra = letra;
    }
    
    

    public ArrayList<Control> getControles() {
        return controles;
    }

    public void setControles(ArrayList<Control> controles) {
        this.controles = controles;
    }

    public Expediente getEx() {
        return ex;
    }

    public void setEx(Expediente ex) {
        this.ex = ex;
    }

    public ArrayList<Expediente> getExpedientes() {
        return expedientes;
    }

    public void setExpedientes(ArrayList<Expediente> expedientes) {
        this.expedientes = expedientes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getAmbito() {
        return ambito;
    }

    public void setAmbito(String ambito) {
        this.ambito = ambito;
    }

    public String getCaratulaExpediente() {

        return (this.numero + "-" + this.letra + "-" + this.descripcion);
    }

    public String getControlesObligatorios() {
        String listaControles = "";

        for (int i = 0; i < controles.size(); i++) {

            if (controles.get(i).isEsObligatorio()) {
                listaControles += controles.get(i).getDenominacion();
                if(i!=controles.size()-1){
                listaControles+=", ";
                } else{
                 listaControles+=".";
                }
            }
        }
        return listaControles;
    }

    public boolean getEstadoControles() {
        boolean aprobados;
        for (Control control : controles) {
            if (!(control.isEsObligatorio())) {
                return false;
            }
        }
        return true;
    }

    public List<Expediente> listaExpedientes(List<Expediente> listaEx, Expediente exp1) {
        return listaRecursion(expedientes, exp1);

    }

    public List<Expediente> listaRecursion(List<Expediente> listaExpedientes, Expediente exp2) {
        listaExpedientes.add(exp2);
        
        if (listaExpedientes.isEmpty()) {
            return listaExpedientes;
        } else {
            return listaRecursion(listaExpedientes, exp2);
        }

    }
}
