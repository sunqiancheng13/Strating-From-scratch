import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Product {
    public  static void  productName(String name) throws SQLException {
        Connection conn = JdbcUilts.getConnection();
        String sql = "select * from product where name like ? ";
        PreparedStatement psmt = conn.prepareStatement(sql);
        name = "%"+name+"%";
        psmt.setObject(1,name);
        ResultSet resultSet = psmt.executeQuery();
        List<Product1> list = new ArrayList<>();
        while (resultSet.next()){
            Product1 product1 = new Product1();
            product1.setId(resultSet.getInt(1));
            product1.setName(resultSet.getString(2));
            product1.setDescription(resultSet.getString(3));
            product1.setPrice(resultSet.getFloat(4));
            product1.setStock(resultSet.getInt(5));
            product1.setCategoryLevel1Id(resultSet.getInt(6));
            product1.setCategoryLevel2Id(resultSet.getInt(7));
            product1.setCategoryLevel3Id(resultSet.getInt(8));
            product1.setFileName(resultSet.getString(9));
            product1.setIsDelete(resultSet.getInt(10));
            list.add(product1);
        }
        for (Product1 product1 : list) {
            System.out.println(product1);
        }


    }
    public  static Product1  productid(int id) throws SQLException {
        Connection conn = JdbcUilts.getConnection();
        String sql = "select * from product where id = ? ";
        PreparedStatement psmt = conn.prepareStatement(sql);
        psmt.setObject(1,id);
        ResultSet resultSet = psmt.executeQuery();
        if(resultSet.next()){
            Product1 product1 = new Product1();
            product1.setId(resultSet.getInt(1));
            product1.setName(resultSet.getString(2));
            product1.setDescription(resultSet.getString(3));
            product1.setPrice(resultSet.getFloat(4));
            product1.setStock(resultSet.getInt(5));
            product1.setCategoryLevel1Id(resultSet.getInt(6));
            product1.setCategoryLevel2Id(resultSet.getInt(7));
            product1.setCategoryLevel3Id(resultSet.getInt(8));
            product1.setFileName(resultSet.getString(9));
            product1.setIsDelete(resultSet.getInt(10));
            System.out.println(product1);
            return product1;
        }else {
            System.out.println("商品不存在");
        }
        return  null;
    }
}
