/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parteA;

/**
 *
 * @author Fernando
 */
public class Factura {

    private String fecha;
    private long nroFactura;
    private String razonSocial;
    private long cuitCliente;
    private String tipoPago;
    private double montoTotalItems;
    private double recargo;
    private double montoFinal;
    private String[][] itemsFactura;

    public Factura() {
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public long getNroFactura() {
        return nroFactura;
    }

    public void setNroFactura(long nroFactura) {
        this.nroFactura = nroFactura;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public long getCuitCliente() {
        return cuitCliente;
    }

    public void setCuitCliente(long cuitCliente) {
        this.cuitCliente = cuitCliente;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public double getMontoTotalItems() {
        return montoTotalItems;
    }

    public void setMontoTotalItems(double montoTotalItems) {
        this.montoTotalItems = montoTotalItems;
    }

    public double getRecargo() {
        return recargo;
    }

    public void setRecargo(double recargo) {
        this.recargo = recargo;
    }

    public double getMontoFinal() {
        return montoFinal;
    }

    public void setMontoFinal(double montoFinal) {
        this.montoFinal = montoFinal;
    }

    public String[][] getItemsFactura() {
        return itemsFactura;
    }

    public void setItemsFactura(String[][] itemsFactura) {
        this.itemsFactura = itemsFactura;
    }

    public void cargarProducto(String[] fila, int posicion, double cantidad) {

        String cantidadAIngresar = String.valueOf(cantidad);

        if (fila[3].contains("U")) {
            for (int i = 0; i < cantidadAIngresar.length(); i++) {
                if (cantidadAIngresar.charAt(i) == '.') {
                    cantidadAIngresar = cantidadAIngresar.substring(0, i);
                }
            }
            cantidadAIngresar += " U";
        } else {
            cantidadAIngresar += " Kg";
        }

        itemsFactura[posicion][0] = fila[0]; //codigo item
        itemsFactura[posicion][1] = fila[1]; //denominacion
        itemsFactura[posicion][2] = fila[2]; //precio unitario
        itemsFactura[posicion][3] = cantidadAIngresar; //cantidad
        itemsFactura[posicion][4] = String.valueOf(cantidad * (Double.valueOf(fila[2]))); //subtotal

    }

}
