package com.epochge.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 */
public class JDBCUtils {
//获取包
    private static DataSource ds;

    static {
        //1.:加载jar包
        //2.：加载配置文件
        Properties properties = new Properties();
        //获取classpath路径下的资源文件的输入流
        InputStream ras = JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");

        try {
            properties.load(ras);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //3.获取连接对象
        try {
            ds = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //获取连接对象
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    //释放资源
    public static void close(PreparedStatement stmt, Connection conn) {
        close(null, stmt, conn);
    }

    public static void close(ResultSet rs, PreparedStatement stmt, Connection conn) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    //获取连接池的方法
    public static DataSource getDataSource() {
        return ds;
    }
}
