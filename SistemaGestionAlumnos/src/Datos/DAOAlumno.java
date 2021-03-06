package Datos;

import Modelo.Alumno;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;

/**
 *
 * @author fernando
 */
public class DAOAlumno extends DAOConexion {

    private ArrayList<Modelo.Alumno> alumnos;
    private Modelo.Alumno alumno;

    public ArrayList<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(ArrayList<Alumno> alumnos) {
        this.alumnos = alumnos;
    }

    public boolean cargaDatosDAO(Modelo.Alumno alumno) {
        try {
            this.conectar();
            this.consulta = this.conn.prepareStatement("SET FOREIGN_KEY_CHECKS=0");
            this.datos = this.consulta.executeQuery();
            String sql = "INSERT INTO alumno (alu_dni, alu_nombre,alu_apellido,alu_fec_nac,alu_domicilio, alu_telefono) VALUES (?,?,?,?,?,?)";
            PreparedStatement preparedStmt = (PreparedStatement) this.conn.prepareStatement(sql);
            preparedStmt.setInt(1, alumno.getDni());
            preparedStmt.setString(2, alumno.getNombre());
            preparedStmt.setString(3, alumno.getApellido());
            preparedStmt.setDate(4, alumno.getFechaNacimiento());
            preparedStmt.setString(5, alumno.getDomicilio());
            preparedStmt.setString(6, alumno.getTelefono());
            preparedStmt.execute();
            this.desconectar();
            return true;

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DAOAlumno.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }
 public boolean eliminaDAO(JTable tabla) {
        int seleccion;
        alumnos = new ArrayList(pedirAlumnosDAO());
        try {
            this.conectar();
            this.consulta = this.conn.prepareStatement("SET FOREIGN_KEY_CHECKS=0");
            this.consulta = this.conn.prepareStatement("DELETE FROM alumno WHERE alu_dni=?");

            seleccion = tabla.getSelectedRow();
            this.consulta.setInt(1, alumnos.get(seleccion).getDni());
            consulta.execute();

            this.desconectar();
            return true;

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DAOAlumno.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public ArrayList<Modelo.Alumno> pedirAlumnosDAO() {
        alumnos = new ArrayList();
        try {
            this.conectar();
            this.consulta = this.conn.prepareStatement("SELECT * FROM alumno");
            ResultSet hojadeResultados = consulta.executeQuery();

            while (hojadeResultados.next()) {
                alumno = new Modelo.Alumno();
                alumno.setDni(hojadeResultados.getInt(1));
                alumno.setNombre(hojadeResultados.getString(2));
                alumno.setApellido(hojadeResultados.getString(3));
                alumno.setFechaNacimiento(hojadeResultados.getDate(4));
                alumno.setDomicilio(hojadeResultados.getString(5));
                alumno.setTelefono(hojadeResultados.getString(6));
                alumno.setCodigoInscripcion(hojadeResultados.getInt(7));
                alumnos.add(alumno);
            }
            this.desconectar();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DAOAlumno.class.getName()).log(Level.SEVERE, null, ex);
        }

        return alumnos;

    }

   
    public boolean modificaDAO(Modelo.Alumno alumno) {
        try {
            this.conectar();
            PreparedStatement preparedStmt = (PreparedStatement) this.conn.prepareStatement("UPDATE alumno SET alu_nombre=?,alu_apellido=?,alu_fec_nac=?, alu_domicilio=?,alu_telefono=? WHERE alu_dni=?");

            preparedStmt.setString(1, alumno.getNombre());
            preparedStmt.setString(2, alumno.getApellido());
            preparedStmt.setDate(3, alumno.getFechaNacimiento());
            preparedStmt.setString(4, alumno.getDomicilio());
            preparedStmt.setString(5, alumno.getTelefono());

            preparedStmt.setInt(6, alumno.getDni());
            preparedStmt.executeUpdate();
            this.desconectar();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DAOAlumno.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    public boolean dniExisteDAO(Modelo.Alumno alumno) {

        try {
            this.conectar();
            this.consulta = this.conn.prepareStatement("SELECT * FROM alumno WHERE alu_dni=?");
            this.consulta.setInt(1, alumno.getDni());
            ResultSet hojadeResultados = consulta.executeQuery();
            if (hojadeResultados.next()) {
                return true;
            }
            this.desconectar();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DAOAlumno.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean actualizarCarrera(Alumno alumno) {
        try {
            this.conectar();
            this.consulta = this.conn.prepareStatement("SET FOREIGN_KEY_CHECKS=0");
            this.datos = this.consulta.executeQuery();
            String sql = "UPDATE alumno SET alu_insc_cod=? WHERE alu_dni=?";
            PreparedStatement preparedStmt = (PreparedStatement) this.conn.prepareStatement(sql);
            preparedStmt.setInt(1, alumno.getCodigoInscripcion());

            preparedStmt.setInt(2, alumno.getDni());
            preparedStmt.executeUpdate();
            this.desconectar();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DAOInscripcion.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    public boolean eliminarConCodDAO(int inscCod) {
        try {
            this.conectar();
            this.consulta = this.conn.prepareStatement("SET FOREIGN_KEY_CHECKS=0");
            this.datos = this.consulta.executeQuery();
            String sql = ("DELETE FROM inscripcion WHERE insc_cod=?");
            PreparedStatement preparedStmt = (PreparedStatement) this.conn.prepareStatement(sql);
            preparedStmt.setInt(1, inscCod);

            preparedStmt.execute();
            this.desconectar();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DAOInscripcion.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }
}
