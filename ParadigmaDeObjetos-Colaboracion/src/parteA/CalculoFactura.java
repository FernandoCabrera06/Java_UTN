/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package parteA;

import java.util.Scanner;

/**
 *
 * @author Fernando
 */
public class CalculoFactura {

    public static final String[][] articulos = {{"100", "Azucar", "20", "U"}, {"101", "Leche", "30", "U"},
    {"102", "Aceite", "50", "U"}, {"103", "Sal", "45", "U"}, {"104", " Fideos", "15", "U"},
    {"105", " Arroz", "28", "U"}, {"106", "Galletas", "26", "U"}, {"107", "Carne Molida", "220",
        "Kg"}, {"108", "Shampoo", "42", "U"}, {"109", "Queso Mantecoso", "178", "Kg"},
    {"110", "Jamon Cocido", "320", "Kg"}, {"111", "Naranjas", "80", "Kg"}};

    public static void main(String args[]) {

        Factura factura1 = new Factura();
        Scanner entradaString = new Scanner(System.in);
        Scanner entradaNumerica = new Scanner(System.in);

        System.out.println("--------Datos de factura--------");

        System.out.println("Ingrese la fecha para la factura: ");
        factura1.setFecha(entradaString.nextLine());

        System.out.println("Ingrese Nro de factura: ");
        factura1.setNroFactura(entradaNumerica.nextLong());

        System.out.println("--------Datos de cliente--------");

        System.out.println("Ingrese razón social: ");
        factura1.setRazonSocial(entradaString.nextLine());

        System.out.println("Ingrese Nro de cuit: ");
        factura1.setCuitCliente(entradaNumerica.nextLong());

        System.out.println("--------Tipo de pago--------");

        String tipoDePago;
        while (true) {
            System.out.println("[C] contado\n"
                    + "[TC] tarjeta crédito\n"
                    + "[TD] tarjeta débito");
            tipoDePago = entradaString.nextLine();

            if (validarTipoDePago(tipoDePago)) {
                factura1.setTipoPago(tipoDePago);
                break;
            }

        }

        int cantidadArticulos;

        while (true) {
            System.out.println("Ingrese la cantidad de articulos a facturar: ");
            cantidadArticulos = entradaNumerica.nextInt();
            if (validarCantidadArticulos(cantidadArticulos)) {
                break;
            }
        }

        factura1.setItemsFactura(new String[cantidadArticulos][5]);
        System.out.println("--------Artículos a facturar-----------");
        String codigoArticulo = "";

        for (int i = 0; i < cantidadArticulos; i++) {
            do {
                System.out.println("Ingrese el codigo del artículo: ");

                codigoArticulo = entradaString.nextLine();
            } while (!(buscarArticulo(codigoArticulo)));

            double cantidadDeProducto;
            System.out.println("Ingrese cantidad de producto: ");
            cantidadDeProducto = entradaNumerica.nextDouble();
            String[] fila = devolverProducto(codigoArticulo);

            factura1.cargarProducto(fila, i, cantidadDeProducto);
        }

        System.out.println("-------------EL TICKET A PAGAR ES: -----------\n");
        System.out.println("Cliente: " + factura1.getRazonSocial());
        System.out.println("Fecha: " + factura1.getFecha());
        System.out.println("Numero: " + factura1.getNroFactura());
        System.out.println("Tipo Pago: " + factura1.getTipoPago());
        System.out.println(espaciado("Codigo Item") + espaciado("Denominación   ") + espaciado("Precio") + espaciado("Cantidad") + espaciado("Subtotal"));
        int acumuladorSubtotales = 0;
        for (String[] articulo : factura1.getItemsFactura()) {

            System.out.println("     " + espaciado(articulo[0]) + "" + espaciado(articulo[1]) + "" + espaciado(articulo[2]) + "" + espaciado(articulo[3]) + "" + espaciado(articulo[4]));
            acumuladorSubtotales += Double.valueOf(articulo[4]);
        }
        System.out.println("\t\t\t\t\t\t\t\tTotal de ítems: " + acumuladorSubtotales);
        
        double recargo = calcularRacargo(factura1.getTipoPago(), acumuladorSubtotales);
        System.out.println("\t\t\t\t\t\t\t\tRecargo: " + recargo);
        
        System.out.println("\t\t\t\t\t\t\t\tTotal Final: "+(acumuladorSubtotales+recargo));

    }

    private static double calcularRacargo(String tipoPago, double acuSubtotales) {
        double recargo;
        tipoPago = tipoPago.toUpperCase();
        switch (tipoPago) {

            case "TD":
                recargo = acuSubtotales / 20;
                break;
            case "TC":
                recargo = acuSubtotales / 10;
                break;
            default:
                recargo = acuSubtotales;
                break;

        }

        return recargo;
    }

    private static String espaciado(String palabra) {

        while (palabra.length() < 20) {

            palabra += " ";
        }
        return palabra;
    }

    private static boolean validarTipoDePago(String tipoDePago) {

        return (tipoDePago.equalsIgnoreCase("C") || tipoDePago.equalsIgnoreCase("TC") || tipoDePago.equalsIgnoreCase("TD"));

    }

    private static boolean validarCantidadArticulos(int cantidadArticulos) {

        return (cantidadArticulos > 0);

    }

    private static String[] devolverProducto(String codigoArticulo) {
        String[] filaProducto = new String[4];
        for (String[] articulo : articulos) {
            if (articulo[0].equalsIgnoreCase(codigoArticulo)) {
                filaProducto = articulo;

            }
        }
        return filaProducto;
    }

    private static boolean buscarArticulo(String codArticulo) {

        boolean busqueda = false;

        for (String[] articulo : articulos) {
            if (articulo[0].equalsIgnoreCase(codArticulo)) {
                busqueda = true;
                break;
            }
        }
        if (!(busqueda)) {
            System.out.println("El código ingresado es incorrecto");
        }

        return busqueda;
    }
}
