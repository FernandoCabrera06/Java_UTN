package parteB;

import java.util.ArrayList;

/**
 *
 * @author Fernando
 */
public class Cliente {

    private int numero;
    private String razonSocial;
    private long cuit;
    private ArrayList<Factura> facturas;
   
    public Cliente() {
        
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public long getCuit() {
        return cuit;
    }

    public void setCuit(long cuit) {
        this.cuit = cuit;
    }
    
     public ArrayList<Factura> getFacturas() {
        return facturas;
    }

    public void addFacturas(Factura factura) {
        facturas.add(factura);
    } 
}
