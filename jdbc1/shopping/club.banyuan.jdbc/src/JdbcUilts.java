import java.sql.*;

public class JdbcUilts {
    //        jdbc driver class name
    static String jdbcDriver = "com.mysql.cj.jdbc.Driver";
    //        数据库的连接字符串:连接协议，mysql的ip，端口，数据库名称，时区
    static String url = "jdbc:mysql://localhost:3306/shopping?&useSSL=false&serverTimezone=UTC";
    //登录用户名
    static String username = "root";
    //登录用户密码
    static String password = "03037799";

    static {
        try {
            Class.forName(jdbcDriver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return conn;
    }

    public static void closeConnection(Connection conn, Statement stmt) {
        try {
            if (conn != null) {
                conn.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void closeConnection(Connection conn, Statement stmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        closeConnection(conn,stmt);
    }

}

