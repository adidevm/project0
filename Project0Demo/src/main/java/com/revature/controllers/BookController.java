package com.revature.controllers;

import com.revature.models.Book;
import com.revature.DAOs.BookDAO;
import io.javalin.http.Handler;

import java.util.List;

public class BookController {

    BookDAO bookDAO = new BookDAO();

    // Handler to get all books
    public Handler getAllBooks = ctx -> {
        List<Book> books = bookDAO.getAllBooks();
        if (books != null) {
            ctx.json(books);
            ctx.status(200);
        } else {
            ctx.status(404).result("Books not found");
        }
    };

    // Handler to get books by user id
    public Handler getBooksByUserId = ctx -> {
        int userId = Integer.parseInt(ctx.pathParam("id"));
        List<Book> books = bookDAO.getBooksByUserId(userId);
        if (books != null) {
            ctx.json(books);
            ctx.status(200);
        } else {
            ctx.status(404).result("Books not found for user id: " + userId);
        }
    };

    // Handler to insert a new book (Changed to use addBook)
    public Handler insertBook = ctx -> {
        Book book = ctx.bodyAsClass(Book.class);
        if (bookDAO.addBook(book)) {  // Changed insertBook to addBook
            ctx.status(201).result("Book created successfully");
        } else {
            ctx.status(400).result("Failed to create book");
        }
    };

    // Handler to update a book
    public Handler updateBook = ctx -> {
        Book book = ctx.bodyAsClass(Book.class);
        if (bookDAO.updateBook(book)) {
            ctx.status(200).result("Book updated successfully");
        } else {
            ctx.status(400).result("Failed to update book");
        }
    };

    // Handler to delete a book
    public Handler deleteBook = ctx -> {
        int bookId = Integer.parseInt(ctx.pathParam("id"));
        if (bookDAO.deleteBook(bookId)) {
            ctx.status(200).result("Book deleted successfully");
        } else {
            ctx.status(400).result("Failed to delete book");
        }
    };
}