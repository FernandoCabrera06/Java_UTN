/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio_C;

import java.util.List;

/**
 *
 * @author Fernando
 */
public class Persona {

    private String nombre;
    private String tipoDocumento;
    private long nroDocumento;
    private int telefono;
    private String email;
    private String celular;

//relaciones
    private Sector sector;
    private List<Actividad> actividades;

//constructores    
    public Persona() {
    }

    public Persona(String nombre, String tipoDocumento, long nroDocumento, int telefono, String email, String celular, List<Actividad> actividades) {
        this.nombre = nombre;
        this.tipoDocumento = tipoDocumento;
        this.nroDocumento = nroDocumento;
        this.telefono = telefono;
        this.email = email;
        this.celular = celular;
        this.actividades = actividades;

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public long getNroDocumento() {
        return nroDocumento;
    }

    public void setNroDocumento(long nroDocumento) {
        this.nroDocumento = nroDocumento;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public List<Actividad> getActividades() {
        return actividades;
    }

    public void setActividades(List<Actividad> actividades) {
        this.actividades = actividades;
    }

    public double totalPuntosAsignados() {
        double totalPuntos = 0;
        for (Actividad actividad : actividades) {
            totalPuntos += actividad.getTipoActividad().getPuntosAsignados();
        }
        return totalPuntos;
    }

    public double totalPuntosAsignados(int codigo) {
        double totalPuntos = 0;
        for (Actividad actividad : actividades) {
            if (actividad.getTipoActividad().getCodigo() == codigo) {
                totalPuntos += actividad.getTipoActividad().getPuntosAsignados();
            }
        }
        return totalPuntos;
    }

    public double totalPuntosAsignados(int codigo, int anio) {
        double totalPuntos = 0;
        for (Actividad actividad : actividades) {
            if (anio >= actividad.getFechaInicio().getYear() && anio <= actividad.getFechaFin().getYear()) {
                if (actividad.getTipoActividad().getCodigo() == codigo) {
                    totalPuntos += actividad.getTipoActividad().getPuntosAsignados();
                }
            }
        }
        return totalPuntos;
    }
}
