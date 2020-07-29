package club.banyuan.mbm.service;

import club.banyuan.mbm.entity.Bill;
import club.banyuan.mbm.entity.Provider;
import club.banyuan.mbm.exception.FormPostException;
import club.banyuan.mbm.uti.PropUtil;
import com.alibaba.fastjson.JSONObject;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class BillService {
    private static int billId;
    private static List<Bill> billList;
    public static final String BILL_STORE_PATH = "bill.store.path";
    ProviderService providerService = new ProviderService();

    static {
        load();
        Optional<Bill> max = billList.stream().max(Comparator.comparing(Bill::getId));
        billId = max.map(provider -> provider.getId() + 1).orElse(1);
    }




    public String providerName(int providrtId){
        List<Provider> provider =  new ArrayList<>();
        for (Provider provider1 : provider) {
            if(providrtId == provider1.getId()){
                return provider1.getName();
            }
        }
        return  null;
    }

    private static void load() {
        File file = new File(PropUtil.getProp(BILL_STORE_PATH));
        if (file.exists()) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                byte[] bytes = fileInputStream.readAllBytes();
                billList = JSONObject.parseArray(new String(bytes), Bill.class);
                if(billList == null){
                    billList = new LinkedList<>();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("用戶文件不存在");
            billList = new LinkedList<>();
        }
    }


    public static void save() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(PropUtil.getProp(BILL_STORE_PATH));
            String string = JSONObject.toJSONString(billList);
            byte[] bytes = string.getBytes();
            fileOutputStream.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public  boolean isExist(Bill bill) {//判断是否存在同名的供应商
        for (Bill bill1 : billList) {
            if(bill1.getProduct().equals(bill.getProduct())){
                return true;
            }
        }
        return false;
    }


    public static int getBillId() {
        return billId;
    }

    public static void setBillId(int billId) {
        BillService.billId = billId;
    }

    public static List<Bill> getBillList() {
        return billList;
    }
    public static void setBillList(List<Bill> billList) {
        BillService.billList = billList;
    }


    public  List<Bill> getBillList(Bill bill){
        if(bill.getProduct().equals("")&&bill.getIsPay()==-1){
            return  billList;
        }
        List<Bill> billList1 = new ArrayList<>();
        for (Bill bill1 : billList) {
            if(bill.getIsPay()==bill1.getIsPay()&&bill1.getProduct().contains(bill.getProduct())){
                billList1.add(bill1);
            }
            if(bill.getIsPay()==-1){
                if(bill1.getProduct().contains(bill.getProduct())){
                    billList1.add(bill1);
                }
            }
            if(bill.getProduct().equals("")&&bill.getIsPay()!=-1){
                System.out.println("asd");
                if(bill1.getIsPay()==bill.getIsPay()){
                    billList1.add(bill1);
                }
            }
        }
        return  billList1;
    }

    //添加账单
    public void addSupplier(Bill bill) {
        if(isExist(bill)){
            throw new FormPostException("账单已存在");
        }else {
            bill.setId(billId++);
            billList.add(bill);
            bill.setIsPayStr(bill.getIsPay());
            Provider provider = providerService.getProviderById(bill.getProviderId());
            bill.setProviderName(provider.getName());
            bill.setUpdateTime(forMate());
        }
        save();
    }
//格式化时间
    public String forMate(){
      Date date = new Date();
      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = dateFormat.format(date);
        return format;
    }
//修改账单
    public void updateBill(Bill bill) {
        for (Bill bill1 : billList) {
            if(bill1.getId() == bill.getId()){

                bill1.setIsPay(bill.getIsPay());
                bill1.setIsPayStr(bill1.getIsPay());

                bill1.setMoney(bill.getMoney());

                bill1.setProduct(bill.getProduct());

                bill1.setUpdateTime(forMate());

                bill1.setProviderId(bill.getProviderId());
                Provider provider = providerService.getProviderById(bill.getProviderId());
                bill1.setProviderName(provider.getName());
                break;
            }
        }
        save();
    }

    public void delete(int id) {
        List<Bill> billList1 = new ArrayList<>();
        for (Bill bill : billList) {
            if(bill.getId() == id) {
                billList1.add(bill);
                break;
            }
        }
        for (Bill bill : billList1) {
            billList.remove(bill);
        }
        save();
    }

    public Bill billByid(int id) {
        List<Bill> billList1 = new ArrayList<>();
        for (Bill bill : billList) {
            if(bill.getId() == id) {
               return  bill;
            }

        }
        return null;
    }
}


