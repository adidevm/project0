package com.revature.controllers;

import com.revature.DAOs.UserDAO;
import com.revature.models.User;
import io.javalin.http.Handler;

public class UserController {
    private UserDAO userDAO = new UserDAO();

    // Handler to insert a new user
    public Handler insertUser = ctx -> {
        User user = ctx.bodyAsClass(User.class);
        if (userDAO.addUser(user)) {
            ctx.status(201).result("User created successfully");
        } else {
            ctx.status(400).result("Failed to create user");
        }
    };

    // Handler to update a user
    public Handler updateUser = ctx -> {
        User user = ctx.bodyAsClass(User.class);
        if (userDAO.updateUser(user)) {
            ctx.status(200).result("User updated successfully");
        } else {
            ctx.status(400).result("Failed to update user");
        }
    };

    // Handler to delete a user by ID
    public Handler deleteUser = ctx -> {
        int userId = Integer.parseInt(ctx.pathParam("id"));
        if (userDAO.deleteUser(userId)) {
            ctx.status(200).result("User deleted successfully");
        } else {
            ctx.status(400).result("Failed to delete user");
        }
    };

    // Handler to get all users
    public Handler getAllUsers = ctx -> {
        ctx.json(userDAO.getAllUsers());
    };

    // Handler to get a user by ID
    public Handler getUserById = ctx -> {
        int userId = Integer.parseInt(ctx.pathParam("id"));
        User user = userDAO.getUserById(userId);
        if (user != null) {
            ctx.json(user);
        } else {
            ctx.status(404).result("User not found for id: " + userId);
        }
    };
}