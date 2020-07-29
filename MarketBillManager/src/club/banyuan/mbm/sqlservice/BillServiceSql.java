package club.banyuan.mbm.sqlservice;

import club.banyuan.mbm.entity.Bill;
import club.banyuan.mbm.entity.Provider;
import club.banyuan.mbm.entity.User;
import club.banyuan.mbm.uti.JdbcUtil;
import com.alibaba.fastjson.JSONObject;

import java.text.SimpleDateFormat;
import java.util.*;


public class BillServiceSql {
    List<Bill> billList = billAll();
    ProviderServiceSql providerServiceSql = new ProviderServiceSql();

    public List<Bill> getBillList() {
        return billList;
    }

    public List<Bill> getBillList(Bill bill) {
        if (bill.getProduct().equals("") && bill.getIsPay() == -1) {
            return billList;
        }
        List<Bill> billList1 = new ArrayList<>();
        for (Bill bill1 : billList) {
            if (bill.getIsPay() == bill1.getIsPay() && bill1.getProduct().contains(bill.getProduct())) {
                billList1.add(bill1);
            }
            if (bill.getIsPay() == -1) {
                if (bill1.getProduct().contains(bill.getProduct())) {
                    billList1.add(bill1);
                }
            }
            if (bill.getProduct().equals("") && bill.getIsPay() != -1) {
                System.out.println("asd");
                if (bill1.getIsPay() == bill.getIsPay()) {
                    billList1.add(bill1);
                }
            }
        }
        return billList1;
    }


    //模糊查询
    public List<Bill> billLike(Bill bill) {

        if (bill.getProduct().equals("") && bill.getIsPay() == -1) {
            return getBillList();
        }
        List<Bill> bill1 = new LinkedList<>();
        String sql = "select id,providerId,product,money,isPay,isPayStr,providerName,updateTime from bill where product like ? and isPay like ?";
        String s = "%" + bill.getProduct().trim() + "%";
        String s1;
        if (bill.getIsPay() == -1) {
            s1 = "%";
        } else {
            s1 = "%" + bill.getIsPay() + "%";
        }
        List<Map<String, Object>> list = JdbcUtil.queryAll(sql, s, s1);
        bill1 = JSONObject.parseArray(JSONObject.toJSONString(list), Bill.class);
        return bill1;

    }

    //按照ID单个查询表单信息
    public Bill billOne(int id) {
        String sql = "select providerId,product,money,isPay,isPayStr,providerName,updateTime from bill where id = ？";
        Map<String, Object> map = JdbcUtil.queryQne(sql, id);
        Bill bill = JSONObject.parseObject(JSONObject.toJSONString(map), Bill.class);
        return bill;
    }

    //查询所有表单信息
    public List<Bill> billAll() {
        String sql = "select id,providerId,product,money,isPay,isPayStr,providerName,updateTime from bill ";
        List<Map<String, Object>> list = JdbcUtil.queryAll(sql);
        List<Bill> bill = JSONObject.parseArray(JSONObject.toJSONString(list), Bill.class);
        return bill;
    }

    //修改单个表单信息
    public void billUpdate(Bill bill) {
        Bill bill1 = billOne(bill.getId());
        if (bill1 != null) {
            bill1.setId(bill.getId());
            bill1.setIsPay(bill.getIsPay());
            bill1.setProviderId(bill.getProviderId());

            Provider provider = providerServiceSql.providerOne(bill.getProviderId());
            bill1.setProviderName(provider.getName());

            bill1.setProduct(bill.getProduct());

            bill1.setUpdateTime(forMate());

            bill1.setMoney(bill.getMoney());
            bill1.setIsPayStr(setIsPayStr(bill.getIsPay()));
            String sql = "update bill set providerId =?,product =?,money=?,isPay =? ,isPayStr=?,providerName=?,updateTime= ? where id = ?";
            JdbcUtil.update(sql, bill1.getProviderId(), bill1.getProduct(), bill1.getMoney(), bill1.getIsPay(), bill1.getIsPayStr(), bill1.getProviderName(), bill1.getUpdateTime(), bill1.getId());
        }
    }

    //添加一条表单信息
    public void billAdd(Bill bill) {
        bill.setIsPayStr(setIsPayStr(bill.getIsPay()));
        bill.setUpdateTime(forMate());
        Provider provider = providerServiceSql.providerOne(bill.getProviderId());
        bill.setProviderName(provider.getName());
        String sql = "insert into bill(providerId,product,money,isPay,updateTime,isPayStr,providerName) values (?,?,?,?,?,?,?)";
        JdbcUtil.update(sql, bill.getProviderId(), bill.getProduct(), bill.getMoney(), bill.getIsPay(), bill.getUpdateTime(), bill.getIsPayStr(), bill.getProviderName());
    }

    //是否付款
    public String setIsPayStr(int ispay) {
        if (ispay == 0) {
            return "未付款";
        } else {
            return "已付款";
        }
    }

    //格式化时间
    public String forMate() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = dateFormat.format(date);
        return format;
    }

    //删除表单信息
    public void billDelete(int id) {
        String sql = "delete from bill where id = ?";
        JdbcUtil.update(sql, id);
    }
}
