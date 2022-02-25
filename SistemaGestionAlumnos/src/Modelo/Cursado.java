package Modelo;

import Datos.DAOCursado;
import java.util.ArrayList;
import javax.swing.JTable;

/**
 *
 * @author fernando
 */
public class Cursado {

    private int alumnoDni;
    private int codigoMateria;
    private double nota;
    private Alumno alumno;
    private Materia materia;
    private Datos.DAOCursado cursadoDAO = new Datos.DAOCursado();

  
    public Cursado() {
    }

    public Cursado(double nota, Alumno alumno, Materia materia) {
        this.nota = nota;
        this.alumno = alumno;
        this.materia = materia;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public DAOCursado getCursadoDAO() {
        return cursadoDAO;
    }

    
    public void setCursadoDAO(DAOCursado cursadoDAO) {
        this.cursadoDAO = cursadoDAO;
    }

    public int getAlumnoDni() {
        return alumnoDni;
    }

    public void setAlumnoDni(int alumnoDni) {
        this.alumnoDni = alumnoDni;
    }

    public int getCodigoMateria() {
        return codigoMateria;
    }

    public void setCodigoMateria(int codigoMateria) {
        this.codigoMateria = codigoMateria;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public boolean validaCarga(String a) {
        if (a.length() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<Cursado> pideNotas() {
        return cursadoDAO.pideNotasDAO();
    }

    public ArrayList<String> pideDNIAlumno() {
        return cursadoDAO.pideDNIAlumnoDAO();
    }

    public Modelo.Alumno pideAlumno(Integer dni) {
        this.alumno = cursadoDAO.pideAlumnoDAO(dni);
        return this.alumno;
    }

    public ArrayList<String> pideCodigoMateria() {
        return cursadoDAO.pideCodigoMateriaDAO();
    }

    public Modelo.Materia pideMateria(int codigo) {
        this.materia = cursadoDAO.pideMateriaDAO(codigo);
        return this.materia;
    }

    public boolean cargaDatos(Modelo.Cursado cursado) {
        return cursadoDAO.cargaDatosDAO(cursado);
    }

    public boolean elimina(JTable tabla) {
        return cursadoDAO.eliminaDAO(tabla);
    }

    public boolean eliminaConDni(int dni) {
        return cursadoDAO.eliminaConDni(dni);
    }

    public boolean modifica(Modelo.Cursado cursado) {
        return cursadoDAO.modificaDAO(cursado);
    }

    public boolean notaRepetida(Modelo.Cursado nota) {
        return !cursadoDAO.notaRepetida(nota);
    }

    public boolean validaNota(int nota) {
        if (nota >= 1 && nota <= 10) {
            return true;
        } else {
            return false;
        }
    }

}
