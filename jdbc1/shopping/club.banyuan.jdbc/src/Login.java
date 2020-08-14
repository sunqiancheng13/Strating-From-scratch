import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {
    private static boolean setuser(String username) throws SQLException {
        Connection conn = JdbcUilts.getConnection();
        String sql = "select * from user where loginName = ? ";
        PreparedStatement psmt = conn.prepareStatement(sql);
        psmt.setObject(1,username);
        ResultSet resultSet = psmt.executeQuery();
        if (!resultSet.next()) {
            JdbcUilts.closeConnection(conn,psmt,resultSet);
            return true;
        } else {
            JdbcUilts.closeConnection(conn,psmt,resultSet);
            return false;
        }
    }



    public static int  userName(String username) throws SQLException {
        Connection conn = JdbcUilts.getConnection();
        String sql = "select * from user where loginName = ? ";
        PreparedStatement psmt = conn.prepareStatement(sql);
        psmt.setObject(1,username);
        ResultSet resultSet = psmt.executeQuery();
        resultSet.next();
        int id = resultSet.getInt(1);
        JdbcUilts.closeConnection(conn,psmt,resultSet);
            return id ;

    }


    public static boolean login(String uname, String psd) throws SQLException {
        Connection conn = JdbcUilts.getConnection();
        String sql = "select * from user";
        PreparedStatement psmt = conn.prepareStatement(sql);
        ResultSet resultSet = psmt.executeQuery();
        while (resultSet.next()) {
            String name = resultSet.getString(2);
            String pass = resultSet.getString(4);
            if (name.equals(uname)) {
                if (psd.equals(pass)) {
                    System.out.println("登录成功");
                    JdbcUilts.closeConnection(conn,psmt,resultSet);
                    return true;
                } else {
                    System.out.println("登录失败");
                    JdbcUilts.closeConnection(conn,psmt,resultSet);
                    return false;
                }
            }
        }
        JdbcUilts.closeConnection(conn,psmt,resultSet);
        return false;
    }


    public static void register(String loginName, String uname, String psd, int sex, String identityCode, String
            email, String phone, int type) throws SQLException {
            Connection conn = JdbcUilts.getConnection();
            String sql = "insert into user(loginName,userName,password,sex,identityCode,email,mobile,type) values (?,?,?,?,?,?,?,?)";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setObject(1, loginName);
            pstm.setObject(2, uname);
            pstm.setObject(3, psd);
            pstm.setObject(4, sex);
            pstm.setObject(5, identityCode);
            pstm.setObject(6, email);
            pstm.setObject(7, phone);
            pstm.setObject(8, type);
        if (setuser(loginName)) {
            pstm.executeUpdate();
            JdbcUilts.closeConnection(conn,pstm);
            System.out.println("注册成功");
        } else {
            JdbcUilts.closeConnection(conn,pstm);
            System.out.println("用户名已存在，请重新输入");
        }
    }
}
