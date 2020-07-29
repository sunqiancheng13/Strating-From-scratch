package club.banyuan.mbm.test;

import club.banyuan.mbm.entity.Bill;
import club.banyuan.mbm.entity.User;
import club.banyuan.mbm.sqlservice.BillServiceSql;
import club.banyuan.mbm.sqlservice.UserServiceSql;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

public class test {
    static UserServiceSql userService = new UserServiceSql();
    static BillServiceSql billServiceSql = new BillServiceSql();
    public static void main(String[] args) {
//        list();


//
//
//        User user1 = new User();
//        user1.setName("u2");
//        user1.setPwd("1234567");
//        user1.setUserTypeStr("经理");
//        user1.setUserType(1);
//        userService.userAdd(user1);
//        list();

//         userService.userDelete(1);
//         list();
//
//
//        System.out.println("---------删除--------");
//        int sno1 = 101;
//        studentService.deletStudent(sno);
//        list();
//    }
      Bill bill = new Bill();
      bill.setIsPayStr(1);
      bill.setIsPay(1);
      bill.setMoney(1);
      bill.setProduct("1");
      bill.setUpdateTime("2021");
      bill.setProviderId(1);
      bill.setProviderName("1");
      billServiceSql.billAdd(bill);

    }

//    public static void userOne(int id){
//        User user = userService.userOne(id);
//        String s = JSONObject.toJSONString(user);
//        System.out.println(s);
//    }
//
//    public static void list(){
//        System.out.println("------获取列表--------");
//        List<User> users = userService.userAll();
//        String s = JSONObject.toJSONString(users);
//        System.out.println(s);
//    }
}
