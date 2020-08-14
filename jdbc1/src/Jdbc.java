import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Jdbc {
    Connection conn = null;
    Statement stmt = null;

    public Statement connection() throws ClassNotFoundException, SQLException {
//        jdbc driver class name
        String jdbcDriver = "com.mysql.cj.jdbc.Driver";
//        数据库的连接字符串:连接协议，mysql的ip，端口，数据库名称，时区
        String url = "jdbc:mysql://localhost:3306/db1?&useSSL=false&serverTimezone=UTC";
        //登录用户名
        String username = "root";
        //登录用户密码
        String password = "03037799";

        Class.forName(jdbcDriver);//加载jdbc驱动类
        conn = DriverManager.getConnection(url, username, password); //创建数据库连接
        stmt = conn.createStatement();  //创建statement对象，用于执行sql语句
        return stmt;
    }

    public void close1() throws SQLException {
        stmt.close();
        conn.close();
    }
}
