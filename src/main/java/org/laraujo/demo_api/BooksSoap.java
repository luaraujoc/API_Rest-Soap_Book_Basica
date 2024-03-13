package org.laraujo.demo_api;

import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.SerializationFeature;
//import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
//import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import java.util.ArrayList;
import java.util.List;

@WebService
public class BooksSoap {
    private final List<Book> books = BookManager.getInstance().getBooks();

    @WebMethod
    public void addBook(String jsonBook) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Book book = mapper.readValue(jsonBook, Book.class);
            books.add(book);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @WebMethod
    public String getBook() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(books);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @WebMethod
    public void editBook(String title, String jsonUpdatedBook) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Book updatedBook = mapper.readValue(jsonUpdatedBook, Book.class);
            for (Book book : books) {
                if (book.getTitle().equals(title)) {
                    book.setTitle(updatedBook.getTitle());
                    book.setAuthor(updatedBook.getAuthor());
                    book.setPublicationYear(updatedBook.getPublicationYear());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @WebMethod
    public void deleteBook(String title) {
        books.removeIf(book -> book.getTitle().equals(title));
    }

    @WebMethod
    public String getBookByTitle(String title) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            for (Book book : books) {
                if (book.getTitle().equals(title)) {
                    return mapper.writeValueAsString(book);
                }
            }
            return null; // Si no se encuentra el libro
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @WebMethod
    public String getBooksByAuthor(String author) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<Book> authorBooks = new ArrayList<>();
            for (Book book : books) {
                if (book.getAuthor().equals(author)) {
                    authorBooks.add(book);
                }
            }
            return mapper.writeValueAsString(authorBooks);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @WebMethod
    public int countBooks() {
        return books.size();
    }

}
