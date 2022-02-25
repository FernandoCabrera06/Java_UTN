package Controlador;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author fernando
 */
public class ControladorMateria implements ActionListener, MouseListener, ItemListener {

    private Modelo.Materia materiaModelo;
    private Vista.VistaMateria materiaVista;

    private DefaultTableModel modeloTabla;
    Modelo.Profesor profesor = new Modelo.Profesor();

    public Vista.VistaMateria getMateriaVista() {
        return materiaVista;
    }

    public ControladorMateria(Vista.VistaMateria vista, Modelo.Materia modelo) {
        this.materiaVista = vista;
        this.materiaModelo = modelo;
        this.materiaVista.setVisible(true);
        llenaComboBox();
        llenarTabla(this.materiaVista.getjTable1());
        escucharBotones();
        this.materiaModelo.setProfesor(profesor);
        this.materiaVista.getTxtProfesorNombre().setEditable(false);

    }

    public void escucharBotones() {

        this.materiaVista.getBtnGuardar().addActionListener(this);
        this.materiaVista.getjTable1().addMouseListener(this);
        this.materiaVista.getBtnEliminar().addActionListener(this);
        this.materiaVista.getBtnModificar().addActionListener(this);
        this.materiaVista.getBtnVolver().addActionListener(this);
        this.materiaVista.getComboDNIProfesor().addItemListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource().equals(this.materiaVista.getBtnGuardar())) {
            if (materiaModelo.validaCarga(this.materiaVista.getTxtNombre().getText())) {
                JOptionPane.showMessageDialog(null, "Nombre no puede estar vacío!");

            } else {
                this.materiaModelo.setCodMateria(Integer.valueOf(this.materiaVista.getTxtCodigo().getText()));
                this.materiaModelo.setNombreMateria(this.materiaVista.getTxtNombre().getText());
                this.materiaModelo.getProfesor().setDni(Integer.parseInt(this.materiaVista.getComboDNIProfesor().getSelectedItem().toString()));
                if (this.materiaVista.getComboDNIProfesor().getSelectedItem().toString().equals("")) {

                }
                if (this.materiaModelo.cargaDatos(materiaModelo)) {
                    JOptionPane.showMessageDialog(null, "Materia cargada!");
                }
                this.limpiarTabla(this.materiaVista.getjTable1());
                llenarTabla(this.materiaVista.getjTable1());
                borrarTextFields();

            }
        } else if (evento.getSource().equals(this.materiaVista.getBtnVolver())) {
            Vista.VistaMenu menuVista = new Vista.VistaMenu();
            ControladorMenu menuControlador = new ControladorMenu(menuVista);
            this.materiaVista.dispose();

        } else if (evento.getSource().equals(this.materiaVista.getBtnEliminar())) {
            int row = materiaVista.getjTable1().getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Debe Seleccione Una Fila!");
            } else {
                if (this.materiaModelo.baja(this.materiaVista.getjTable1())) {
                    limpiarTabla(this.materiaVista.getjTable1());
                    llenarTabla(this.materiaVista.getjTable1());
                    JOptionPane.showMessageDialog(null, "Materia eliminada!");
                    borrarTextFields();

                }
            }
        } else if (evento.getSource().equals(this.materiaVista.getBtnModificar())) {
            int row = materiaVista.getjTable1().getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Debe Seleccione Una Fila!");
            } else {
                this.materiaModelo.setCodMateria(Integer.parseInt(this.materiaVista.getTxtCodigo().getText()));
                this.materiaModelo.setNombreMateria(this.materiaVista.getTxtNombre().getText());
                if (this.materiaVista.getComboDNIProfesor().getSelectedItem().toString().equals("")) {
                    this.materiaModelo.getProfesor().setDni(0);
                } else {
                    this.materiaModelo.getProfesor().setDni(Integer.parseInt(this.materiaVista.getComboDNIProfesor().getSelectedItem().toString()));
                }
                if (this.materiaModelo.modifica(materiaModelo)) {
                    JOptionPane.showMessageDialog(null, "Materia modificada!");
                }
                this.limpiarTabla(this.materiaVista.getjTable1());
                llenarTabla(this.materiaVista.getjTable1());
                borrarTextFields();

            }

        }
    }

    public void llenarTabla(JTable tabla) {
        modeloTabla = new DefaultTableModel(null, nombrarColumnas());
        ArrayList<Modelo.Materia> materias;
        this.materiaVista.getTxtCodigo().setEditable(true);
        materias = this.materiaModelo.traeMaterias();
        this.limpiarTabla(this.materiaVista.getjTable1());
        Object datosColumna[] = new Object[3];
        if (materias.size() > 0) {
            for (int i = 0; i < materias.size(); i++) {
                datosColumna[0] = materias.get(i).getCodMateria();
                datosColumna[1] = materias.get(i).getNombreMateria();
                datosColumna[2] = materias.get(i).getDniProfesor();
                modeloTabla.addRow(datosColumna);
            }
        }

        tabla.setModel(modeloTabla);
        materias.clear();

    }
  public String[] nombrarColumnas() {
        String[] Columna = {"Cod. Materia", "Nombre", "DNI Profesor"};
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

        this.materiaVista.getTxtNombre().setText("");
        this.materiaVista.getTxtCodigo().setText("");
        this.materiaVista.getComboDNIProfesor().setSelectedIndex(0);
        this.materiaVista.getTxtProfesorNombre().setText("");

    }

    public void llenaComboBox() {
        ArrayList<String> dni = this.materiaModelo.traeDNI();
        Iterator<String> dniIterator = dni.iterator();
        while (dniIterator.hasNext()) {
            this.materiaVista.getComboDNIProfesor().addItem(dniIterator.next());
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == 1) {
            int row = this.materiaVista.getjTable1().rowAtPoint(e.getPoint());
            if (row > -1) {
                this.materiaVista.getTxtCodigo().setEditable(false);
                this.materiaVista.getTxtCodigo().setText(String.valueOf(this.materiaVista.getjTable1().getValueAt(row, 0)));
                this.materiaVista.getTxtNombre().setText(String.valueOf(this.materiaVista.getjTable1().getValueAt(row, 1)));
                this.materiaVista.getComboDNIProfesor().setSelectedItem(String.valueOf(this.materiaVista.getjTable1().getValueAt(row, 2)));

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

        if (ie.getItemSelectable().equals(this.materiaVista.getComboDNIProfesor()) && !this.materiaVista.getComboDNIProfesor().getSelectedItem().equals("")) {
            Integer eleccion = Integer.parseInt(this.materiaVista.getComboDNIProfesor().getSelectedItem().toString());
            this.profesor = this.materiaModelo.traeProfesor(eleccion);
            this.materiaVista.getTxtProfesorNombre().setText(this.profesor.getNombre() + " " + this.profesor.getApellido());
        }
    }

}
