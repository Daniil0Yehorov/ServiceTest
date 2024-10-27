package Probaaaa.Client;

import Probaaaa.Model.Book;
import Probaaaa.Service.BookService;
import jakarta.xml.ws.Service;
import javax.xml.namespace.QName;
import java.net.URL;

public class SoapClient {
    public static void main(String[] args) {
        try {
            //  WSDL URL
            URL wsdlURL = new URL("http://localhost:8081/ws/books?wsdl");

            //  QName (пространство имен и имя сервиса) из WSDL
            QName qname = new QName("http://services.Probaaaa/", "BookServiceImplService");

            Service service = Service.create(wsdlURL, qname);

            BookService bookService = service.getPort(BookService.class);

            Book book= new Book("dadadad","Dadad");
            String result = bookService.addBook(book);
            System.out.println("Результат: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
