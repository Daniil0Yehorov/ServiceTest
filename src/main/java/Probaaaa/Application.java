package Probaaaa;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import Probaaaa.services.BookServiceImpl;



import jakarta.xml.ws.Endpoint;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner publishWebService() {
		return args -> {
			Endpoint.publish("http://localhost:8081/ws/books?wsdl", new BookServiceImpl());//РАБОТАЕТТТ
			System.out.println("SOAP Web Service is published!");
		};
	}

}