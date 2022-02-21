import javax.swing.*;
import java.util.*;
public class Ejercicio1 {
    public static void main(String[] args){
       Operaciones operaciones = new Operaciones();
        Scanner reader = new Scanner(System.in); //Creamos un objeto para leer los datos
        int seleccion;
        int sumandos,limite, posiciones=0;
        try{
            do{
                System.out.println("-----Menú----- \nSeleccione una opción:"+"\n1. Ingresar información en el arreglo.\n2. Mostrar información.\n3. Sumar.\n4. Restar.\n5. Dividir.\n6. Multiplicar.\n7. Salir.");
                System.out.println("Por favor digite la opción:");
                seleccion = reader.nextInt() ;
               /* while (!isNumber(seleccion)) {
                    System.out.println("-----Menú----- \nSeleccione una opción:"+"\n1. Ingresar información en el arreglo.\n2. Mostrar información.\n3. Sumar.\n4. Restar.\n5. Dividir.\n6. Multiplicar.\n7. Salir.");
                    System.out.println("Por favor digite la opción:");
                    seleccion = reader.nextLine();
                }*/
               // int opcion = Integer.parseInt(seleccion);
                switch (seleccion){
                    case 1:
                        System.out.println("Bienvenido \n ¿Cúantos números desea ingresar?: ");
                        limite=reader.nextInt();
                        operaciones.ingresoDatos(limite);
                        break;
                    case 2:
                        operaciones.mostrar();
                        break;
                    case 3:
                        System.out.println("¿Cúantos números desea sumar?: ");
                        sumandos=reader.nextInt();
                        operaciones.sumar(sumandos);



                        //Sumar
                    case 7:
                        System.exit(0);
                    default: System.out.println("Opción inválida");
                }
            }while(seleccion != 7);

        }catch(NumberFormatException e)
        {
            System.out.println("AAAA");
        }




    }
    private static boolean isNumber(String n) {
        try {
            Integer.parseInt(n);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }


}
