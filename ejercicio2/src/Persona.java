import javax.swing.*;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.Locale;
import java.util.concurrent.CancellationException;


public class Persona {
    private String nombre,tipoEmpleado;
    private String dui;
    private String nit;
    private static final DecimalFormat df = new DecimalFormat("0.00");
    private Float salarioBase,salarioFinal,horas;
    private Connection conexion;
    private ResultSet rs;
    private PreparedStatement pr;
    private Statement s;
    public Persona() {
        //obtenemos el driver de para mysql
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion=DriverManager.getConnection("jdbc:mysql://localhost/empleados","root", "");
            s=conexion.createStatement();
            //verificarAnalistas();
            menu();

            //ingresoInfo();
           // ingresoBdd();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void menu(){
        Integer seleccion,contador;

        boolean salir=false;
        do {
            try {
                seleccion=Integer.parseInt(JOptionPane.showInputDialog("Selecciona una opcion:\n1.Ingresar empleados\n2.Mostrar empleado\n3.Salir"));
                switch (seleccion){
                    case 1:
                        contador=Integer.parseInt(JOptionPane.showInputDialog("digite la cantidad de empleados que desea ingresar"));
                        for (int i = 1; i <=contador ; i++) {
                            ingresoInfo();
                            ingresoBdd();

                        }
                        menu();
                        break;
                    case 2:
                            mostrarDatos();
                            menu();
                        break;
                    case 3:
                        System.exit(1);
                        break;
                    default:
                        JOptionPane.showMessageDialog(null,"Opcion incorrecta");
                        menu();
                        break;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }while (salir==true);

    }
    public void ingresoInfo() {
        Boolean error=false;
        this.nombre= JOptionPane.showInputDialog("Ingrese su nombre completo");
        this.dui=JOptionPane.showInputDialog("Ingrese su Dui");
        do {
            try {
                if (dui.matches("^\\d{8}-\\d$")) {
                    System.out.println("es dui");
                    error=false;
                }else{
                    this.dui=JOptionPane.showInputDialog("Ingrese su  de manera correcta Dui");
                    error=true;
                }
            }catch (NullPointerException e){
                System.exit(1);
            }

        }while (error==true);

        this.nit=JOptionPane.showInputDialog("ingrese su nit");
        //valida formato nit
        do {
            error=false;
            try {
                if (nit.matches("^\\d{4}-\\d{6}-\\d{3}-\\d$")) {
                    System.out.println("es nit");
                }else{
                    this.nit=JOptionPane.showInputDialog("Ingrese su  de manera correcta NIT");
                    error=true;
                }
            }catch (NullPointerException e){
                System.exit(1);
            }
        }while (error==true);


        this.tipoEmpleado=JOptionPane.showInputDialog("Ingrese que tipo de empleado es:");

        if (tipoEmpleado.toLowerCase(Locale.ROOT).matches(".*\\banalista programador\\b.*")){

            System.out.println("fue programador ");

            this.salarioBase= Float.valueOf(800);
            Boolean error2=false;
            do {
                error2=false;
                try {
                    this.horas=Float.parseFloat(JOptionPane.showInputDialog("Ingrese las horas extras que trabajó"));
                    setValorHoras();
                }catch (NumberFormatException e){
                    this.horas=Float.parseFloat(JOptionPane.showInputDialog("Ingrese correctamente cantidad de horas extras que trabajó"));
                    error2=true;
                }
            }while (error2==true);



        }else{
            do {
                error=false;
                try {
                    this.salarioBase=Float.parseFloat(JOptionPane.showInputDialog("Ingrese su salario"));
                    horas= Float.valueOf(0);
                }catch (NumberFormatException e){
                    this.salarioBase=Float.parseFloat(JOptionPane.showInputDialog("Ingrese la cantidad correcta de su salario "));
                    error=true;
                }
            }while (error==true);



        }
        sueldoLiquido();
    }
    public double setValorHoras(){
        double extraHoras;
        double cantHoras=this.horas;
        if (cantHoras <= 20 &&cantHoras>=1) {
             extraHoras=cantHoras*9.25;
            //System.out.println("valor horas"+extraHoras);
        }else if (cantHoras >20) {
            double primerasHoras=20*9.25;
           // System.out.println("primeras horas"+primerasHoras);
             extraHoras=(cantHoras-20)*13.25+primerasHoras;
           // System.out.println("Horas extras "+extraHoras);
            //System.out.println("suma "+(primerasHoras+extraHoras));
        }else {
             extraHoras=0;
        }
        return extraHoras;
    }

    public double sueldoLiquido(){
        double salarioSubtotal=salarioBase+setValorHoras();
        double isss=salarioSubtotal*0.0525,afp=salarioSubtotal*0.0688,renta=salarioSubtotal*0.1;
        double salarioFinal= Double.parseDouble(df.format(salarioSubtotal-isss-afp-renta));
        System.out.println("Salario base "+salarioBase+" Salario subtotal "+salarioSubtotal+" Salario Liquido "+salarioFinal);
        return salarioFinal;
    }
    public void ingresoBdd() throws SQLException{
        pr=conexion.prepareStatement("INSERT INTO empleado(nombre,dui,nit,sueldoBase,sueldoFinal,tipo) VALUES (?,?,?,?,?,?)");
        pr.setString(1,nombre);
        pr.setString(2,dui);
        pr.setString(3,nit);
        pr.setFloat(4,salarioBase);
        pr.setFloat(5, (float) sueldoLiquido());
        pr.setString(6,tipoEmpleado);
        pr.executeUpdate();
    }
    public void mostrarDatosDui() throws SQLException{

    }
    public void mostrarDatos()throws SQLException{
        pr=conexion.prepareStatement("SELECT nombre,dui,nit,sueldoFinal,tipo from empleado");
        rs=pr.executeQuery();
        while (rs.next()){
            JOptionPane.showMessageDialog(null,"nombre: "+rs.getString("nombre")+"\nDUI: "+rs.getString("dui")
            +"\nNIT: "+rs.getString("nit")+"\nSalario Liquido :"+rs.getString("sueldoFinal")+"\nTipo de empleado:"+rs.getString("tipo"));

        }
    }
    public void verificarAnalistas()throws SQLException{
        pr=conexion.prepareStatement("SELECT COUNT(tipo) from empleado where tipo='analista programador'");
        rs= pr.executeQuery();

    }
    public void cierreConexion()throws SQLException{
        if (conexion != null) {
            conexion.close();
        }
    }
}
