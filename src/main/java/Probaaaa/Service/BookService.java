package Probaaaa.Service;

import Probaaaa.Model.Book;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

@WebService
public interface BookService {
    @WebMethod
    String addBook(Book book);

    @WebMethod
    Book getBook(String title);
}