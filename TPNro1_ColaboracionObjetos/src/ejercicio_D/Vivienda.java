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
public class Vivienda {

    private long id;
    private String calle;
    private int numeroCalle;
    private double superficieTerreno;

//relaciones
    private List<Habitacion> habitaciones;
    private Barrio barrio;

//constructores
    public Vivienda() {
    }

    public Vivienda(long id, String calle, int numeroCalle, double superficieTerreno, List<Habitacion> habitaciones) {
        this.id = id;
        this.calle = calle;
        this.numeroCalle = numeroCalle;
        this.superficieTerreno = superficieTerreno;
        this.habitaciones = habitaciones;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getNumeroCalle() {
        return numeroCalle;
    }

    public void setNumeroCalle(int numeroCalle) {
        this.numeroCalle = numeroCalle;
    }

    public double getSuperficieTerreno() {
        return superficieTerreno;
    }

    public void setSuperficieTerreno(double superficieTerreno) {
        this.superficieTerreno = superficieTerreno;
    }

    public List<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(List<Habitacion> habitaciones) {
        this.habitaciones = habitaciones;
    }

    public Barrio getBarrio() {
        return barrio;
    }

    public void setBarrio(Barrio barrio) {
        this.barrio = barrio;
    }

    public void validarMetrosCubiertos(double metrosCubiertos) throws Exception {

        if (metrosCubiertos > this.superficieTerreno) {
            throw new Exception();
        }
    }

    public double getMetrosCuadradosCubiertos() {

        double superficieCubiertaTotal = 0;

        for (Habitacion habitacion : getHabitaciones()) {
            superficieCubiertaTotal += habitacion.getMetrosCuadrados();
        }
        try {
            validarMetrosCubiertos(superficieCubiertaTotal);
        } catch (Exception e) {
            System.out.println("La superficie cubierta no puede ser mayor a la superficie del terreno");
        }

        return superficieCubiertaTotal;

    }
}
