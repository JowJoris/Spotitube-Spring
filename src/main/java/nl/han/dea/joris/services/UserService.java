package nl.han.dea.joris.services;

import nl.han.dea.joris.database.dao.UserDAO;
import nl.han.dea.joris.database.objects.User;
import nl.han.dea.joris.exceptions.TokenException;
import nl.han.dea.joris.exceptions.UnauthorizedException;
import nl.han.dea.joris.login.LoginResponseDTO;
import org.springframework.context.annotation.Bean;

public class UserService {

    private UserDAO userDAO = new UserDAO();

    public LoginResponseDTO authenticate(String username, String password) throws UnauthorizedException {
        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();

        User user = userDAO.getUser(username, password);

        loginResponseDTO.setUser(user.getUsername());
        loginResponseDTO.setToken(user.getToken());
        return loginResponseDTO;
    }

    public int verifyToken(String token) throws TokenException {
        return userDAO.getID(token);
    }

    @Bean
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
}