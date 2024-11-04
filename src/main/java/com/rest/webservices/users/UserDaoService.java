package com.rest.webservices.users;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDaoService {
    private static List<User> users = new ArrayList<>();
    private static int userCount = 0;
    static {
        users.add(new User(++userCount, "Adam", LocalDate.now().minusYears(30)));
        users.add(new User(++userCount, "Eve", LocalDate.now().minusYears(13)));
        users.add(new User(++userCount, "Suraj", LocalDate.now().minusYears(23)));
        users.add(new User(++userCount, "Ashu", LocalDate.now().minusYears(22)));
        users.add(new User(++userCount, "Gopal", LocalDate.now().minusYears(25)));
    }
    public List<User> findAllUsers() {
        return users;
    }

    public User findUserById(int id) {
        return users.stream().filter(user -> user.getId() == id).findFirst().orElse(null);
    }

    public User saveUser(User user) {
        user.setId(++userCount);
        users.add(user);
        return user;
    }

    public void deleteUser(int id) {
        users.removeIf(user -> user.getId() == id);
    }

    public User updateUserById(int id, User user) {
        User currentUser = findUserById(id);
        if (user != null) {
            currentUser.setName(user.getName());
            currentUser.setBirthDate(user.getBirthDate());
        }
        return currentUser;
    }

}
