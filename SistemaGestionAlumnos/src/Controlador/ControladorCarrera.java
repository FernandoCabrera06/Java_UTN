package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author fernando
 */
public class ControladorCarrera implements ActionListener, MouseListener {

    private Modelo.Carrera carrerasModelo;
    private Vista.VistaCarrera carrerasVista;

    private DefaultTableModel modeloTabla;

    public ControladorCarrera(Vista.VistaCarrera vista, Modelo.Carrera modelo) {
        this.carrerasVista = vista;
        this.carrerasModelo = modelo;
        this.carrerasVista.setVisible(true);
        llenarTabla(this.carrerasVista.getTxtableCarrera());
        escucharBotones();

    }

    public void escucharBotones() {
        this.carrerasVista.getBtnGuardar().addActionListener(this);
        this.carrerasVista.getBtnEliminar().addActionListener(this);
        this.carrerasVista.getTxtableCarrera().addMouseListener(this);
        this.carrerasVista.getBtnModificar().addActionListener(this);
        this.carrerasVista.getBtnVolver().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource().equals(this.carrerasVista.getBtnGuardar())) {

            if (carrerasModelo.validaCarga(this.carrerasVista.getTxtNombreCarrera().getText()) || carrerasModelo.validaCarga(this.carrerasVista.getTxtDuracion().getText()) || carrerasModelo.validaCarga(this.carrerasVista.getTxtCodCarrera().getText())) {

                JOptionPane.showMessageDialog(null, "DNI, nombre y apellido son obligatorios");

            } else if (carrerasModelo.validaDNI(this.carrerasVista.getTxtCodCarrera().getText())) {
                JOptionPane.showMessageDialog(null, "DNI inválido!");

            } else {
                this.carrerasModelo.setCodigoCarrera(Integer.parseInt(this.carrerasVista.getTxtCodCarrera().getText()));
                this.carrerasModelo.setNombre(this.carrerasVista.getTxtNombreCarrera().getText());
                this.carrerasModelo.setDuracion(this.carrerasVista.getTxtDuracion().getText());
                if (carrerasModelo.dniExiste(carrerasModelo)) {
                    if (this.carrerasModelo.cargaDatos(carrerasModelo)) {
                        JOptionPane.showMessageDialog(null, "Carrera cargada!");
                    }
                    this.limpiarTabla(this.carrerasVista.getTxtableCarrera());
                    llenarTabla(this.carrerasVista.getTxtableCarrera());
                    borrarTextFields();

                } else {
                    JOptionPane.showMessageDialog(null, "Carrera repetida!");
                }
            }
        } else if (evento.getSource().equals(this.carrerasVista.getBtnVolver())) {
            Vista.VistaMenu menuVista = new Vista.VistaMenu();
            ControladorMenu menuControlador = new ControladorMenu(menuVista);
            this.carrerasVista.dispose();

        } else if (evento.getSource().equals(this.carrerasVista.getBtnEliminar())) {
            int row = carrerasVista.getTxtableCarrera().getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Debe Seleccione Una Fila!");
            } else {
                if (this.carrerasModelo.baja(this.carrerasVista.getTxtableCarrera())) {
                    limpiarTabla(this.carrerasVista.getTxtableCarrera());
                    llenarTabla(this.carrerasVista.getTxtableCarrera());
                    JOptionPane.showMessageDialog(null, "Carrera eliminada!");
                    borrarTextFields();
                    this.carrerasVista.getTxtCodCarrera().setEditable(true);
                }
            }
        } else if (evento.getSource().equals(this.carrerasVista.getBtnModificar())) {
            int row = carrerasVista.getTxtableCarrera().getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Debe Seleccione Una Fila!");
            } else {
                this.carrerasModelo.setCodigoCarrera(Integer.valueOf(this.carrerasVista.getTxtCodCarrera().getText()));
                this.carrerasModelo.setNombre(this.carrerasVista.getTxtNombreCarrera().getText());
                this.carrerasModelo.setDuracion(this.carrerasVista.getTxtDuracion().getText());

                if (this.carrerasModelo.modifica(carrerasModelo)) {
                    JOptionPane.showMessageDialog(null, "Carrera modificada!");
                }
                this.limpiarTabla(this.carrerasVista.getTxtableCarrera());
                llenarTabla(this.carrerasVista.getTxtableCarrera());
                borrarTextFields();
                this.carrerasVista.getTxtCodCarrera().setEditable(true);
            }
        }
    }

  
    public void llenarTabla(JTable tabla) {
        modeloTabla = new DefaultTableModel(null, nombrarColumnas());
        ArrayList<Modelo.Carrera> carreras;
        carreras = this.carrerasModelo.traeCarreras();
        this.limpiarTabla(this.carrerasVista.getTxtableCarrera());
        Object datosColumna[] = new Object[3];
        if (carreras.size() > 0) {
            for (int i = 0; i < carreras.size(); i++) {
                datosColumna[0] = carreras.get(i).getCodigoCarrera();
                datosColumna[1] = carreras.get(i).getNombre();
                datosColumna[2] = carreras.get(i).getDuracion();
                modeloTabla.addRow(datosColumna);
            }
        }

        tabla.setModel(modeloTabla);
        carreras.clear();

    }
  public String[] nombrarColumnas() {
        String[] Columna = {"Cod. Carrera", "Nombre", "Duración"};
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
        this.carrerasVista.getTxtNombreCarrera().setText("");
        this.carrerasVista.getTxtDuracion().setText("");
        this.carrerasVista.getTxtCodCarrera().setText("");
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == 1) {
            int row = this.carrerasVista.getTxtableCarrera().rowAtPoint(e.getPoint());
            if (row > -1) {
                this.carrerasVista.getTxtCodCarrera().setText(String.valueOf(this.carrerasVista.getTxtableCarrera().getValueAt(row, 0)));
                this.carrerasVista.getTxtCodCarrera().setEditable(false);
                this.carrerasVista.getTxtNombreCarrera().setText(String.valueOf(this.carrerasVista.getTxtableCarrera().getValueAt(row, 1)));
                this.carrerasVista.getTxtDuracion().setText(String.valueOf(this.carrerasVista.getTxtableCarrera().getValueAt(row, 2)));

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
