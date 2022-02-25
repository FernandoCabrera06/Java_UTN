package Controlador;

import Vista.VistaMenu;

public class Main {

    /**
     *
     * @author fernando
     */
    public static void main(String[] args) {
        VistaMenu menu = new VistaMenu();
        ControladorMenu menuControlador = new ControladorMenu(menu);

    }

}
