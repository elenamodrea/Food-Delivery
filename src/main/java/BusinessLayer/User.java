package BusinessLayer;

import java.io.Serializable;

public class User implements Serializable {
    private int userId;
    private String username;
    private String typeuser;
    private String password;

    public User(int userId,String username, String typeuser, String password) {
        this.userId=userId;
        this.username = username;
        this.typeuser = typeuser;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTypeuser() {
        return typeuser;
    }

    public void setTypeuser(String typeuser) {
        this.typeuser = typeuser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
