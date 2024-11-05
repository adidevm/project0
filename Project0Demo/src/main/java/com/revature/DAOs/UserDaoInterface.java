package com.revature.DAOs;

import com.revature.models.User;

import java.util.List;

public interface UserDaoInterface {

    boolean addUser(User user);

    boolean updateUser(User user);

    boolean deleteUser(int user_id);

    List<User> getAllUsers();

    User getUserById(int user_id);
}
