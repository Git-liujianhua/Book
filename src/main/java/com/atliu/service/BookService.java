package com.atliu.service;

import com.atliu.pojo.Book;
import com.atliu.pojo.Page;

import java.util.List;

public interface BookService {

    public void addBook(Book book);

    public void deleteBookById(Integer id);

    public void updateBook(Book book);

    public Book queryBookById(Integer id);

    public List<Book> queryBooks();


    public Page<Book> page(int pageNo, int pageSize);

    Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max);
}
