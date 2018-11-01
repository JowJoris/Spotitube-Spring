package nl.han.dea.joris.database.dao;

import nl.han.dea.joris.database.connector.MySQLDatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DefaultDAO {
    protected static final Logger LOGGER = Logger.getLogger(DefaultDAO.class.getName() );
    MySQLDatabaseConnector connector = new MySQLDatabaseConnector();

    Connection connection = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    protected void closeConnections(){
        try {
            if(connection != null){connection.close();}
            if(pstmt != null){pstmt.close();}
            if(rs != null){rs.close();}
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
        }
    }
}
