package com.atliu.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {

    private static DruidDataSource dataSource;
    private static ThreadLocal<Connection> conns = new ThreadLocal<Connection>();

    
    static {
        
        try {

            Properties properties = new Properties();
            //读取jdbc.properties的配置文件
            InputStream resource = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            //从流中加载数据
            properties.load(resource);
            //创建数据库连接池
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接池中的连接
     * @return如果返回null则说明连接失败
     */
    public static Connection getConnection(){
        Connection connection = conns.get();
        if (connection == null){
            try {
                connection = dataSource.getConnection();//从数据库连接池中获取连接
                conns.set(connection);//保存到ThreadLocal对象中，供后面的jdbc操作使用
                connection.setAutoCommit(false);//设置为手动管理事务
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // Connection conn = null;
        //
        // try {
        //     conn = dataSource.getConnection();
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }
        // return conn;
        return connection;
    }

    /**
     * 提交事务并关闭释放连接
     */
    public static void commitAndClose(){
        Connection connection = conns.get();
        if (connection != null){//如果不等于null，说明之前使用过连接，操作过数据库
            try {
                connection.commit();//提交事务

            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    connection.close();//关闭连接,释放资源
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        //一定要执行remove操作，否则就会出错。（因为Tomcat服务器底层使用了线程池的技术）
        conns.remove();
    }

    /**
     * 回滚事务并关闭释放连接
     */
    public static void rollbackAndClose(){
        Connection connection = conns.get();
        if (connection != null){//如果不等于null，说明之前使用过连接，操作过数据库
            try {
                connection.rollback();//回滚事务
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    connection.close();//关闭连接,释放资源
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        //一定要执行remove操作，否则就会出错。（因为Tomcat服务器底层使用了线程池的技术）
        conns.remove();
    }

    /**
     * 连接介绍后关闭连接放回连接池
     * @param conn
     */
    // public static void close(Connection conn){
    //
    //         if (conn != null){
    //             try {
    //                 conn.close();
    //             } catch (Exception e) {
    //                 e.printStackTrace();
    //             }
    //         }
    // }
}
