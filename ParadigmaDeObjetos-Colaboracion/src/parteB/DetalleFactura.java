package parteB;

/**
 *
 * @author Fernando
 */
public class DetalleFactura {
    
    private int cantidad;
    private double subtotal;

    public DetalleFactura() {
        
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
}
