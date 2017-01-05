package restaurant_service;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;

/**
 * Gestión de conexiones con la base de datos.
 */
public final class DataManager {
    /**
     * Constructor privado (para evitar instanciación, debido a que es una clase de métodos estáticos).
     */
    private DataManager() {}

    /**
     * Obtiene la conexión con la base de datos principal.
     * @return Conexión de base de datos.
     */
    public static Connection getDatabaseConnection() {
        try {
            return (Connection) DriverManager.getConnection("jdbc:mysql://localhost/restaurant?autoReconnect=true&useSSL=false", "root", "1234");
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
