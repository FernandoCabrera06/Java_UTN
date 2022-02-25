package Controlador;

import Modelo.Cursado;
import Modelo.Inscripcion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author fernando
 */
public class ControladorAlumno implements ActionListener, MouseListener {

    private Vista.VistaAlumno alumnoVista;
    private Modelo.Alumno alumnoModelo;
    private DefaultTableModel modeloTabla;
    private Cursado cursado = new Cursado();
    private Inscripcion inscripcion = new Inscripcion();

    public ControladorAlumno(Modelo.Alumno modelo, Vista.VistaAlumno vista) {
        this.alumnoModelo = modelo;
        this.alumnoVista = vista;
        this.alumnoVista.setVisible(true);
        this.llenarTabla(this.alumnoVista.getTablaAlumno());
        escucharBotones();
    }

    public void escucharBotones() {
        this.alumnoVista.getBtnGuardar().addActionListener(this);
        this.alumnoVista.getBtnEliminar().addActionListener(this);
        this.alumnoVista.getTablaAlumno().addMouseListener(this);
        this.alumnoVista.getBtnModificar().addActionListener(this);
        this.alumnoVista.getBtnVolver().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {

        if (evento.getSource().equals(this.alumnoVista.getBtnGuardar())) {
            if (alumnoModelo.validaCarga(this.alumnoVista.getTxtNombre().getText()) || alumnoModelo.validaCarga(this.alumnoVista.getTxtApellido().getText()) || alumnoModelo.validaCarga(this.alumnoVista.getTxtDNI().getText())) {

                JOptionPane.showMessageDialog(null, "DNI, nombre y apellido son obligatorios");

            } else if (alumnoModelo.validaDNI(this.alumnoVista.getTxtDNI().getText())) {
                JOptionPane.showMessageDialog(null, "DNI inválido!");

            } else {

                this.alumnoModelo.setDni(Integer.parseInt(this.alumnoVista.getTxtDNI().getText()));
                this.alumnoModelo.setNombre(this.alumnoVista.getTxtNombre().getText());
                this.alumnoModelo.setApellido(this.alumnoVista.getTxtApellido().getText());
                this.alumnoModelo.setFechaNacimiento(Date.valueOf(this.alumnoVista.getDateChooser().getText()));
                this.alumnoModelo.setDomicilio(this.alumnoVista.getTxtDomicilio().getText());
                this.alumnoModelo.setTelefono(this.alumnoVista.getTxtTelefono().getText());
                if (alumnoModelo.dniExiste(alumnoModelo)) {
                    if (this.alumnoModelo.cargaDatos(alumnoModelo)) {
                        JOptionPane.showMessageDialog(null, "Alumno cargado!");
                    }
                    this.limpiarTabla(this.alumnoVista.getTablaAlumno());
                    llenarTabla(this.alumnoVista.getTablaAlumno());
                    borrarTextFields();
                } else {
                    JOptionPane.showMessageDialog(null, "Alumno repetido!");
                }
            }
        } else if (evento.getSource().equals(this.alumnoVista.getBtnVolver())) {
            Vista.VistaMenu menuVista = new Vista.VistaMenu();
            ControladorMenu menuControlador = new ControladorMenu(menuVista);
            this.alumnoVista.dispose();

        } else if (evento.getSource().equals(this.alumnoVista.getBtnEliminar())) {

            int row = alumnoVista.getTablaAlumno().getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Debe Seleccione Una Fila!");
            } else {
                if (JOptionPane.showConfirmDialog(null, "Atención! Tambien Eliminará la Inscripción y el Cursado Del Alumno.", "Desea Eliminar?", JOptionPane.YES_NO_OPTION) == 0) {
                    int cursadoDni = Integer.valueOf(alumnoVista.getTablaAlumno().getValueAt(row, 0).toString());
                    cursado.eliminaConDni(cursadoDni);
                    int inscCod = Integer.valueOf(alumnoVista.getTablaAlumno().getValueAt(row, 6).toString());

                    alumnoModelo.eliminarConCod(inscCod);

                    alumnoModelo.elimina(this.alumnoVista.getTablaAlumno());
                    limpiarTabla(this.alumnoVista.getTablaAlumno());
                    llenarTabla(this.alumnoVista.getTablaAlumno());
                    JOptionPane.showMessageDialog(null, "Alumno eliminado correctamente");
                    borrarTextFields();
                    this.alumnoVista.getTxtDNI().setEditable(true);
                }
            }

        } else if (evento.getSource().equals(this.alumnoVista.getBtnModificar())) {

            int row = alumnoVista.getTablaAlumno().getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Debe Seleccione Una Fila!");
            } else {
                this.alumnoModelo.setDni(Integer.parseInt(this.alumnoVista.getTxtDNI().getText()));
                this.alumnoModelo.setNombre(this.alumnoVista.getTxtNombre().getText());
                this.alumnoModelo.setApellido(this.alumnoVista.getTxtApellido().getText());
                this.alumnoModelo.setFechaNacimiento(Date.valueOf(this.alumnoVista.getDateChooser().getText()));
                this.alumnoModelo.setDomicilio(this.alumnoVista.getTxtDomicilio().getText());
                this.alumnoModelo.setTelefono(this.alumnoVista.getTxtTelefono().getText());
                if (this.alumnoModelo.modifica(alumnoModelo)) {
                    JOptionPane.showMessageDialog(null, "Alumno modificado!");
                }
                this.limpiarTabla(this.alumnoVista.getTablaAlumno());
                llenarTabla(this.alumnoVista.getTablaAlumno());
                borrarTextFields();
                this.alumnoVista.getTxtDNI().setEditable(true);
            }

        }
    }



    public void llenarTabla(JTable tabla) {
        modeloTabla = new DefaultTableModel(null, nombrarColumnas());
        ArrayList<Modelo.Alumno> alumnos;
        alumnos = this.alumnoModelo.pedirAlumnos();
        this.limpiarTabla(this.alumnoVista.getTablaAlumno());
        Object datosColumna[] = new Object[7];
        if (alumnos.size() > 0) {
            for (int i = 0; i < alumnos.size(); i++) {
                datosColumna[0] = alumnos.get(i).getDni();
                datosColumna[1] = alumnos.get(i).getNombre();
                datosColumna[2] = alumnos.get(i).getApellido();
                datosColumna[3] = alumnos.get(i).getFechaNacimiento();
                datosColumna[4] = alumnos.get(i).getDomicilio();
                datosColumna[5] = alumnos.get(i).getTelefono();
                datosColumna[6] = alumnos.get(i).getCodigoInscripcion();

                modeloTabla.addRow(datosColumna);
            }
        }

        tabla.setModel(modeloTabla);
        alumnos.clear();

    }
    public String[] nombrarColumnas() {
        String[] Columna = {"DNI", "Nombre", "Apellido", "Fecha Nac.", "Domicilio", "Telefono", "Cod. Inscripción"};
        return Columna;
    }

    public void limpiarTabla(JTable tabla) {
        DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
        int a = tabla.getRowCount() - 1;
        for (int i = a; i >= 0; i--) {
            tb.removeRow(tb.getRowCount() - 1);
        }
    }
    public void borrarTextFields() {
        this.alumnoVista.getTxtNombre().setText("");
        this.alumnoVista.getTxtApellido().setText("");
        this.alumnoVista.getTxtDNI().setText("");
        this.alumnoVista.getTxtDomicilio().setText("");
        this.alumnoVista.getTxtTelefono().setText("");
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == 1) {
            int row = this.alumnoVista.getTablaAlumno().rowAtPoint(e.getPoint());
            if (row > -1) {
                this.alumnoVista.getTxtDNI().setText(String.valueOf(this.alumnoVista.getTablaAlumno().getValueAt(row, 0)));
                this.alumnoVista.getTxtDNI().setEditable(false);
                this.alumnoVista.getTxtNombre().setText(String.valueOf(this.alumnoVista.getTablaAlumno().getValueAt(row, 1)));
                this.alumnoVista.getTxtApellido().setText(String.valueOf(this.alumnoVista.getTablaAlumno().getValueAt(row, 2)));
                this.alumnoVista.getDateChooser().setText(String.valueOf(this.alumnoVista.getTablaAlumno().getValueAt(row, 3)));
                this.alumnoVista.getTxtDomicilio().setText(String.valueOf(this.alumnoVista.getTablaAlumno().getValueAt(row, 4)));
                this.alumnoVista.getTxtTelefono().setText(String.valueOf(this.alumnoVista.getTablaAlumno().getValueAt(row, 5)));
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {

    }

    @Override
    public void mouseReleased(MouseEvent me) {

    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {

    }
}
