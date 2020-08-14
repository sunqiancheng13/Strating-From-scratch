import java.sql.SQLException;
import java.util.Scanner;

public class main {
    static Scanner  scanner = new Scanner(System.in);
    public static void main(String[] args) throws SQLException {

        while (true) {
            System.out.println("___________________________");
            System.out.println("1.登录");
            System.out.println("2.注册");
            System.out.println("3.商品查询");
            System.out.println("4.加入购物车");
            System.out.println("5.查看购物车");
            System.out.println("___________________________");
            System.out.println("请选择功能：");
            int x = scanner.nextInt();
            switch (x) {
                case 1:login();break;
                case 2:register();break;
                case 3:product();break;
                case 4:order();break;
                case 5:orderByname();break;
            }
        }


    }

    public static  void login() throws SQLException {
        System.out.println("请输入用户名");
        String name = scanner.next();
        System.out.println("请输入密码");
        String psd = scanner.next();
        Login.login(name, psd);
    }

    public static void register() throws SQLException {
        Login.register("sqc", "孙前程", "123456", 1, "320682199903037799", "1070474284@qq.com", "13962722723", 0);
    }

    public static void product() throws SQLException {
        System.out.println("请输入想要查询的关键字：");
        String s = scanner.next();
        Product.productName(s);
        Product.productid(764);
    }

    public static void order() throws SQLException {
        System.out.print("请输入想要加入购物车的商品id：");
        int i = scanner.nextInt();
        System.out.print("请输入商品数量：");
        int i1 = scanner.nextInt();
        orders.order("sqc",i,i1);
    }
    public static  void orderByname() throws SQLException {
        orders.orderByUname("sqc");
    }
}
