package com.atliu.dao.impl;

import com.atliu.dao.BookDao;
import com.atliu.pojo.Book;

import java.util.List;

public class BookDaoImpl extends BaseDao implements BookDao {
    @Override
    public int addBook(Book book) {
        String sql = "insert into t_book (name,author,price,sales,stock,img_path) values(?,?,?,?,?,?);";
        return update(sql,book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImgPath());
    }

    @Override
    public int deleteBookById(Integer id) {
        String sql = "delete from t_book where id = ?";
        return update(sql,id);
    }

    @Override
    public int updateBook(Book book) {
        String sql = "update t_book set name = ? , author = ? , price = ? , sales = ? , stock = ? , img_path = ? where id = ?";
        return update(sql,book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImgPath(),book.getId());
    }

    @Override
    public Book queryBookById(Integer id) {
        String sql = "select id ,  name , author , price , sales , stock , img_path as imgPath from t_book where id = ?";
        return queryForOne(Book.class,sql,id);
    }

    @Override
    public List<Book> queryBooks() {
        String sql = "select id , name , author , price , sales , stock , img_path as imgPath from t_book";
        return queryForList(Book.class,sql);
    }

    @Override
    public Integer queryForPageTotalCount() {
        String sql = "select count(*) from t_book";
        Number count = (Number) queryForSingleValue(sql);
        return count.intValue();
    }

    @Override
    public List<Book> queryForPageItems(int begin, int pageSize) {
        //top(?) id , name , author , price , sales , stock , img_path as imgPath from t_book where id not in (select top(?) id from t_book)
        String sql = "select ";
        sql += "top(" + pageSize + ") id , name , author , price , sales , stock , img_path as imgPath from t_book where id not in (select ";
        sql += "top("+ begin +") id from t_book)";
        return queryForList(Book.class,sql);
    }

    @Override
    public Integer queryForPageTotalCountByPrice(int min, int max) {
        String sql = "select count(*) from t_book where 1=1 ";
        sql += "and price between '"+ min + "'and'" + max +"'";
        Number count = (Number) queryForSingleValue(sql);
        return count.intValue();
    }

    @Override
    public List<Book> queryForPageItemsByPrice(int begin, int pageSize, int min, int max) {
        //top(?) id , name , author , price , sales , stock , img_path as imgPath from t_book where id not in (select top(?) id from t_book)
        String sql = "select ";
        sql += "top(" + pageSize + ") id , name , author , price , sales , stock , img_path as imgPath from t_book where 1=1";
        sql += "and id not in (select top("+ begin +") id from t_book)";
        sql += "and price between '"+ min + "'and'" + max +"' order by price";
        return queryForList(Book.class,sql);
    }
}
