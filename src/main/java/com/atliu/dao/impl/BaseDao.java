package com.atliu.dao.impl;

import com.atliu.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseDao {

    //使用dbUtils来操作数据库
    private QueryRunner queryRunner = new QueryRunner();

    /**
     * update用来执行update/insert/delete语句
     * @param sql
     * @param args
     * @return
     */
    public int update(String sql,Object ... args){
        Connection connection = JdbcUtils.getConnection();
        try {
           return queryRunner.update(connection,sql,args);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        // }finally {
        //     JdbcUtils.close(connection);
        }
        // return -1;
    }

    /**
     * 查询返回一个JavaBean的sql语句
     * @param type  返回的对象类型
     * @param sql   返回的sql语句
     * @param args  返回的参数值
     * @param <T>   返回的类型的泛型
     * @return
     */
    public <T> T queryForOne(Class<T> type,String sql,Object ... args){
        Connection conn = JdbcUtils.getConnection();
        try {
            return queryRunner.query(conn,sql,new BeanHandler<T>(type),args);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        // }finally {
        //     JdbcUtils.close(conn);
        }
        // return null;
    }
    /**
     * 查询返回多个JavaBean的sql语句
     * @param type  返回的对象类型
     * @param sql   返回的sql语句
     * @param args  返回的参数值
     * @param <T>   返回的类型的泛型
     * @return
     */
    public <T> List<T> queryForList(Class<T> type,String sql,Object ... args){
        Connection conn = JdbcUtils.getConnection();
        try {
            return queryRunner.query(conn,sql,new BeanListHandler<>(type),args);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        // }finally {
        //     JdbcUtils.close(conn);
        }
        // return null;
    }

    /**
     * 执行返回一行一列的sql语句
     * @param sql   执行的sql语句
     * @param args  返回的参数值
     * @return
     */
    public Object queryForSingleValue(String sql , Object ... args){
        Connection conn = JdbcUtils.getConnection();
        try {
            return queryRunner.query(conn,sql,new ScalarHandler(),args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        // }finally {
        //     JdbcUtils.close(conn);
        }
        // return null;
    }
}
