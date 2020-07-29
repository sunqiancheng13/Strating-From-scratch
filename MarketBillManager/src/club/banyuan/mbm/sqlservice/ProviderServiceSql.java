package club.banyuan.mbm.sqlservice;

import club.banyuan.mbm.entity.Provider;
import club.banyuan.mbm.uti.JdbcUtil;
import com.alibaba.fastjson.JSONObject;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ProviderServiceSql {

    public List<Provider> getProviderList(){
        List<Provider> providers = providersAll();
        return  providers;
    }

    public  List<Provider> getProviderList(Provider provider) {//根据provider对象检索返回链表
        if(provider.getName()==null&&provider.getDesc1().trim().length()==0){
            return getProviderList();
        }
        List<Provider> providerList  = new LinkedList<>();
        for (Provider pro : getProviderList()) {
            if(pro.getName().contains(provider.getName())&&pro.getDesc1().contains(provider.getDesc1())){
                providerList.add(pro);
            }else if(provider.getName()==null&&pro.getDesc1().contains(provider.getDesc1())){
                providerList.add(pro);
            }else if(provider.getDesc1()==null&&pro.getName().contains(provider.getName())){
                providerList.add(pro);
            }
        }
        return providerList;
    }

    //模糊查询
    public List<Provider> providerLike(Provider provider){
        if(provider.getName()==""&&provider.getDesc1()==null){
            return getProviderList();
        }
        List<Provider> providerList  = new LinkedList<>();
        String sql = "select id,name,desc1,contactPerson,phone from provider where name like ? and desc1 like ?";
        String s = "%" + provider.getName().trim() + "%";
        String s1 = "%" + provider.getDesc1() + "%";
        List<Map<String, Object>> list = JdbcUtil.queryAll(sql, s, s1);
        providerList = JSONObject.parseArray(JSONObject.toJSONString(list),Provider.class);
        return  providerList;
    }



    //按照ID单个查询表单信息
    public Provider providerOne(int id){
        String sql = "select id,name,desc1,contactPerson,phone from provider where id = ?";
        Map<String, Object> map = JdbcUtil.queryQne(sql, id);
        Provider provider = JSONObject.parseObject(JSONObject.toJSONString(map),Provider.class);
        return provider;
    }




    //查询所有表单信息
    public List<Provider> providersAll(){
        String sql = "select id,name,desc1,contactPerson,phone from provider ";
        List<Map<String, Object>> list = JdbcUtil.queryAll(sql);
        List<Provider> providerList = JSONObject.parseArray(JSONObject.toJSONString(list),Provider.class);
        return providerList;
    }

    //修改单个表单信息
    public  void providerUpdate(Provider provider){
        Provider provider1 = providerOne(provider.getId());
        if(provider1 != null){
            provider1.setId(provider.getId());
            provider1.setName(provider.getName());
            provider1.setContactPerson(provider.getContactPerson());
            provider1.setDesc1(provider.getDesc1());
            provider1.setPhone(provider.getPhone());
            String sql = "update provider set name =?,desc1 = ?,contactPerson=?,phone=? where id = ?";
            JdbcUtil.update(sql,provider1.getName(),provider1.getDesc1(),provider1.getContactPerson(),provider1.getPhone(),provider1.getId());
        }
    }

    //添加一条表单信息
    public void providerAdd(Provider provider){
        String sql = "insert into provider(name,desc1,contactPerson,phone) values (?,?,?,?)";
        JdbcUtil.update(sql,provider.getName(),provider.getDesc1(),provider.getContactPerson(),provider.getPhone());
    }

    //删除表单信息
    public void providerDelete(int id){
        String sql = "delete from provider where id = ?";
        JdbcUtil.update(sql,id);
    }
}
