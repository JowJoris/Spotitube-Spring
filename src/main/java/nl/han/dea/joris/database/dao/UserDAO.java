package nl.han.dea.joris.database.dao;

import nl.han.dea.joris.database.objects.User;
import nl.han.dea.joris.exceptions.TokenException;
import nl.han.dea.joris.exceptions.UnauthorizedException;

import java.sql.SQLException;
import java.util.logging.Level;

public class UserDAO extends DefaultDAO {

    private static final String GET_USER = "SELECT * FROM `userdata` WHERE `username` = ? AND `password` = ?";
    private static final String VERIFY_TOKEN = "SELECT `user_id` FROM `userdata` WHERE `token` = ?";

    public User getUser(String username, String password) throws UnauthorizedException {
        User user= new User();
        try {
            connection = connector.getConnection();

            pstmt = connection.prepareStatement(GET_USER);
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            rs = pstmt.executeQuery();

            if (!rs.first()) {
                throw new UnauthorizedException();
            }
            if (rs.first()) {
                user.setUsername(rs.getString("username"));
                user.setToken(rs.getString("token"));
                user.setid(rs.getInt("user_id"));
                user.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
        } finally {
            closeConnections();
        }
        return user;
    }

    public int getID(String token) throws TokenException {
        int userID = 0;
        try {
            connection = connector.getConnection();
            pstmt = connection.prepareStatement(VERIFY_TOKEN);
            pstmt.setString(1, token);

            rs = pstmt.executeQuery();

            if(!rs.first()){
                throw new TokenException();
            }

            if (rs.first()) {
                userID = rs.getInt("user_id");
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
        } finally {
            closeConnections();
        }
        return userID;
    }
}
