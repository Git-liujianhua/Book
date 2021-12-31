package com.atliu.test;

import com.atliu.dao.BookDao;
import com.atliu.dao.impl.BookDaoImpl;
import com.atliu.pojo.Book;
import com.atliu.pojo.Page;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class BookDaoTest {

    private BookDao bookDao = new BookDaoImpl();
    @Test
    public void addBook() {
        bookDao.addBook(new Book(null,"我很帅！","刘峙杰",new BigDecimal(99999),999999,0,null));
    }

    @Test
    public void deleteBookById() {
        bookDao.deleteBookById(25);
    }

    @Test
    public void updateBook() {
        bookDao.updateBook(new Book(24,"我帅的一塌糊涂！","刘峙杰",new BigDecimal(999999.99),999999,0,null));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookDao.queryBookById(24));
    }

    @Test
    public void queryBooks() {
        for (Book queryBook : bookDao.queryBooks()) {
            System.out.println(queryBook);
        }
    }

    @Test
    public void queryForPageTotalCount() {
        System.out.println(bookDao.queryForPageTotalCount());
    }

    @Test
    public void queryForPageItems() {
        List<Book> books = bookDao.queryForPageItems(4, Page.PAGE_SIZE);
        for (Book book : books) {
            System.out.println(book);
        }
    }

    @Test
    public void queryForPageTotalCountByPrice() {
        System.out.println(bookDao.queryForPageTotalCountByPrice(10,100));
    }

    @Test
    public void queryForPageItemsByPrice() {
        List<Book> books = bookDao.queryForPageItemsByPrice(0, Page.PAGE_SIZE,10,100);
        for (Book book : books) {
            System.out.println(book);
        }
    }
}