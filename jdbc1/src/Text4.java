import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Text4 {
    public static void main(String[] args) throws SQLException {
        Connection conn = JdbcUtils.getConnection();
        String name = "Xxx";
        boolean gender = false;
        String birthday = "2000-4-2";

//        String sql = "insert into student(name,gender,birthday) values (?,?,?)";
//        PreparedStatement pstm = conn.prepareStatement(sql);
//        pstm.setString(1,name);
//        pstm.setBoolean(2,gender);
//        pstm.setObject(3,new Date());
//        pstm.executeUpdate();
//        JdbcUtils.closeConnection(conn,pstm);


        Connection connection = JdbcUtils.getConnection();
        String sql1 = "select * from student";
        PreparedStatement pstm1 = connection.prepareStatement(sql1);
        ResultSet resultSet = pstm1.executeQuery();
        List<Student> list = new ArrayList<>();
        while (resultSet.next()){
            Student student = new Student();
            student.setId(resultSet.getInt(1));
            student.setName(resultSet.getString(2));
            student.setGender(resultSet.getBoolean(3));
            student.setBirthday(resultSet.getDate(4));
            list.add(student);
        }
        System.out.println(list);
        JdbcUtils.closeConnection(connection,pstm1,resultSet);
    }
}
