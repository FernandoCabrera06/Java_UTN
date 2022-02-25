package Controlador;

import Modelo.Cursado;
import Vista.VistaCursado;
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
public class ControladorCursado implements ActionListener, MouseListener, ItemListener {

    private Vista.VistaCursado cursadoVista;
    private Modelo.Cursado cursado;

    private DefaultTableModel modeloTabla;
    private Modelo.Alumno alumno = new Modelo.Alumno();
    private Modelo.Materia materia = new Modelo.Materia();

    public ControladorCursado(Cursado cursado, VistaCursado cursadoVista) {
        this.cursado = cursado;
        this.cursadoVista = cursadoVista;
        this.cursadoVista.setVisible(true);
        this.llenarTabla(this.cursadoVista.getTablaNotas());
        llenaComboBoxAlumno();
        this.cursadoVista.getTxtAlumno().setEditable(false);
        llenaComboBoxMateria();
        this.cursadoVista.getTxtMateria().setEditable(false);
        this.cursado.setAlumno(alumno);
        this.cursado.setMateria(materia);
        escucharBotones();
    }

    public void escucharBotones() {
        this.cursadoVista.getBtnGuardar().addActionListener(this);
        this.cursadoVista.getBtnEliminar().addActionListener(this);
        this.cursadoVista.getBtnModificar().addActionListener(this);
        this.cursadoVista.getTablaNotas().addMouseListener(this);
        this.cursadoVista.getBtnVolver().addActionListener(this);
        this.cursadoVista.getComboCodigoMateria().addItemListener(this);
        this.cursadoVista.getComboDniAlumno().addItemListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource().equals(this.cursadoVista.getBtnGuardar())) {

            if (cursado.validaCarga(this.cursadoVista.getComboCodigoMateria().toString()) || cursado.validaCarga(this.cursadoVista.getComboDniAlumno().toString()) || cursado.validaCarga(this.cursadoVista.getTxtNota().getText())) {
                JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios!");
            } else if (cursado.validaNota(Integer.parseInt(this.cursadoVista.getTxtNota().getText()))) {
                this.cursado.getAlumno().setDni(Integer.parseInt(this.cursadoVista.getComboDniAlumno().getSelectedItem().toString()));
                this.cursado.getMateria().setCodMateria(Integer.parseInt(this.cursadoVista.getComboCodigoMateria().getSelectedItem().toString()));
                this.cursado.setNota(Integer.parseInt(this.cursadoVista.getTxtNota().getText()));
                if (cursado.notaRepetida(cursado)) {
                    if (this.cursado.cargaDatos(cursado)) {
                        JOptionPane.showMessageDialog(null, "Nota cargada!");
                    }
                    this.limpiarTabla(this.cursadoVista.getTablaNotas());
                    llenarTabla(this.cursadoVista.getTablaNotas());
                    borrarTextFields();

                } else {
                    JOptionPane.showMessageDialog(null, "Nota repetida!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "La nota debe estar entre 1 y 10");
            }
        } else if (evento.getSource().equals(this.cursadoVista.getBtnEliminar())) {

            int row = cursadoVista.getTablaNotas().getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Debe Seleccione Una Fila!");
            } else {
                if (this.cursado.elimina(this.cursadoVista.getTablaNotas())) {
                    limpiarTabla(this.cursadoVista.getTablaNotas());
                    llenarTabla(this.cursadoVista.getTablaNotas());
                    JOptionPane.showMessageDialog(null, "Nota eliminada!");
                    borrarTextFields();
                }
            }
        } else if (evento.getSource().equals(this.cursadoVista.getBtnModificar())) {

            int row = cursadoVista.getTablaNotas().getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Debe Seleccione Una Fila!");
            } else {
                this.cursado.getAlumno().setDni(Integer.parseInt(this.cursadoVista.getComboDniAlumno().getSelectedItem().toString()));
                this.cursado.getMateria().setCodMateria(Integer.parseInt(this.cursadoVista.getComboCodigoMateria().getSelectedItem().toString()));
                this.cursado.setNota(Integer.parseInt(this.cursadoVista.getTxtNota().getText()));
                if (this.cursado.modifica(cursado)) {
                    JOptionPane.showMessageDialog(null, "Nota modificada!");
                }
                this.limpiarTabla(this.cursadoVista.getTablaNotas());
                llenarTabla(this.cursadoVista.getTablaNotas());
                borrarTextFields();
            }
        } else if (evento.getSource().equals(this.cursadoVista.getBtnVolver())) {
            Vista.VistaMenu menuVista = new Vista.VistaMenu();
            Controlador.ControladorMenu menuControlador = new ControladorMenu(menuVista);
            this.cursadoVista.dispose();
        }
    }

   

    public void llenarTabla(JTable tabla) {
        modeloTabla = new DefaultTableModel(null, nombrarColumnas());
        ArrayList<Modelo.Cursado> cursados;
        cursados = this.cursado.pideNotas();
        this.limpiarTabla(this.cursadoVista.getTablaNotas());
        Object datosColumna[] = new Object[3];
        if (cursados.size() > 0) {
            for (int i = 0; i < cursados.size(); i++) {
                datosColumna[0] = cursados.get(i).getAlumno().getDni();
                datosColumna[1] = cursados.get(i).getMateria().getCodMateria();
                datosColumna[2] = cursados.get(i).getNota();
                modeloTabla.addRow(datosColumna);
            }
        }
        tabla.setModel(modeloTabla);
        cursados.clear();

    }
 
    public void limpiarTabla(JTable tabla) {
        DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
        int a = tabla.getRowCount() - 1;
        for (int i = a; i >= 0; i--) {
            tb.removeRow(tb.getRowCount() - 1);
        }
    }

    public String[] nombrarColumnas() {
        String[] Columna = {"DNI Alumno", "Codigo Materia", "Nota"};
        return Columna;
    }

    public void borrarTextFields() {
        this.cursadoVista.getTxtNota().setText("");
        this.cursadoVista.getComboCodigoMateria().setSelectedIndex(0);
        this.cursadoVista.getComboCodigoMateria().setEnabled(true);
        this.cursadoVista.getComboDniAlumno().setSelectedIndex(0);
        this.cursadoVista.getComboDniAlumno().setEnabled(true);
        this.cursadoVista.getTxtMateria().setText("");
    }
   
 public void llenaComboBoxAlumno() {
        ArrayList<String> dni = this.cursado.pideDNIAlumno();
        Iterator<String> dniIterator = dni.iterator();
        while (dniIterator.hasNext()) {
            this.cursadoVista.getComboDniAlumno().addItem(dniIterator.next());
        }
    }

    public void llenaComboBoxMateria() {
        ArrayList<String> materia = this.cursado.pideCodigoMateria();
        Iterator<String> materiaIterator = materia.iterator();
        while (materiaIterator.hasNext()) {
            this.cursadoVista.getComboCodigoMateria().addItem(materiaIterator.next());
        }
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == 1) {
            int row = this.cursadoVista.getTablaNotas().rowAtPoint(e.getPoint());
            if (row > -1) {
                this.cursadoVista.getComboDniAlumno().setSelectedItem(String.valueOf(this.cursadoVista.getTablaNotas().getValueAt(row, 0)));
                this.cursadoVista.getComboDniAlumno().setEnabled(false);
                this.cursadoVista.getComboCodigoMateria().setSelectedItem(String.valueOf(this.cursadoVista.getTablaNotas().getValueAt(row, 1)));
                this.cursadoVista.getComboCodigoMateria().setEnabled(false);
                this.cursadoVista.getTxtNota().setText(String.valueOf(this.cursadoVista.getTablaNotas().getValueAt(row, 2)));

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
        if (ie.getItemSelectable().equals(this.cursadoVista.getComboCodigoMateria()) && !this.cursadoVista.getComboCodigoMateria().getSelectedItem().equals("")) {
            int eleccion = Integer.parseInt(this.cursadoVista.getComboCodigoMateria().getSelectedItem().toString());
            this.materia = this.cursado.pideMateria(eleccion);
            this.cursadoVista.getTxtMateria().setText(this.materia.getNombreMateria());
        } else if (ie.getItemSelectable().equals(this.cursadoVista.getComboDniAlumno()) && !this.cursadoVista.getComboDniAlumno().getSelectedItem().equals("")) {
            Integer eleccion = Integer.parseInt(this.cursadoVista.getComboDniAlumno().getSelectedItem().toString());
            this.alumno = this.cursado.pideAlumno(eleccion);
            this.cursadoVista.getTxtAlumno().setText(this.alumno.getNombre() + " " + this.alumno.getApellido());
        }
    }
}
