package il.ac.shenkar.costmanager.entities;

import static java.util.UUID.randomUUID;

/**
 * Class describes a user
 */
public class User {

    private String userId;
    private String username;
    private String email;
    private String password;

    /**
     * Default constructor
     */
    public User() {
        setUserId("");
        setUsername("");
        setEmail("");
        setPassword("");
    }

    /**
     * Constructor
     * @param userId - if null - a random UUID will be set
     * @param username
     * @param email
     * @param password
     */
    public User(String userId, String username, String email, String password) {

        // set random UUID if the userId is null
        if (userId == null || userId.isBlank()) {
            setUserId(randomUUID().toString());
        } else {
            setUserId(userId);
        }

        // set the rest of the parameters
        setUsername(username);
        setEmail(email);
        setPassword(password);
    }

    /**
     * @return userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Overrides the toString() method
     * @return serialized params
     */
    @Override
    public String toString() {
        return "User {" +
                "userId=" + userId +
                ", username=" + username +
                ", email=" + email +
                ", password=" + password +
                '}';
    }

    /**
     * Overrides equals() method
     * @param obj
     * @return boolean whether obj parameters are equal to the object's
     */
    @Override
    public boolean equals(Object obj) {

        User user = (User)obj;
        return user.getUserId().equals(userId) &&
                user.getUsername().equals(username) &&
                user.getEmail().equals(email) &&
                user.getPassword().equals(password);
    }
}
