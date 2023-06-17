package main.Controller;

import com.mysql.cj.xdevapi.SessionFactory;
import main.DAO.UserRepository;
import main.DAO.UserRepositoryImpl;
import main.objects.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    private SessionFactory sessionFactory = new SessionFactory();
    @Autowired
    private UserRepository userRepository;
    public static User main;

    @PostMapping("/login")
    @ResponseBody
    public String login(@RequestBody LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        // login logic
        User u = new UserRepositoryImpl(sessionFactory).getUserByUsername(username);
        if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
            main = u;
            return "success";
        } else {
            return "failure";
        }
    }

    @PostMapping("/register")
    @ResponseBody
    public String register(@RequestBody RegisterRequest registerRequest) {
        String username = registerRequest.getUsername();
        String email = registerRequest.getEmail();
        String password = registerRequest.getPassword();

        // registration
        User u = new UserRepositoryImpl(sessionFactory).getUserByUsername(username);

        //registration result
        if (!u.getUsername().equals(username) && !u.getEmail().equals(email)) {
            return "success";
        } else {
            return "failure";
        }
    }
    public User getMainUser(){
        return main;
    }
}