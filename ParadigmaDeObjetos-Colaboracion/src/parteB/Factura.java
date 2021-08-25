package parteB;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Fernando
 */
public class Factura {

    private String letra;
    private int numero;
    private double recargo;
    private String tipoPago;
    private double totalItems;
    private double totalFinal;
    private Date fecha;
    private ArrayList<DetalleFactura> detalles;

    public Factura() {

    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public double getRecargo() {
        return recargo;
    }

    public void setRecargo(double recargo) {
        this.recargo = recargo;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public double getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(double totalItems) {
        this.totalItems = totalItems;
    }

    public double getTotalFinal() {
        return totalFinal;
    }

    public void setTotalFinal(double totalFinal) {
        this.totalFinal = totalFinal;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public ArrayList<DetalleFactura> getDetalles() {
        return detalles;
    }

    public void addDetalles(DetalleFactura detalle) {
        detalles.add(detalle);
    }
}
