package services;

import entities.Book;
import repositories.BookRepository;

import java.sql.SQLException;
import java.util.List;

public class BookService {
    private final BookRepository bookRepository = new BookRepository();

    public void addBook(Book book) throws SQLException {
        bookRepository.addBook(book);
    }

    public List<Book> getAllBooks() throws SQLException {
        return bookRepository.getAllBooks();
    }

    public boolean purchaseBook(int id, int quantity) throws SQLException {
        Book book = bookRepository.getBookById(id);
        if (book != null && book.getStock() >= quantity) {
            bookRepository.updateBookStock(id, book.getStock() - quantity);
            return true;
        }
        return false;
    }

    public void deleteBook(int id) throws SQLException {
        bookRepository.deleteBook(id);
    }
}
