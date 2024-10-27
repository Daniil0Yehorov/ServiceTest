package Probaaaa.services;

import Probaaaa.Model.Book;
import Probaaaa.Service.BookService;

import jakarta.jws.WebService;
import java.util.HashMap;
import java.util.Map;

@WebService(endpointInterface = "Probaaaa.Service.BookService")
public class BookServiceImpl implements BookService {
    private final Map<String, Book> bookStore = new HashMap<>();

    @Override
    public String addBook(Book book) {
        bookStore.put(book.getTitle(), book);
        return "Book added: " + book.getTitle();
    }

    @Override
    public Book getBook(String title) {
        return bookStore.get(title);
    }
}