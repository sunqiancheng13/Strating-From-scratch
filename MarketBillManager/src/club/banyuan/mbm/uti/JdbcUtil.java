package club.banyuan.mbm.uti;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcUtil {
    //用户名
    static final String USRE = "root";
    //数据库密码
    static final String PASSWORD = "03037799";
    //驱动
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    //连接串
    static final String DB_URL = "jdbc:mysql://localhost:3306/javasql?useSSL=false";


    protected static Connection connection;
    protected static PreparedStatement preparedStatement;
    protected static  ResultSet resultSet;

    static {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(DB_URL,USRE,PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }

    public static Map<String, Object> queryQne(String sql, Object... params) {
        Map<String, Object> map = new HashMap<String, Object>();
        connection = getConnection();
        try {
            preparedStatement = connection.prepareStatement(sql);
            if (params != null && params.length > 0) {
                for (int i = 0; i < params.length; i++) {
                    preparedStatement.setObject(i + 1, params[i]);
                }
            }
            resultSet = preparedStatement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int colLen = metaData.getColumnCount();
            while (resultSet.next()) {
                for (int i = 0; i < colLen; i++) {
                    String colName = metaData.getColumnName(i + 1);
                    Object colValue = resultSet.getObject(colName);
                    if (colValue == null) {
                        colValue = "";
                    }
                    map.put(colName, colValue);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            //关闭连接
            close(connection,preparedStatement,resultSet);
        }
        return map;
    }


    public static List<Map<String, Object>> queryAll(String sql, Object... params) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        connection = getConnection();
        try {
            preparedStatement = connection.prepareStatement(sql);
            if (params != null && params.length > 0) {
                for (int i = 0; i < params.length; i++) {
                    preparedStatement.setObject(i + 1, params[i]);
                }
            }
             resultSet = preparedStatement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int colLen = metaData.getColumnCount();
            while (resultSet.next()) {
                Map<String, Object> map = new HashMap<String, Object>();
                for (int i = 0; i < colLen; i++) {
                    String colName = metaData.getColumnName(i + 1);
                    Object colValue = resultSet.getObject(colName);
                    if (colValue == null) {
                        colValue = "";
                    }
                    map.put(colName, colValue);
                }
                list.add(map);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            //关闭连接
            close(connection,preparedStatement,resultSet);
        }
        return list;
    }

    public static int update(String sql, Object... params) {
        int result = 0;
        connection = getConnection();
        try {
            preparedStatement = connection.prepareStatement(sql);
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    preparedStatement.setObject(i + 1, params[i]);
                }
            }
            result = preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            //关闭连接
            close(connection,preparedStatement,resultSet);
        }
        return result;
    }




    public static void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {

        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            if (pstmt != null) {
                pstmt.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

