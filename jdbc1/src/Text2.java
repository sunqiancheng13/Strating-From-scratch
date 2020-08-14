import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Text2 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
//        jdbc driver class name
        String jdbcDriver = "com.mysql.cj.jdbc.Driver";
//        数据库的连接字符串:连接协议，mysql的ip，端口，数据库名称，时区
        String url = "jdbc:mysql://localhost:3306/db1?&useSSL=false&serverTimezone=UTC";
        //登录用户名
        String username = "root";
        //登录用户密码
        String password = "03037799";

        Class.forName(jdbcDriver);//加载jdbc驱动类
        Connection conn = DriverManager.getConnection(url, username, password); //创建数据库连接
        Statement stmt = conn.createStatement();  //创建statement对象，用于执行sql语句

        String sql = "INSERT into student(name,gender,birthday) VALUES('bbb',true,'2003-3-2');";

        //执行executeXXX()执行sql
//            如果是查询语句   executeQuery();
//            费查询语句      executeUpdate();
        int count = stmt.executeUpdate(sql);
        System.out.println(count);


        stmt.close();
        conn.close();
    }
}
