package main.DAO;

import main.objects.User;

import java.util.List;

public interface UserRepository {
    User getUserByUsername(String username);
    User getUserById(int userId);
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(User user);
    List<User> getAllUsers();
}
