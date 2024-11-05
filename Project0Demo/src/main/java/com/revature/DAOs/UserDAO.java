package com.revature.DAOs;

import com.revature.models.Book;
import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements UserDaoInterface {

    @Override
    public List<User> getAllUsers() {
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "SELECT u.user_id, u.first_name, u.last_name, b.book_id, b.title, b.pages " +
                    "FROM users u " +
                    "JOIN books b ON u.book_id_fk = b.book_id;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            List<User> users = new ArrayList<>();

            while (rs.next()) {
                Book book = new Book(rs.getInt("book_id"), rs.getString("title"), rs.getInt("pages"));
                User user = new User(
                        rs.getInt("user_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        book
                );
                users.add(user);
            }

            return users;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public User getUserById(int userId) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "SELECT u.user_id, u.first_name, u.last_name, b.book_id, b.title, b.pages " +
                    "FROM users u " +
                    "JOIN books b ON u.book_id_fk = b.book_id WHERE user_id = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Book book = new Book(rs.getInt("book_id"), rs.getString("title"), rs.getInt("pages"));
                return new User(
                        rs.getInt("user_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        book
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean addUser(User user) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "INSERT INTO users (first_name, last_name, book_id_fk) VALUES (?, ?, ?);";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setInt(3, user.getBook().getBookId());

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean updateUser(User user) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "UPDATE users SET first_name = ?, last_name = ?, book_id_fk = ? WHERE user_id = ?;";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setInt(3, user.getBook().getBookId());
            ps.setInt(4, user.getUserId());

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deleteUser(int userId) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "DELETE FROM users WHERE user_id = ?;";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}