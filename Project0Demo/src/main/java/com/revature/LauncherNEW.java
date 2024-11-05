package com.revature;

import com.revature.controllers.BookController;
import com.revature.controllers.UserController;
import io.javalin.Javalin;

public class LauncherNEW {

    public static void main(String[] args) {

        // Initialize Javalin app
        var app = Javalin.create().start(7000);

        // Create instances of your controllers
        BookController bookController = new BookController();
        UserController userController = new UserController();

        // Routes for Users
        app.get("/users", userController.getAllUsers); // Get all users
        app.get("/users/{id}", userController.getUserById); // Get user by id
        app.post("/users", userController.insertUser); // Insert a new user
        app.put("/users", userController.updateUser); // Update a user
        app.delete("/users/{id}", userController.deleteUser); // Delete a user by id

        // Routes for Books
        app.get("/books", bookController.getAllBooks); // Get all books
        app.get("/books/user/{id}", bookController.getBooksByUserId); // Get books by user id
        app.post("/books", bookController.insertBook); // Insert a new book
        app.put("/books", bookController.updateBook); // Update a book
        app.delete("/books/{id}", bookController.deleteBook); // Delete a book by id

        System.out.println("Javalin server running on http://localhost:7000");
    }
}