/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package ejercicio_B;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Fernando
 */
public class Empleado {

    private String nombre;
    private long cuit;
    private String domicilio;
    private String email;

    //relaciones
    private ArrayList<Tardanza> tardanzas;
    private ArrayList<Asistencia> asistencias;
    private RegimenHorario regimenHorario;

    //constructores
    public Empleado() {
    }

    public Empleado(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getCuit() {
        return cuit;
    }

    public void setCuit(long cuit) {
        this.cuit = cuit;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Tardanza> getTardanzas() {
        return tardanzas;
    }

    public void setTardanzas(ArrayList<Tardanza> tardanzas) {
        this.tardanzas = tardanzas;
    }

    public ArrayList<Asistencia> getAsistencias() {
        return asistencias;
    }

    public void addAsistencias(Asistencia asistencia) {

        if (asistencias == null) {
            asistencias = new ArrayList();
        }
        asistencias.add(asistencia);
    }

    public RegimenHorario getRegimenHorario() {
        return regimenHorario;
    }

    public void setRegimenHorario(RegimenHorario regimenHorario) {
        this.regimenHorario = regimenHorario;
    }

    public List<Asistencia> getAsistenciaXMesXAnio(int mes, int anio) {

        List<Asistencia> asistenciasBuscadas = new ArrayList();

        for (Asistencia asist : this.getAsistencias()) {
            if (asist.getFecha().getYear() == anio) {
                if (asist.getFecha().getMonth() == mes) {
                    asistenciasBuscadas.add(asist);
                }
            }
        }
        return asistenciasBuscadas;
    }

    public List<Tardanza> getDiasConTardanza(int mes, int anio) {

        List<Tardanza> diasConTardanzas = new ArrayList();

        for (Asistencia asist : this.getAsistencias()) {
            if (asist.getFecha().getYear() == anio) {
                if (asist.getFecha().getMonth() == mes) {

                    if ((((asist.getHora() * 60) - (this.getRegimenHorario().getHoraIngreso() * 60)) + (asist.getMinuto() - this.getRegimenHorario().getMinutoIngreso())) > 15) {

                        diasConTardanzas.add(new Tardanza(asist.getId(), asist.getTipo(), asist.getFecha(), asist.getHora(), asist.getMinuto(), asist.getEmpleado()));

                    }
                }
            }
        }
        return diasConTardanzas;
    }

    @Override
    public String toString() {
        return "Empleado{" + "nombre=" + nombre + '}';
    }

}
