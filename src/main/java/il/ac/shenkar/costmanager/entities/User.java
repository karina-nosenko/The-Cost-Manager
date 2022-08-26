package il.ac.shenkar.costmanager.entities;

import static java.util.UUID.randomUUID;

public class User {

    private String userId;
    private String username;
    private String email;
    private String password;

    public User() {
        setUserId("");
        setUsername("");
        setEmail("");
        setPassword("");
    }

    public User(String userId, String username, String email, String password) {
        if (userId == null || userId.isBlank()) {
            setUserId(randomUUID().toString());
        } else {
            setUserId(userId);
        }

        setUsername(username);
        setEmail(email);
        setPassword(password);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User {" +
                "userId=" + userId +
                ", username=" + username +
                ", email=" + email +
                ", password=" + password +
                '}';
    }
}
