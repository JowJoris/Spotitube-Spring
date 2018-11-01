package nl.han.dea.joris.database.connector;

import nl.han.dea.joris.database.dao.DefaultDAO;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySQLDatabaseConnector {

    private static final Logger LOGGER = Logger.getLogger(DefaultDAO.class.getName() );

    private Properties prop = new Properties();

    private Map<String, String> getProperties(){
        InputStream input;
        Map<String, String> properties = new HashMap<>();
        try{
            input = new FileInputStream("C:\\Users\\Joris\\Documents\\GitHub\\Spotitube-JEE\\src\\main\\resources\\mysql.properties");
            prop.load(input);
            properties.put("driver", prop.getProperty("driverPropertie"));
            properties.put("connectionstring", prop.getProperty("connectionstringPropertie"));
            properties.put("username", prop.getProperty("usernamePropertie"));
            properties.put("password", prop.getProperty("passwordPropertie"));
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
        }
        return properties;

    }

    public Connection getConnection(){
        Map<String, String> properties = getProperties();
        Connection connection;
        try {
            Class.forName(properties.get("driver"));
            connection = DriverManager.getConnection(properties.get("connectionstring"), properties.get("username"), properties.get("password"));
            return connection;
        } catch (ClassNotFoundException | SQLException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
        }
        return null;
    }
}
