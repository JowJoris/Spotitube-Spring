package nl.han.dea.joris.login;

public class LoginResponseDTO {

    private String user;
    private String token;

    public void setUser(String user) {this.user = user;}
    public void setToken(String token) {this.token = token;}
    public String getUser() {return user;}
    public String getToken() {return token;}

}
