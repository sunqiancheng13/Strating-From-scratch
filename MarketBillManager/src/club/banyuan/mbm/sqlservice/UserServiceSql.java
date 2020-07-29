package club.banyuan.mbm.sqlservice;

import club.banyuan.mbm.entity.User;
import club.banyuan.mbm.uti.JdbcUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserServiceSql {
   private static UserServiceSql userService = new UserServiceSql();
//    //遍历表单
//    public static void list(){
//        System.out.println("------获取列表--------");
//        List<User> users = userService.userAll();
//        String s = JSONObject.toJSONString(users);
//        System.out.println(s);
//    }

    //判断密码是否相同 返回值是否为null
    public User login(String name, String pwd){
        List<User> users = userService.userAll();
        for (User user : users) {
            if(user.getName().equals(name)&&user.getPwd().equals(pwd)){
                return user;
            }
        }
        return null;
    }

    public List<User> getUserList(){
        List<User> users = userService.userAll();
        return users;
    }

    public List<User> getUserList(User user){
        if (user.getName() == null || user.getName().trim().length() == 0) {
            return getUserList();
        }
        List<User> list = new ArrayList<>();
        List<User> users = userService.userAll();
        for (User user1 : users) {
            if (user1.getName().contains(user.getName().trim())) {
                list.add(user1);
            }
        }
        return list;
    }

    //模糊查询
    public List<User> UserLike(User user){
        if (user.getName() == null || user.getName().trim().length() == 0) {
            return getUserList();
        }
        String str = "select id,name,pwd,userType,userTypeStr from user where name like ?";
        String trim = "%"+user.getName().trim()+"%";
        List<Map<String, Object>> list = JdbcUtil.queryAll(str, trim);
        List<User> users = JSONObject.parseArray(JSONObject.toJSONString(list),User.class);
        return users;
    }

    //按照ID单个查询表单信息
    public User userOne(int id){
        String sql = "select id,name,pwd,userType,userTypeStr from user where id = ?";
        Map<String, Object> map = JdbcUtil.queryQne(sql, id);
        User user = JSONObject.parseObject(JSONObject.toJSONString(map),User.class);
        return user;
    }

   //查询所有表单信息
    public List<User> userAll(){
        String sql = "select id,name,pwd,userType,userTypeStr  from user ";
        List<Map<String, Object>> list = JdbcUtil.queryAll(sql);
        List<User> user = JSONObject.parseArray(JSONObject.toJSONString(list),User.class);
        return user;
    }

    //修改单个表单信息
    public  void userUpdate(User user){
        User user1 = userOne(user.getId());
        if(user1 != null){
            user1.setId(user.getId());
            user1.setName(user.getName());
            user1.setPwd(user.getPwd());
            user1.setUserType(user.getUserType());
            user1.setUserTypeStr(user.getUserTypeStr());
            String sql = "update user set name =?,pwd = ?,userType=?,userTypeStr =? where id = ?";
            JdbcUtil.update(sql,user1.getName(),user1.getPwd(),user1.getUserType(),user1.getUserTypeStr(),user1.getId());
        }
    }

    //添加一条表单信息
    public void userAdd(User user){
        String sql = "insert into user(name,pwd,userType,userTypeStr) values (?,?,?,?)";
        JdbcUtil.update(sql,user.getName(),user.getPwd(),user.getUserType(),user.getUserTypeStr());
    }

    //删除表单信息
    public void userDelete(int id){
        String sql = "delete from user where id = ?";
        JdbcUtil.update(sql,id);
    }
}
