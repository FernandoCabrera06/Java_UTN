/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejercicio_A;

import java.util.ArrayList;

/**
 *
 * @author Fernando
 */
public class EstadoControl {

    private long id;
    private boolean aprobado;


    //relaciones
    ArrayList<Control> controles;
    
    //contructores
    public EstadoControl() {
    }

    public EstadoControl(long id, boolean aprobado) {
        this.id = id;
        this.aprobado = aprobado;
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isAprobado() {
        return aprobado;
    }

    public void setAprobado(boolean aprobado) {
        this.aprobado = aprobado;
    }
    
    
}
