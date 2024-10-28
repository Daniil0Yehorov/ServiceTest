package Probaaaa.services;

import Probaaaa.Model.Book;
import Probaaaa.Model.BookStoreWrapper;
import Probaaaa.Service.BookService;

import jakarta.jws.WebService;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebService(endpointInterface = "Probaaaa.Service.BookService")
public class BookServiceImpl implements BookService {

    private final Map<String, Book> bookStore = new HashMap<>();
    private static final String XML_FILE = "src/main/resources/static/xml/books.xml";

    @Override
    public String addBook(Book book) {
        loadBooksFromXML();
        bookStore.put(book.getTitle(), book);
        saveBooksToXML();
        return "Book added: " + book.getTitle();
    }

    @Override
    public Book getBook(String title) {
        loadBooksFromXML();
        System.out.println("Searching for book with title: " + title);
        Book foundBook = bookStore.get(title);
        System.out.println("Found book: " + foundBook);
        return foundBook;
    }
    // Метод для сохранения данных в XML
    private void saveBooksToXML() {
        try {
            JAXBContext context = JAXBContext.newInstance(BookStoreWrapper.class);
            var marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            BookStoreWrapper wrapper = new BookStoreWrapper();
            wrapper.setBooks(new ArrayList<>(bookStore.values()));

            marshaller.marshal(wrapper, new File(XML_FILE));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    // Метод для загрузки данных из XML
    private void loadBooksFromXML() {
        try {
            File file = new File(XML_FILE);
            if (!file.exists()) return;

            JAXBContext context = JAXBContext.newInstance(BookStoreWrapper.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            BookStoreWrapper wrapper = (BookStoreWrapper) unmarshaller.unmarshal(file);
            List<Book> books = wrapper.getBooks();
            if (books != null) {
                for (Book book : books) {
                    bookStore.put(book.getTitle(), book);
                }
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}