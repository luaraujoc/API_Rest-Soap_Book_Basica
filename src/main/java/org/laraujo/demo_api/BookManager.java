package org.laraujo.demo_api;

import java.util.ArrayList;
import java.util.List;
public class BookManager {
    private static BookManager instance;
    private List<Book> books;

    private BookManager() {
        books = new ArrayList<>();
    }

    public static synchronized BookManager getInstance() {
        if (instance == null) {
            instance = new BookManager();
        }
        return instance;
    }

    public List<Book> getBooks() {
        return books;
    }
}
