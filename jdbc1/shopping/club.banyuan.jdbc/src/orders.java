import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class orders {

    public static  void order(String name,int pid,int nub) throws SQLException {
        int uid = Login.userName(name);
        Product1 productid = Product.productid(pid);
        float price = productid.getPrice();
        Connection con = JdbcUilts.getConnection();
        String sql = "insert into order_detail(orderId,productId,quantity,cost) values (?,?,?,?)";
        PreparedStatement pstm = con.prepareStatement(sql);
        pstm.setObject(1,uid);
        pstm.setObject(2,pid);
        pstm.setObject(3,nub);
        pstm.setObject(4,price*nub);
        pstm.executeUpdate();
        JdbcUilts.closeConnection(con,pstm);
    }

    public static void orderByUname(String uname) throws SQLException {
            int id = Login.userName(uname);
            Connection conn = JdbcUilts.getConnection();
            String sql = "select * from order_detail where orderId = ? ";
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setObject(1,id);
            ResultSet resultSet = psmt.executeQuery();
            List<Order1> list = new ArrayList<>();
            while (resultSet.next()){
                Order1 o = new Order1();
                o.setId(resultSet.getInt(1));
                o.setOrderid(resultSet.getInt(2));
                o.setProductid(resultSet.getInt(3));
                o.setQuantity(resultSet.getInt(4));
                o.setCost(resultSet.getFloat(5));
                list.add(o);
            }
        for (Order1 order1 : list) {
            System.out.println(order1);
        }
    }
}
