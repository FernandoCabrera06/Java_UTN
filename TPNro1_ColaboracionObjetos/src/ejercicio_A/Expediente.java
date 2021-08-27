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
    private ArrayList<Control> controles;

    //relación reflexiva
    private Expediente expPadre;
    private ArrayList<Expediente> expedientesHijos;

    public Expediente() {
    }

    public Expediente(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public ArrayList<Control> getControles() {
        return controles;
    }

    public void setControles(ArrayList<Control> controles) {
        this.controles = controles;
    }

    public Expediente getExpPadre() {
        return expPadre;
    }

    public void setExpPadre(Expediente ex) {
        this.expPadre = ex;
    }

    public ArrayList<Expediente> getExpedientesHijos() {
        return expedientesHijos;
    }

    public void setExpedientesHijos(ArrayList<Expediente> expedientesHijos) {
        this.expedientesHijos = expedientesHijos;
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
                if (i != controles.size() - 1) {
                    listaControles += ", ";
                } else {
                    listaControles += ".";
                }
            }
        }
        return listaControles;
    }

    public boolean getEstadoControles() {

        for (Control control : controles) {
            if (control.isEsObligatorio()) {
                if (!(control.getEstadoControl().isAprobado())) {
                    return false;
                }
            }
        }
        return true;
    }

    public List<Expediente> listaExpedientes() {
        
        List<Expediente> listaRecursiva = new ArrayList();
        
        listaRecursion(this, listaRecursiva);
        
        return listaRecursiva;
    }

    public void listaRecursion(Expediente exp, List<Expediente> listaExpedientes) {

            listaExpedientes.add(exp);
        if (exp.getExpedientesHijos() != null) {
            for (Expediente expHijo : exp.getExpedientesHijos()) {
                listaRecursion(expHijo, listaExpedientes);
            }

        }
    }
}
