package org.laraujo.demo_api;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.ServletException;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "bookServlet", urlPatterns = {"/book-servlet"})
public class BookServlet extends HttpServlet {
    private final List<Book> books = BookManager.getInstance().getBooks();

    /* Esta opcion era cuando solo queres tener un metodo GET que obtenia todos los libros,

    Luego implemente un doGet que segun un parametro que le das lo que hace

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // ---- OPCION JSON ------
        //resp.setContentType("application/json");
        //ObjectMapper mapper = new ObjectMapper();
        //mapper.writeValue(resp.getWriter(), books);

        // ---- OPCION WEB ------
        req.setAttribute("books", books);
        req.getRequestDispatcher("listBooks.jsp").forward(req, resp);
    }

     */

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if (action != null) {
            switch (action) {
                case "all":
                    getAllBooks(req, resp);
                    break;
                case "byTitle":
                    getBooksByTitle(req, resp);
                    break;
                case "byAuthor":
                    getBooksByAuthor(req, resp);
                    break;
                default:
                    resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    break;
            }
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private void getAllBooks(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("books", books);
        req.getRequestDispatcher("listBooks.jsp").forward(req, resp);
    }

    private void getBooksByTitle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String titleToFind = req.getParameter("title");
        System.out.println(titleToFind);
        List<Book> booksByTitle = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().equals(titleToFind)) {
                booksByTitle.add(book);
                System.out.println("encontre");
            }
        }

        if (!booksByTitle.isEmpty()) {
            ObjectMapper mapper = new ObjectMapper();
            resp.setContentType("application/json");
            mapper.writeValue(resp.getWriter(), booksByTitle);
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void getBooksByAuthor(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String authorToFind = req.getParameter("author");
        List<Book> booksByAuthor = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().equals(authorToFind)) {
                booksByAuthor.add(book);
            }
        }

        if (!booksByAuthor.isEmpty()) {
            ObjectMapper mapper = new ObjectMapper();
            resp.setContentType("application/json");
            mapper.writeValue(resp.getWriter(), booksByAuthor);
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(req.getReader());
        Book book = mapper.readValue(req.getReader(), Book.class);
        books.add(book);
        resp.setStatus(HttpServletResponse.SC_CREATED);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String titleToDelete = req.getParameter("title").trim();
        boolean removed = books.removeIf(book -> book.getTitle().equals(titleToDelete));
        if (removed) {
            resp.setStatus(HttpServletResponse.SC_OK);
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        Book updatedBook = mapper.readValue(req.getReader(), Book.class);
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getTitle().equals(updatedBook.getTitle().trim())) {
                books.set(i, updatedBook);
                resp.setStatus(HttpServletResponse.SC_OK);
                return;
            }
        }
        resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
    }


}

