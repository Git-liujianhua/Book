package com.atliu.test;

import com.atliu.pojo.Book;
import com.atliu.pojo.Page;
import com.atliu.service.BookService;
import com.atliu.service.impl.BookServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class BookServiceTest {

    private BookService bookService = new BookServiceImpl();
    @Test
    public void addBook() {
        bookService.addBook(new Book(null,"陈翰是个狗","丽水",new BigDecimal(999909.99),1000000,0,null));
    }

    @Test
    public void deleteBookById() {
        bookService.deleteBookById(26);
    }

    @Test
    public void updateBook() {
        bookService.updateBook(new Book(26,"陈翰是个狗！！！！","丽水",new BigDecimal(999909.99),1000000,0,null));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookService.queryBookById(26));
    }

    @Test
    public void queryBooks() {
        for (Book queryBook : bookService.queryBooks()) {
            System.out.println(queryBook);
        }
    }

    @Test
    public void page(){
        System.out.println(bookService.page(1, Page.PAGE_SIZE));
    }


    @Test
    public void queryForPageTotalCountByPrice() {
        System.out.println(bookService.pageByPrice(1, Page.PAGE_SIZE,10,100));
    }


}