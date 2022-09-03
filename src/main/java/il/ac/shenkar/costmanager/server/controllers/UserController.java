package il.ac.shenkar.costmanager.server.controllers;

import il.ac.shenkar.costmanager.CostManagerException;
import il.ac.shenkar.costmanager.entities.User;
import il.ac.shenkar.costmanager.server.models.UserModel;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @PostMapping("/auth/login")
    public User login(@RequestBody User authObj) throws CostManagerException {

        String email = authObj.getEmail();
        String password = authObj.getPassword();

        UserModel userModel = new UserModel();
        return userModel.login(email, password);
    }

    @GetMapping("/users")
    public List<User> getAll() throws CostManagerException {

        UserModel userModel = new UserModel();
        return userModel.getAll();
    }

    @GetMapping("/users/{userId}")
    public User getById(@PathVariable("userId") String userId) throws CostManagerException {

        UserModel userModel = new UserModel();
        return userModel.getById(userId);
    }

    @PostMapping("/users")
    public void add(@RequestBody User userObj) throws CostManagerException {

        UserModel userModel = new UserModel();
        userModel.add(userObj);
    }

    @DeleteMapping("/users/{userId}")
    public void delete(@PathVariable("userId") String userId) throws CostManagerException {

        UserModel userModel = new UserModel();
        userModel.delete(userId);
    }
}
