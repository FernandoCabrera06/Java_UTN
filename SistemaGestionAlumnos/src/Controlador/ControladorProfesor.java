package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class ControladorProfesor implements ActionListener, MouseListener {

    private Modelo.Profesor profesorModelo;
    private Vista.VistaProfesor profesorVista;

    private DefaultTableModel modeloTabla;

    public ControladorProfesor(Vista.VistaProfesor vista, Modelo.Profesor modelo) {
        this.profesorVista = vista;
        this.profesorModelo = modelo;
        this.profesorVista.setVisible(true);
        llenarTabla(this.profesorVista.getTablaProfesor());
        escucharBotones();

    }

    public void escucharBotones() {
        this.profesorVista.getBtnGuardar().addActionListener(this);
        this.profesorVista.getBtnEliminar().addActionListener(this);
        this.profesorVista.getBtnVolver().addActionListener(this);
        this.profesorVista.getTablaProfesor().addMouseListener(this);
        this.profesorVista.getBtnModificar().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource().equals(this.profesorVista.getBtnGuardar())) {
            if (profesorModelo.validaCarga(this.profesorVista.getTxtNombre().getText()) || profesorModelo.validaCarga(this.profesorVista.getTxtApellido().getText()) || profesorModelo.validaCarga(this.profesorVista.getTxtDNI().getText())) {

                JOptionPane.showMessageDialog(null, "DNI, nombre y apellido son obligatorios!");

            } else if (profesorModelo.validaDNI(this.profesorVista.getTxtDNI().getText())) {
                JOptionPane.showMessageDialog(null, "DNI inválido!");

            } else {
                this.profesorModelo.setDni(Integer.parseInt(this.profesorVista.getTxtDNI().getText()));
                this.profesorModelo.setNombre(this.profesorVista.getTxtNombre().getText());
                this.profesorModelo.setApellido(this.profesorVista.getTxtApellido().getText());
                this.profesorModelo.setFechaNacimiento(Date.valueOf(this.profesorVista.getDateChooser().getText()));
                this.profesorModelo.setDomicilio(this.profesorVista.getTxtDomicilio().getText());
                this.profesorModelo.setTelefono(this.profesorVista.getTxtTelefono().getText());
                if (profesorModelo.dniExiste(profesorModelo)) {
                    if (this.profesorModelo.cargaDatos(profesorModelo)) {
                        JOptionPane.showMessageDialog(null, "Profesor cargado!");
                    }
                    this.limpiarTabla(this.profesorVista.getTablaProfesor());
                    llenarTabla(this.profesorVista.getTablaProfesor());
                    borrarTextFields();

                } else {
                    JOptionPane.showMessageDialog(null, "Profesor repetido!");
                }
            }
        } else if (evento.getSource().equals(this.profesorVista.getBtnVolver())) {
            Vista.VistaMenu menuVista = new Vista.VistaMenu();
            ControladorMenu menuControlador = new ControladorMenu(menuVista);
            this.profesorVista.dispose();

        } else if (evento.getSource().equals(this.profesorVista.getBtnEliminar())) {
            int row = profesorVista.getTablaProfesor().getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Debe Seleccione Una Fila!");
            } else if (this.profesorModelo.baja(this.profesorVista.getTablaProfesor())) {
                limpiarTabla(this.profesorVista.getTablaProfesor());
                llenarTabla(this.profesorVista.getTablaProfesor());
                JOptionPane.showMessageDialog(null, "Profesor eliminado!");
                borrarTextFields();
                this.profesorVista.getTxtDNI().setEditable(true);
            }

        } else if (evento.getSource().equals(this.profesorVista.getBtnModificar())) {
            int row = profesorVista.getTablaProfesor().getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Debe Seleccione Una Fila!");
            } else {
                this.profesorModelo.setDni(Integer.valueOf(this.profesorVista.getTxtDNI().getText()));
                this.profesorModelo.setNombre(this.profesorVista.getTxtNombre().getText());
                this.profesorModelo.setApellido(this.profesorVista.getTxtApellido().getText());
                this.profesorModelo.setFechaNacimiento(Date.valueOf(this.profesorVista.getDateChooser().getText()));
                this.profesorModelo.setDomicilio(this.profesorVista.getTxtDomicilio().getText());
                this.profesorModelo.setTelefono(this.profesorVista.getTxtTelefono().getText());
                if (this.profesorModelo.modifica(profesorModelo)) {
                    JOptionPane.showMessageDialog(null, "Profesor modificado!");
                }
                this.limpiarTabla(this.profesorVista.getTablaProfesor());
                llenarTabla(this.profesorVista.getTablaProfesor());
                borrarTextFields();
                this.profesorVista.getTxtDNI().setEditable(true);
            }
        }
    }

   

    public void llenarTabla(JTable tabla) {
        modeloTabla = new DefaultTableModel(null, nombrarColumnas());
        ArrayList<Modelo.Profesor> profesores;
        profesores = this.profesorModelo.traeProfesores();
        this.limpiarTabla(this.profesorVista.getTablaProfesor());
        Object datosColumna[] = new Object[6];
        if (profesores.size() > 0) {
            for (int i = 0; i < profesores.size(); i++) {
                datosColumna[0] = profesores.get(i).getDni();
                datosColumna[1] = profesores.get(i).getNombre();
                datosColumna[2] = profesores.get(i).getApellido();
                datosColumna[3] = profesores.get(i).getFechaNacimiento();
                datosColumna[4] = profesores.get(i).getDomicilio();
                datosColumna[5] = profesores.get(i).getTelefono();
                modeloTabla.addRow(datosColumna);
            }
        }

        tabla.setModel(modeloTabla);
        profesores.clear();

    }
 public String[] nombrarColumnas() {
        String[] Columna = {"DNI", "Nombre", "Apellido", "Fecha Nac.", "Domicilio", "Telefono",};
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
        this.profesorVista.getTxtNombre().setText("");
        this.profesorVista.getTxtApellido().setText("");
        this.profesorVista.getTxtDNI().setText("");
        this.profesorVista.getTxtDomicilio().setText("");
        this.profesorVista.getTxtTelefono().setText("");
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == 1) {
            int row = this.profesorVista.getTablaProfesor().rowAtPoint(e.getPoint());
            if (row > -1) {
                this.profesorVista.getTxtDNI().setText(String.valueOf(this.profesorVista.getTablaProfesor().getValueAt(row, 0)));
                this.profesorVista.getTxtDNI().setEditable(false);
                this.profesorVista.getTxtNombre().setText(String.valueOf(this.profesorVista.getTablaProfesor().getValueAt(row, 1)));
                this.profesorVista.getTxtApellido().setText(String.valueOf(this.profesorVista.getTablaProfesor().getValueAt(row, 2)));
                this.profesorVista.getDateChooser().setText(String.valueOf(this.profesorVista.getTablaProfesor().getValueAt(row, 3)));
                this.profesorVista.getTxtDomicilio().setText(String.valueOf(this.profesorVista.getTablaProfesor().getValueAt(row, 4)));
                this.profesorVista.getTxtTelefono().setText(String.valueOf(this.profesorVista.getTablaProfesor().getValueAt(row, 5)));
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
