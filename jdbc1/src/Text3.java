import java.sql.*;
import java.util.Date;

public class Text3 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Jdbc jdbc = new Jdbc();
        Statement stmt = jdbc.connection();
        String sql = "select * from student;";
        //执行executeXXX()执行sql
//            如果是查询语句   executeQuery();
//            费查询语句      executeUpdate();
        ResultSet resultSet = stmt.executeQuery(sql);//看成指针（游标），指向查询结果集第一行上面
        //如果要读取结果集第一行数据，需要将resulrset向下移动一行
        //如果要读取第二行，继续向下移动一行，以此类推，知道next返回值为false
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            boolean sex = resultSet.getBoolean(3);
            Date date = resultSet.getDate(4);
            //or Date date = resultSet.getDate("birthday");
            System.out.println(id + "\t" + name + "\t" + sex + "\t" + date);
        }
        resultSet.close();
        jdbc.close1();
    }
}
