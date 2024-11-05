package com.revature.models;

public class User {
    private int userId;
    private String firstName;
    private String lastName;
    private Book book;

    public User() {
    }

    public User(int userId, String firstName, String lastName, Book book) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.book = book;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", book=" + book +
                '}';
    }
}