package ro.qual.movieRentals;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ro.qual.movieRentals.ui.ClientConsole;

public class ClientApp {
    public static void main(String[] args) {

        System.out.println("hello");

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("ro.qual.movieRentals.config");

        ClientConsole clientConsole = context.getBean(ClientConsole.class);
        clientConsole.runConsole();
    }
}
