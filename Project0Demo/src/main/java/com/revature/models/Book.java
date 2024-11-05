package com.revature.models;

public class Book {

    private int bookId;
    private String title;
    private int pages;

    public Book() {
    }

    public Book(int bookId, String title, int pages) {
        this.bookId = bookId;
        this.title = title;
        this.pages = pages;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", pages=" + pages +
                '}';
    }
}