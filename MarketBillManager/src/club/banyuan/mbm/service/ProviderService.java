package club.banyuan.mbm.service;

import club.banyuan.mbm.entity.Provider;
import club.banyuan.mbm.exception.BadRequestException;
import club.banyuan.mbm.exception.FormPostException;
import club.banyuan.mbm.uti.PropUtil;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.util.*;

public class ProviderService {
    private static int providerId;
    private static List<Provider> providerList;
    public static final String PROVIDER_STORE_PATH = "provider.store.path";

    static {
        load();
        Optional<Provider> max = providerList.stream().max(Comparator.comparing(Provider::getId));
        providerId = max.map(provider -> provider.getId() + 1).orElse(1);
    }

    private static void load() {
        File file = new File(PropUtil.getProp(PROVIDER_STORE_PATH));
        if (file.exists()) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                byte[] bytes = fileInputStream.readAllBytes();
                providerList = JSONObject.parseArray(new String(bytes), Provider.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("用戶文件不存在");
            providerList = new LinkedList<>();
        }
    }

    public static void save() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(PropUtil.getProp(PROVIDER_STORE_PATH));
            String string = JSONObject.toJSONString(providerList);
            byte[] bytes = string.getBytes();
            fileOutputStream.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public boolean phoneBy(String phone){
        String regex ="[1-9][0-9]{10}";
        boolean matches = phone.matches(regex);
        return  matches;
    }

    public static void deleteProviderId(int id) {
        List<Provider>  list = new ArrayList<>();
        for (Provider provider : providerList) {
            if(provider.getId() == id){
                list.add(provider);
            }
        }
        for (Provider provider : list) {
            providerList.remove(provider);
        }
        save();
    }

    public  void addSupplier(Provider provider) {//添加供应商
        if (isExist(provider)) {
            throw  new FormPostException("供应商已存在");
        } else {
            provider.setId(providerId++);
            if(phoneBy(provider.getPhone())){
            providerList.add(provider);
            }else {
                throw new FormPostException("电话号码输入格式有误！");
            }
            save();
        }
    }

    public  boolean isExist(Provider provider) {//判断是否存在同名的供应商
        for (Provider provider1 : providerList) {
            if (provider.equals(provider1)) {
                return true;
            }
        }
        return false;
    }
    public  List<Provider> getProviderList(Provider provider) {//根据provider对象检索返回链表
        if(provider.getName()==null&&provider.getDesc1().trim().length()==0){
            return getProviderList();
        }
        List<Provider> providerList=new LinkedList<>();
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

    public Provider getProviderById(int id) {
        for (Provider provider : providerList) {
            if(provider.getId() == id){
                return  provider;
            }
        }
        throw new BadRequestException("用户id:" + id + "不存在");
    }


    public void updateProvider(Provider provider) {
        synchronized (providerList) {

            for (Provider provider1 : providerList) {
                if(provider.getId() == provider.getId())
                {
                    if(phoneBy(provider.getPhone())){
                        provider1.setPhone(provider.getPhone());
                        provider1.setName(provider.getName());
                        provider1.setDesc1(provider.getDesc1());
                        provider1.setContactPerson(provider.getContactPerson());
                    }else {
                        throw new FormPostException("电话号码输入格式有误！");
                    }
                    break;
                }
            }

//            Provider providerById = getProviderById(provider.getId());
//            providerById.setContactPerson(provider.getContactPerson());
//            providerById.setDesc(provider.getDesc());
//            providerById.setName(provider.getName());
//            providerById.setPhone(provider.getPhone());
        }
        save();
    }

    public  List<Provider> getProviderList() {
        return providerList;
    }

    public  void setProviderList(List<Provider> providerList) {
        ProviderService.providerList = providerList;
    }

    public  int getProviderId() {
        return providerId;
    }


    public  void setProviderId(int providerId) {
        ProviderService.providerId = providerId;
    }



}