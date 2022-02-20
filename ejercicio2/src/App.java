import java.sql.SQLException;

public class App {
    public static void main(String[] args) throws SQLException {
        Persona persona=new Persona();
        persona.cierreConexion();
    }

}
