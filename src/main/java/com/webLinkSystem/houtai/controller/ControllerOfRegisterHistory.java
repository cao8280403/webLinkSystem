package com.webLinkSystem.houtai.controller;

import com.alibaba.fastjson.JSONObject;
import com.webLinkSystem.houtai.bean.RegisterHistory;
import com.webLinkSystem.houtai.bean.WebLink;
import com.webLinkSystem.houtai.service.ServiceOfRegisterHistory;
import com.webLinkSystem.houtai.service.ServiceOfVisitHistory;
import com.webLinkSystem.houtai.service.ServiceOfWebLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping(value = "/registerHistory")
public class ControllerOfRegisterHistory {
    @Autowired
    private ServiceOfWebLink serviceOfWebLink;

    @Autowired
    private ServiceOfRegisterHistory serviceOfRegisterHistory;

//    @ResponseBody
//    @RequestMapping(value = "/findAll", method = RequestMethod.POST,produces="application/json;charset=UTF-8")
//    private String findAllweblink(@RequestParam Map<String, String> reqMap) {
//        String productName =reqMap.get("productName");
//        int page = Integer.parseInt(reqMap.get("page"))-1;
//        int size = Integer.parseInt(reqMap.get("size"));
//        page = page*size;
//        productName = "%"+productName+"%";
//        List<Object> list = serviceOfWebLink.findAllOrderByCreateTimeDesc(productName,page,size);
//        int count = serviceOfWebLink.findCount(productName);
//        // 将获取的json数据封装一层，然后在给返回
//        JSONObject result = new JSONObject();
//        result.put("msg", "success");
//        result.put("method", "post");
//        result.put("count", count);
//        result.put("data", list);
//        return result.toJSONString();
//    }
//
//
//
//
//    @RequestMapping(value = "/findWeblinkByGiud" , method = RequestMethod.POST)
//    private String findWeblinkByGiud(@RequestParam Map<String, String> reqMap) {
//        Integer guid = Integer.valueOf(reqMap.get("guid"));
//        String flag = "";
//        List<WebLink> list = serviceOfWebLink.findByGuid(guid);
//        if(list.size()==0){
//            flag = "此链接不存在";
//        }else{
//            Integer state = list.get(0).getState();
//            String sourceWebLink = list.get(0).getSourceWebLink();
//            if (state==1){
//                flag = sourceWebLink;
//            }else{
//                flag = "此链接已关闭";
//            }
//        }
//        return flag;
//    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    private String insertWeblink(@RequestParam Map<String, String> reqMap) {
        String productName = reqMap.get("productId");
        String name = reqMap.get("name");
        String telephone = reqMap.get("telephone");
        String ip = reqMap.get("ip");
        String flag = "";
        //首先检查是否有该产品，如果有则返回已有
        List<RegisterHistory> list = serviceOfRegisterHistory.findByProductNameAndIp(productName, ip);
        if (list.size() == 0 || list.size() == 1) {
            RegisterHistory registerHistory = new RegisterHistory();
            registerHistory.setGuid(UUID.randomUUID().toString());
            registerHistory.setCreatetime(new Timestamp(System.currentTimeMillis()));
            registerHistory.setProductName(productName);
            registerHistory.setTelephone(telephone);
            registerHistory.setIp(ip);
            registerHistory.setName(name);
            serviceOfRegisterHistory.save(registerHistory);
            flag = "yes";
        } else {
            flag = "no";
        }
        return flag;
    }
}
