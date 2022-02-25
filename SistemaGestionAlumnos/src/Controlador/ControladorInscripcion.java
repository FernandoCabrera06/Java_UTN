package Controlador;

import Modelo.Alumno;
import Modelo.Carrera;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author fernando
 */
public class ControladorInscripcion implements ActionListener, MouseListener, ItemListener {

    private Modelo.Inscripcion InscripcionModelo;
    private Vista.VistaInscripcion InscripcionVista;

    private DefaultTableModel modeloTabla;
    Carrera carrera = new Carrera();
    Alumno alumno = new Alumno();

    public Vista.VistaInscripcion getInscripcionVista() {
        return InscripcionVista;
    }

    public ControladorInscripcion(Vista.VistaInscripcion vista, Modelo.Inscripcion modelo) {
        this.InscripcionVista = vista;
        this.InscripcionModelo = modelo;
        this.InscripcionVista.setVisible(true);
        llenarComboBox();
        llenarTabla(this.InscripcionVista.getTablaInscripcion());
        escucharBotones();
        this.InscripcionModelo.setCarrera(carrera);
        this.InscripcionModelo.setAlumno(alumno);

    }

    public void escucharBotones() {

        this.InscripcionVista.getBtnGuardar().addActionListener(this);
        this.InscripcionVista.getTablaInscripcion().addMouseListener(this);
        this.InscripcionVista.getBtnEliminar().addActionListener(this);
        this.InscripcionVista.getBtnVolver().addActionListener(this);
        this.InscripcionVista.getBtnModificar().addActionListener(this);
        this.InscripcionVista.getComboCodigoCarrera().addItemListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource().equals(this.InscripcionVista.getBtnGuardar())) {
            Alumno alu = InscripcionModelo.traeAlumno(splitearDniAlumno(InscripcionVista.getComboDniAlumno().getSelectedItem().toString()));
            int inscripCod = alu.getCodigoInscripcion();
            if (inscripCod > 0) {
                JOptionPane.showMessageDialog(null, "Este Alumno Ya Posee Una Inscricpion A Carrera");
            } else if (revisarCampos() == true) {
                if (InscripcionModelo.validaCarga(this.InscripcionVista.getTxtCodInscripcion().getText())) {
                    JOptionPane.showMessageDialog(null, "Codigo de Inscripción no puede estar vacío!");

                } else {

                    this.InscripcionModelo.setCodigoInscripcion(Integer.valueOf(this.InscripcionVista.getTxtCodInscripcion().getText()));
                    this.InscripcionModelo.setNombre(this.splitearNombreAlumno(this.InscripcionVista.getComboDniAlumno().getSelectedItem().toString()));
                    this.InscripcionModelo.setFecha(Date.valueOf(this.InscripcionVista.getDateChooser().getText()));
                    this.InscripcionModelo.setCodigoCarrera(this.splitearCodCarrera(this.InscripcionVista.getComboCodigoCarrera().getSelectedItem().toString()));

                    alumno.setCodigoInscripcion(Integer.valueOf(InscripcionVista.getTxtCodInscripcion().getText()));
                    alumno.setDni(splitearDniAlumno(InscripcionVista.getComboDniAlumno().getSelectedItem().toString()));
                    alumno.actualizarCarreraAlumno(alumno);

                    if (this.InscripcionModelo.cargaDatos(InscripcionModelo)) {
                        JOptionPane.showMessageDialog(null, "Inscripcion cargada!");
                    }
                    this.limpiarTabla(this.InscripcionVista.getTablaInscripcion());
                    llenarTabla(this.InscripcionVista.getTablaInscripcion());
                    borrarTextFields();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Hay Campos Vacios!");
            }
        } else if (evento.getSource().equals(this.InscripcionVista.getBtnVolver())) {
            Vista.VistaMenu menuVista = new Vista.VistaMenu();
            ControladorMenu menuControlador = new ControladorMenu(menuVista);
            this.InscripcionVista.dispose();

        } else if (evento.getSource().equals(this.InscripcionVista.getBtnEliminar())) {
            int row = InscripcionVista.getTablaInscripcion().getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Debe Seleccione Una Fila!");
            } else if (this.InscripcionModelo.baja(this.InscripcionVista.getTablaInscripcion())) {
                limpiarTabla(this.InscripcionVista.getTablaInscripcion());
                llenarTabla(this.InscripcionVista.getTablaInscripcion());
                JOptionPane.showMessageDialog(null, "Inscripcion eliminada!");
                borrarTextFields();

            }

        } else if (evento.getSource().equals(this.InscripcionVista.getBtnModificar())) {
            int row = InscripcionVista.getTablaInscripcion().getSelectedRow();
            if (revisarCampos() == true) {
                if (row == -1) {
                    JOptionPane.showMessageDialog(null, "Debe Seleccione Una Fila!");
                } else {
                    this.InscripcionModelo.setCodigoInscripcion(Integer.parseInt(this.InscripcionVista.getTxtCodInscripcion().getText()));

                    if (this.InscripcionVista.getComboCodigoCarrera().getSelectedItem().toString().equals("")) {
                        this.InscripcionModelo.getCarrera().setCodigoCarrera(0);
                    } else {
                        this.InscripcionModelo.setCodigoInscripcion(Integer.valueOf(this.InscripcionVista.getTxtCodInscripcion().getText()));
                        this.InscripcionModelo.setNombre(this.splitearNombreAlumno(this.InscripcionVista.getComboDniAlumno().getSelectedItem().toString()));
                        this.InscripcionModelo.setFecha(Date.valueOf(this.InscripcionVista.getDateChooser().getText()));
                        this.InscripcionModelo.setCodigoCarrera(this.splitearCodCarrera(this.InscripcionVista.getComboCodigoCarrera().getSelectedItem().toString()));
                    }
                    if (this.InscripcionModelo.modifica(InscripcionModelo)) {
                        JOptionPane.showMessageDialog(null, "Inscripcion modificada!");
                    }
                    this.limpiarTabla(this.InscripcionVista.getTablaInscripcion());
                    llenarTabla(this.InscripcionVista.getTablaInscripcion());
                    borrarTextFields();

                }
            } else {
                JOptionPane.showMessageDialog(null, "Hay Campos Vacios!");
            }
        }
    }

  
   
    public void llenarTabla(JTable tabla) {
        modeloTabla = new DefaultTableModel(null, nombrarColumnas());
        ArrayList<Modelo.Inscripcion> inscripciones;
        this.InscripcionVista.getTxtCodInscripcion().setEditable(true);
        inscripciones = this.InscripcionModelo.traeInscripcion();
        this.limpiarTabla(this.InscripcionVista.getTablaInscripcion());
        Object datosColumna[] = new Object[4];
        if (inscripciones.size() > 0) {
            for (int i = 0; i < inscripciones.size(); i++) {
                datosColumna[0] = inscripciones.get(i).getCodigoInscripcion();
                datosColumna[1] = inscripciones.get(i).getNombre();
                datosColumna[2] = inscripciones.get(i).getFecha();
                datosColumna[3] = inscripciones.get(i).getCodigoCarrera();
                modeloTabla.addRow(datosColumna);
            }
        }

        tabla.setModel(modeloTabla);
        inscripciones.clear();

    }
 public String[] nombrarColumnas() {
        String[] Columna = {"Cod. Inscripcion", "Nombre Alumno", "Fecha Insc.", "Cod. Carrera"};
        return Columna;
    }

    public void limpiarTabla(JTable tabla) {

        DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
        int a = tabla.getRowCount() - 1;
        for (int i = a; i >= 0; i--) {
            tb.removeRow(tb.getRowCount() - 1);
        }
    }
  public void llenarComboBox() {

        InscripcionVista.getComboDniAlumno().removeAllItems();
        InscripcionVista.getComboDniAlumno().addItem("Seleccionar Alumno");
        for (int i = 0; i < alumno.pedirAlumnos().size(); i++) {
            InscripcionVista.getComboDniAlumno().addItem(String.valueOf(alumno.pedirAlumnos().get(i).getDni()) + " - " + alumno.pedirAlumnos().get(i).getNombre()
                    + " " + alumno.pedirAlumnos().get(i).getApellido());
        }
        InscripcionVista.getComboCodigoCarrera().removeAllItems();
        InscripcionVista.getComboCodigoCarrera().addItem("Seleccionar Carrera");

        for (int i = 0; i < carrera.traeCarreras().size(); i++) {
            InscripcionVista.getComboCodigoCarrera().addItem(String.valueOf(carrera.traeCarreras().get(i).getCodigoCarrera() + " - " + carrera.traeCarreras().get(i).getNombre()));
        }
    }

    public int splitearCodCarrera(String carreraCod) {
        String[] parts = carreraCod.split(" - ");
        String part1 = parts[0]; 

        return Integer.valueOf(part1);
    }

    public int splitearDniAlumno(String alumnoDni) {
        String[] parts = alumnoDni.split(" - ");
        String part1 = parts[0];

        return Integer.valueOf(part1);
    }

    public String splitearNombreAlumno(String alumnoNombre) {
        String[] parts = alumnoNombre.split(" - ");
        String part1 = parts[1];

        return part1;
    }

    public void borrarTextFields() {

        this.InscripcionVista.getTxtCodInscripcion().setText("");
        this.InscripcionVista.getComboDniAlumno().setSelectedIndex(0);
        this.InscripcionVista.getComboCodigoCarrera().setSelectedIndex(0);

    }

    public boolean revisarCampos() {
        if (this.InscripcionVista.getTxtCodInscripcion().getText().isEmpty()
                || this.InscripcionVista.getComboDniAlumno().getSelectedItem().equals("Seleccionar Alumno")
                || this.InscripcionVista.getComboDniAlumno().getSelectedItem() == null
                || this.InscripcionVista.getComboCodigoCarrera().getSelectedItem().equals("Seleccionar Carrera")
                || this.InscripcionVista.getComboCodigoCarrera().getSelectedItem() == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == 1) {
            int row = this.InscripcionVista.getTablaInscripcion().rowAtPoint(e.getPoint());
            if (row > -1) {
                this.InscripcionVista.getTxtCodInscripcion().setText(String.valueOf(this.InscripcionVista.getTablaInscripcion().getValueAt(row, 0)));

                for (int i = 0; i < alumno.pedirAlumnos().size(); i++) {
                    if ((alumno.pedirAlumnos().get(i).getNombre()
                            + " " + alumno.pedirAlumnos().get(i).getApellido()).equals((this.InscripcionVista.getTablaInscripcion().getValueAt(row, 1).toString()))) {

                        String a = ((alumno.pedirAlumnos().get(i).getDni()) + " - " + alumno.pedirAlumnos().get(i).getNombre() + " " + alumno.pedirAlumnos().get(i).getApellido());
                        this.InscripcionVista.getComboDniAlumno().setSelectedItem(String.valueOf(a));
                    }
                }

                for (int i = 0; i < carrera.traeCarreras().size(); i++) {
                    if (String.valueOf(carrera.traeCarreras().get(i).getCodigoCarrera()).equals(String.valueOf(this.InscripcionVista.getTablaInscripcion().getValueAt(row, 3)))) {
                        String b = ((carrera.traeCarreras().get(i).getCodigoCarrera() + " - " + carrera.traeCarreras().get(i).getNombre()));
                        this.InscripcionVista.getComboCodigoCarrera().setSelectedItem(String.valueOf(b));
                    }
                }

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

    @Override
    public void itemStateChanged(ItemEvent ie) {

    }

}
