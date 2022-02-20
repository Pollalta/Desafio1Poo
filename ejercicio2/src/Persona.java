import javax.swing.*;
import java.sql.*;
import java.text.DecimalFormat;


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
            ingresoInfo();
           // ingresoBdd();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void ingresoInfo() {
        this.nombre= JOptionPane.showInputDialog("Ingrese su nombre completo");
        this.dui=JOptionPane.showInputDialog("Ingrese su Dui");
        //Valida formato dui
        if (dui.matches("^\\d{8}-\\d$")) {
            System.out.println("es dui");
        }else{
            System.out.println("no es dui");
        }
        this.nit=JOptionPane.showInputDialog("ingrese su nit");
        //valida formato nit
        if (nit.matches("^\\d{4}-\\d{6}-\\d{3}-\\d$")) {
            System.out.println("es nit");
        }else{
            System.out.println("no es nit");
        }
        this.tipoEmpleado=JOptionPane.showInputDialog("Ingrese que tipo de empleado es:");

        if (tipoEmpleado.matches(".*\\bprogramador\\b.*")|| tipoEmpleado.matches(".*\\bProgramador\\b.*")){

            System.out.println("fue programador ");

            this.salarioBase= Float.valueOf(800);
            this.horas=Float.parseFloat(JOptionPane.showInputDialog("Ingrese las horas extras que trabajó"));
            setValorHoras();

        }else{
            this.salarioBase=Float.parseFloat(JOptionPane.showInputDialog("Ingrese su salario"));
            horas= Float.valueOf(0);

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
       // System.out.println("Salario base "+salarioBase+" Salario subtotal "+salarioSubtotal+" Salario Liquido "+salarioFinal);
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
    public void cierreConexion()throws SQLException{
        if (conexion != null) {
            conexion.close();
        }
    }
}
