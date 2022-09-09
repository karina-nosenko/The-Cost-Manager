package il.ac.shenkar.costmanager.server.controllers;

import il.ac.shenkar.costmanager.CostManagerException;
import il.ac.shenkar.costmanager.entities.User;
import il.ac.shenkar.costmanager.server.models.UserModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Users REST API
 */
@RestController
public class UserController {

    /**
     * Login user
     * @param authObj - user object with email and password
     * @return user if found or null
     * @throws CostManagerException
     */
    @PostMapping("/auth/login")
    public User login(@RequestBody User authObj) throws CostManagerException {

        String email = authObj.getEmail();
        String password = authObj.getPassword();

        UserModel userModel = new UserModel();
        return userModel.login(email, password);
    }

    /**
     * Logup user
     * @body userObj
     * @throws CostManagerException
     */
    @PostMapping("/auth/logup")
    public void logup(@RequestBody User userObj) throws CostManagerException {

        UserModel userModel = new UserModel();
        userModel.logup(userObj);
    }

    /**
     * Get all users
     * @return users list
     * @throws CostManagerException
     */
    @GetMapping("/users")
    public List<User> getAll() throws CostManagerException {

        UserModel userModel = new UserModel();
        return userModel.getAll();
    }

    /**
     * Get user by userId
     * @param userId
     * @return user with the given userId
     * @throws CostManagerException
     */
    @GetMapping("/users/{userId}")
    public User getById(@PathVariable("userId") String userId) throws CostManagerException {

        UserModel userModel = new UserModel();
        return userModel.getById(userId);
    }

    /**
     * Add new user
     * @param userObj
     * @throws CostManagerException
     */
    @PostMapping("/users")
    public void add(@RequestBody User userObj) throws CostManagerException {

        UserModel userModel = new UserModel();
        userModel.add(userObj);
    }

    /**
     * Delete a user with the given userId
     * @param userId
     * @throws CostManagerException
     */
    @DeleteMapping("/users/{userId}")
    public void delete(@PathVariable("userId") String userId) throws CostManagerException {

        UserModel userModel = new UserModel();
        userModel.delete(userId);
    }
}
