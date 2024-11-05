package com.revature.DAOs;

import com.revature.models.Book;
import java.util.List;

public interface BookDAOInterface {
    List<Book> getAllBooks();
    Book getBookById(int book_id);
    boolean addBook(Book book);
    boolean updateBook(Book book);
    boolean deleteBook(int book_id);

    // New method to get books by user ID
    List<Book> getBooksByUserId(int userId);
}