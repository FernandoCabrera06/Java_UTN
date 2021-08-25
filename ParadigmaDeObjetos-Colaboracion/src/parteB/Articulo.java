package parteB;

import java.util.ArrayList;

/**
 *
 * @author Fernando
 */
public class Articulo {

    private int codigo;
    private String denominacion;
    private double precio;
    private String unidadMedida;
    private ArrayList<DetalleFactura> detalles;
    
    public Articulo() {
        
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }
    
    public ArrayList<DetalleFactura> getDetalles() {
        return detalles;
    }

    public void addDetalles(DetalleFactura detalle) {
        detalles.add(detalle);
    }
}
