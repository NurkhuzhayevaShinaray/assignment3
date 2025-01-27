package main;

import entities.Book;
import services.BookService;

import java.sql.SQLException;
import java.util.Scanner;

public class BookStoreApp {
    private static final BookService bookService = new BookService();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n1. Add Book\n2. View All Books\n3. Purchase Book\n4. Delete Book\n5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (choice) {
                    case 1 -> {
                        System.out.print("Enter title: ");
                        String title = scanner.nextLine();
                        System.out.print("Enter author: ");
                        String author = scanner.nextLine();
                        System.out.print("Enter genre: ");
                        String genre = scanner.nextLine();
                        System.out.print("Enter price: ");
                        double price = scanner.nextDouble();
                        System.out.print("Enter stock: ");
                        int stock = scanner.nextInt();
                        scanner.nextLine();
                        bookService.addBook(new Book(0, title, author, genre, price, stock));
                        System.out.println("Book added successfully!");
                    }
                    case 2 -> bookService.getAllBooks().forEach(System.out::println);
                    case 3 -> {
                        System.out.print("Enter book ID to purchase: ");
                        int id = scanner.nextInt();
                        System.out.print("Enter quantity: ");
                        int quantity = scanner.nextInt();
                        scanner.nextLine();
                        if (bookService.purchaseBook(id, quantity)) {
                            System.out.println("Purchase successful!");
                        } else {
                            System.out.println("Purchase failed. Insufficient stock or invalid book ID.");
                        }
                    }
                    case 4 -> {
                        System.out.print("Enter book ID to delete: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        bookService.deleteBook(id);
                        System.out.println("Book deleted successfully!");
                    }
                    case 5 -> {
                        System.out.println("Exiting...");
                        scanner.close();
                        System.exit(0);
                    }
                    default -> System.out.println("Invalid choice!");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
