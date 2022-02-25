package Controlador;

import Vista.VistaAlumno;
import Vista.VistaMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author fernando
 */
public class ControladorMenu implements ActionListener {

    private VistaMenu menu;

    public ControladorMenu(Vista.VistaMenu menu) {
        this.menu = menu;
        menu.setVisible(true);
        escucharBotones();

    }

    public void escucharBotones() {
        this.menu.getBtnAlumno().addActionListener(this);
        this.menu.getBtnProfesor().addActionListener(this);
        this.menu.getBtnMateria().addActionListener(this);
        this.menu.getBtnCursado().addActionListener(this);
        this.menu.getBtnInscripcion().addActionListener(this);
        this.menu.getBtnCarrera().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource().equals(this.menu.getBtnAlumno())) {

            Modelo.Alumno alumnoModelo = new Modelo.Alumno();
            VistaAlumno alumnoVista = new Vista.VistaAlumno();
            ControladorAlumno alumnoControlador = new Controlador.ControladorAlumno(alumnoModelo, alumnoVista);
            this.menu.dispose();

        } else if (evento.getSource().equals(this.menu.getBtnMateria())) {
            Modelo.Materia materiaModelo = new Modelo.Materia();
            Vista.VistaMateria materiaVista = new Vista.VistaMateria();
            ControladorMateria materiaControlador = new Controlador.ControladorMateria(materiaVista, materiaModelo);
            this.menu.dispose();

        } else if (evento.getSource().equals(this.menu.getBtnProfesor())) {
            Modelo.Profesor profesorModelo = new Modelo.Profesor();
            Vista.VistaProfesor profesorVista = new Vista.VistaProfesor();
            ControladorProfesor profesorControlador = new Controlador.ControladorProfesor(profesorVista, profesorModelo);
            this.menu.dispose();

        } else if (evento.getSource().equals(this.menu.getBtnCarrera())) {
            Modelo.Carrera carreraModelo = new Modelo.Carrera();
            Vista.VistaCarrera carreraVista = new Vista.VistaCarrera();
            ControladorCarrera carreraControlador = new Controlador.ControladorCarrera(carreraVista, carreraModelo);
            this.menu.dispose();

        } else if (evento.getSource().equals(this.menu.getBtnCursado())) {
            Modelo.Cursado cursadoModelo = new Modelo.Cursado();
            Vista.VistaCursado cursadoVista = new Vista.VistaCursado();
            ControladorCursado cursadoControlador = new Controlador.ControladorCursado(cursadoModelo, cursadoVista);
            this.menu.dispose();

        } else if (evento.getSource().equals(this.menu.getBtnInscripcion())) {
            Modelo.Inscripcion inscripcionModelo = new Modelo.Inscripcion();
            Vista.VistaInscripcion inscripcionVista = new Vista.VistaInscripcion();
            ControladorInscripcion inscripcionControlador = new Controlador.ControladorInscripcion(inscripcionVista, inscripcionModelo);
            this.menu.dispose();

        }
    }
}
