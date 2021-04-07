package com.gao.test;

import com.gao.pojo.Book;
import com.gao.pojo.Page;
import com.gao.service.BookService;
import com.gao.service.impl.BookServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

public class BookServiceTest {

    private BookService bookService = new BookServiceImpl();
    @Test
    public void addBook() {
        bookService.addBook(new Book(null, "javalearning", "1125", new BigDecimal(100000), 10000000, 0, null));
    }

    @Test
    public void deleteBookById() {
        bookService.deleteBookById(20);
    }

    @Test
    public void updateBook() {
        bookService.updateBook(new Book(20, "哈哈哈", "1125", new BigDecimal(10), 10, 11000, null));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookService.queryBookById(19));
    }

    @Test
    public void queryBooks() {
        for (Book queryBook : bookService.queryBooks()) {
            System.out.println(queryBook);
        }
    }

    @Test
    public void page() {
        System.out.println(bookService.page(1, Page.PAGE_SIZE));
    }
}