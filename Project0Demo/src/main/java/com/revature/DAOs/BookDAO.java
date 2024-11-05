package com.revature.DAOs;

import com.revature.models.Book;
import com.revature.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO implements BookDAOInterface {

    @Override
    public List<Book> getAllBooks() {
        try(Connection conn = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM books;";
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(sql);

            List<Book> books = new ArrayList<>();

            while(rs.next()) {
                Book book = new Book(
                        rs.getInt("book_id"),
                        rs.getString("title"),
                        rs.getInt("pages")
                );

                books.add(book);
            }

            return books;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Book getBookById(int book_id) {
        try(Connection conn = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM books WHERE book_id = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, book_id);
            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                return new Book(
                        rs.getInt("book_id"),
                        rs.getString("title"),
                        rs.getInt("pages")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean addBook(Book book) {
        try(Connection conn = ConnectionUtil.getConnection()) {
            String sql = "INSERT INTO books (title, pages) VALUES (?, ?);";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, book.getTitle());
            ps.setInt(2, book.getPages());

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean updateBook(Book book) {
        try(Connection conn = ConnectionUtil.getConnection()) {
            String sql = "UPDATE books SET title = ?, pages = ? WHERE book_id = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, book.getTitle());
            ps.setInt(2, book.getPages());
            ps.setInt(3, book.getBookId());

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deleteBook(int book_id) {
        try(Connection conn = ConnectionUtil.getConnection()) {
            String sql = "DELETE FROM books WHERE book_id = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, book_id);

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // New method to get books by user ID
    @Override
    public List<Book> getBooksByUserId(int userId) {
        List<Book> books = new ArrayList<>();
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM books WHERE book_id IN (SELECT book_id_fk FROM users WHERE user_id = ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Book book = new Book(
                        rs.getInt("book_id"),
                        rs.getString("title"),
                        rs.getInt("pages")
                );
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }
}