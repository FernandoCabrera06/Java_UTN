/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicas;

import java.util.Scanner;

/**
 *
 * @author Fernando
 */
public class Practicas {

    public static void main(String[] args) {
        //se pone useDelimiter para poner mas de una palabra y que no lo corte
        //cuando hay un espacio
        Scanner entrada = new Scanner(System.in).useDelimiter("\n");  
        String nombre, nombre2;
        int edad, edad2;
        
        System.out.print("escriba su edad: ");
        edad = entrada.nextInt();
        
        //se usa next y no nextLine para que no me guarde el enter y 
        //desp se rompa cuando antes puse numeros.
        System.out.print("escriba su nombre: ");
        nombre = entrada.next();

        System.out.print("escriba su edad: ");
        edad2 = entrada.nextInt();

        System.out.print("escriba su nombre: ");
        nombre2 = entrada.next();

        System.out.println("------------------------------------------");

        System.out.println("edad ingresada: " + edad);
        System.out.println("nombre ingresado: " + nombre);
        System.out.println("edad ingresada: " + edad2);

        System.out.println("nombre ingresado: " + nombre2);

    }

}
