/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio_D;

import java.util.List;

/**
 *
 * @author Fernando
 */
public class Barrio {

    private long id;
    private String nombre;
    private String empresaConstructora;

//relación
    private List<Vivienda> viviendas;

//constructores
    public Barrio() {
    }

    public Barrio(long id, String nombre, String empresaConstructora, List<Vivienda> viviendas) {
        this.id = id;
        this.nombre = nombre;
        this.empresaConstructora = empresaConstructora;
        this.viviendas = viviendas;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmpresaConstructora() {
        return empresaConstructora;
    }

    public void setEmpresaConstructora(String empresaConstructora) {
        this.empresaConstructora = empresaConstructora;
    }

    public List<Vivienda> getViviendas() {
        return viviendas;
    }

    public void setViviendas(List<Vivienda> viviendas) {
        this.viviendas = viviendas;
    }

    public double getSuperficieTotalTerreno() {
        double superficieTotal = 0;
        for (Vivienda vivienda : viviendas) {
            superficieTotal += vivienda.getSuperficieTerreno();
        }
        return superficieTotal;
    }

    public double getSuperficieTotalCubierta() {

        double superficieTotalCubierta = 0;
        for (Vivienda vivienda : viviendas) {
            superficieTotalCubierta += vivienda.getMetrosCuadradosCubiertos();
        }
        return superficieTotalCubierta;
    }

}
