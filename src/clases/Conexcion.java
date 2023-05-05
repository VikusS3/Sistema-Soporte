package clases;

import java.sql.*;

public class Conexcion {

    public static Connection con = null;
    private static String bd = "bd_ds";
    public static String usuario = "root";
    public static String passw = "123456789";
    public static String url = "jdbc:mysql://localhost:3306/bd_ds?autoReconnect=true&useSSL=false";

    public static Connection abrir() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, usuario, passw);
            System.out.println("Conexión exitosa:" + con);
        } catch (Exception e) {
            System.out.println("Error en la conexion...");
            e.printStackTrace();
            return null;
        }
        return con;
    }

    public static void cerrar() {
        try {
            if (con != null) {
                con.close();
            }
        } catch (Exception e) {
            System.out.println(
                    "Error: No se logro cerrar la conexion:\n" + e);
        }
    }
}
