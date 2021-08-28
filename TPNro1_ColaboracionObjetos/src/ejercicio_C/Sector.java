/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package ejercicio_C;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Fernando
 */
public class Sector {

    private int numero;
    private String denominacion;
    private String tipo;

    //relaciones
    private Sector sectorPadre;
    private List<Sector> sectoresHijos;
    private List<Persona> personas;

    public Sector() {
    }

    public Sector(int numero, String denominacion) {
        this.numero = numero;
        this.denominacion = denominacion;
    }

    public Sector(int numero, String denominacion, String tipo, Sector sectorPadre, List<Sector> sectoresHijos) {
        this.numero = numero;
        this.denominacion = denominacion;
        this.tipo = tipo;
        this.sectorPadre = sectorPadre;
        this.sectoresHijos = sectoresHijos;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Sector getSectorPadre() {
        return sectorPadre;
    }

    public void setSectorPadre(Sector sectorPadre) {
        this.sectorPadre = sectorPadre;
    }

    public List<Sector> getSectoresHijos() {
        return sectoresHijos;
    }

    public void setSectoresHijos(List<Sector> sectoresHijos) {
        this.sectoresHijos = sectoresHijos;
    }

    public List<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(List<Persona> personas) {
        this.personas = personas;
    }

    public List<Sector> obtenerTotalSubsectores() {
        List<Sector> listaRecursiva = new ArrayList();
        obtenerSubsecRecursivo(listaRecursiva, this);
        return listaRecursiva;
    }

    public void obtenerSubsecRecursivo(List<Sector> sectores, Sector sector) {

        sectores.add(sector);

        if (sector.getSectoresHijos() != null) {

            for (Sector sectorHijo : sector.getSectoresHijos()) {
                obtenerSubsecRecursivo(sectores, sectorHijo);
            }
        }
    }

}
