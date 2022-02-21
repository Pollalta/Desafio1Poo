import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Operaciones{
    Scanner reader = new Scanner(System.in); //Creamos un objeto para leer los datos
    int limite=0;
    int[] numeros = new int[limite];
    int sumandos=0;
    int[] posiciones = new int[sumandos];
    int totalsuma;
    private int[] miArreglo;
    public int[] getMiArreglo(){
        return miArreglo;
    }
    public void setMiArreglo(int[] miArreglo){
        this.miArreglo = miArreglo;
    }
/*public void mostrarMenu(){

}*/
public void ingresoDatos(int limite){
//Lectura de datos e ingreso de números en el array
    try{
        int[] numeros = new int[limite];
        for (int i = 0; i < limite; i++) {
            System.out.print("Posición[" + i + "]= ");
            numeros[i] = reader.nextInt();
        }
        setMiArreglo(numeros);
        System.out.println("\nLos números fueron ingresados al vector.\n");
    }catch (NumberFormatException e)
    {
        System.out.println("No has ingresado un número entero");
    }

}
public void mostrar(){
    int[] numeros =getMiArreglo();
    System.out.println("Imprimiendo los números del arreglo:");

   /* for (int i=0; i< numeros.length; i++){
        int num = numeros[i];
        System.out.print(num);
    }*/
    System.out.println("A= "+Arrays.toString(numeros));
}

public void sumar(int sumandos){
    ArrayList<Integer> arr = new ArrayList<Integer>();
    arr.add(1);
    arr.add(2);
    arr.add(3);
   // try{
        int[] numeros =getMiArreglo();
        for (int i = 0; i < sumandos; i++) {
            if (sumandos > arr.size()) {
                System.out.println("Los números a sumar no pueden ser mayores a los que ha ingresado en el arreglo");
                break;
            } else {
                System.out.println("¿Qué posiciones desea sumar?: ");
                System.out.println("Ingrese la posición :" + i);
               // posiciones = reader.nextInt();

                int input =reader.nextInt();
                int pos = arr.get(input);
                System.out.println("Posiciones = "+pos);
                /*for (int i = 0; i< numeros.length; i++){

                }*/
            }
        }

            /*System.out.println("Total de la suma es: "+totalsuma);
            if(posiciones> numeros.length || posiciones< 0){
                System.out.println("La posición ingresada no está en el arreglo");
            }*/

    //}catch (NumberFormatException e)
    //{
     //   System.out.println("No has ingresado un número entero");
   // }


}

    // Try catch para validar que sólo se ingrese números enteros
    private static boolean isNumber(String n) {
        try {
            Integer.parseInt(n);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

}
