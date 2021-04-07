package com.gao.test;

import com.gao.dao.BookDao;
import com.gao.dao.impl.BookDaoImpl;
import com.gao.pojo.Book;
import com.gao.pojo.Page;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class BookDaoTest {

    private BookDao bookDao = new BookDaoImpl();

    @Test
    public void addBook() {
    }

    @Test
    public void deleteBookById() {
        bookDao.addBook(new Book(null, "杰哥帅", "19111", new BigDecimal(99999), 110000, 0, null));
    }

    @Test
    public void updateBook() {
        bookDao.updateBook(new Book(21, "dajiadoushaui", "11111", new BigDecimal(99999), 110000, 0, null));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookDao.queryBookById(21));
    }

    @Test
    public void queryBooks() {
        for (Book queryBook : bookDao.queryBooks()) {
            System.out.println(queryBook);
        }
    }

    @Test
    public void queryForPageTotalCount(){
        System.out.println(bookDao.queryForPageTotalCount());
    }

    @Test
    public void queryForPageItems() {
        System.out.println(bookDao.queryForPageItems(8, 4));
    }

    @Test
    public void queryForPageTotalCountByPrice(){
        System.out.println(bookDao.queryForPageTotalCountByPrice(10, 50));
    }

    @Test
    public void queryForPageItemsByPrice() {
        System.out.println(bookDao.queryForPageItemsByPrice(0, Page.PAGE_SIZE, 10, 50));
    }

}