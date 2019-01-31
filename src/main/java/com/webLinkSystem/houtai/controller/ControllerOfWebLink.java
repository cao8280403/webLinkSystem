package com.webLinkSystem.houtai.controller;

import com.alibaba.fastjson.JSONObject;
import com.webLinkSystem.houtai.bean.WebLink;
import com.webLinkSystem.houtai.service.ServiceOfVisitHistory;
import com.webLinkSystem.houtai.service.ServiceOfWebLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping(value = "/weblink")
public class ControllerOfWebLink {
    @Autowired
    private ServiceOfWebLink serviceOfWebLink;

    @Autowired
    private ServiceOfVisitHistory serviceOfVisitHistory;

    @ResponseBody
    @RequestMapping(value = "/findAllweblink", method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    private String findAllweblink(@RequestParam Map<String, String> reqMap) {
        String productName =reqMap.get("productName");
        int page = Integer.parseInt(reqMap.get("page"))-1;
        int size = Integer.parseInt(reqMap.get("size"));
        page = page*size;
        productName = "%"+productName+"%";
        List<Object> list = serviceOfWebLink.findAllOrderByCreateTimeDesc(productName,page,size);
        int count = serviceOfWebLink.findCount(productName);
        // 将获取的json数据封装一层，然后在给返回
        JSONObject result = new JSONObject();
        result.put("msg", "success");
        result.put("method", "post");
        result.put("count", count);
        result.put("data", list);
        return result.toJSONString();
    }




    @RequestMapping(value = "/findWeblinkByGiud" , method = RequestMethod.POST)
    private String findWeblinkByGiud(@RequestParam Map<String, String> reqMap) {
        Integer guid = Integer.valueOf(reqMap.get("guid"));
        String flag = "";
        List<WebLink> list = serviceOfWebLink.findByGuid(guid);
        if(list.size()==0){
            flag = "此链接不存在";
        }else{
            Integer state = list.get(0).getState();
            String sourceWebLink = list.get(0).getSourceWebLink();
            if (state==1){
                flag = sourceWebLink;
            }else{
                flag = "此链接已关闭";
            }
        }
        return flag;
    }

    @RequestMapping(value = "/insertWeblink" , method = RequestMethod.POST)
    private String insertWeblink(@RequestParam Map<String, String> reqMap) {
        String productName = reqMap.get("productName");
        String weblink = reqMap.get("weblink");
        String flag = "";
        //首先检查是否有该产品，如果有则返回已有
        List<WebLink> list = serviceOfWebLink.findByProductName(productName);
        if (list.size() == 0) {
            WebLink webLink = new WebLink();
//            webLink.setGuid(UUID.randomUUID().toString());
            webLink.setCreateTime(new Timestamp(System.currentTimeMillis()));
            webLink.setProductName(productName);
            webLink.setState(1);
            webLink.setSourceWebLink(weblink);
            serviceOfWebLink.save(webLink);
            flag = "添加成功";
        } else {
            flag = "该产品已存在";
        }
        return flag;
    }

    //改变状态或者网址
    @RequestMapping(value = "/updateWeblink" , method = RequestMethod.POST)
    private String updateWeblink(@RequestParam Map<String, String> reqMap) {
        Integer guid= Integer.valueOf(reqMap.get("guid"));
        String weblink= reqMap.get("weblink");
        String productName= reqMap.get("productName");
        String flag = "";
        //首先检查是否有该产品，如果有则返回已有
        List<WebLink> list = serviceOfWebLink.findByGuid(guid);
        if(list.size()==1){
            WebLink webLink = list.get(0);
            webLink.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            webLink.setProductName(productName);
            webLink.setSourceWebLink(weblink);
            serviceOfWebLink.save(webLink);
            flag = "修改成功";
        }else {
            flag = "修改失败";
        }
        return flag;
    }

    //改变状态
    @RequestMapping(value = "/switchWeblink" , method = RequestMethod.POST)
    private String switchWeblink(@RequestParam Map<String, String> reqMap) {
        Integer guid= Integer.valueOf(reqMap.get("guid"));
        String flag = "";
        //首先检查是否有该产品，如果有则返回已有
        List<WebLink> list = serviceOfWebLink.findByGuid(guid);
        if(list.size()==1){
            WebLink webLink = list.get(0);
            webLink.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            Integer state = webLink.getState();
            if (state==1){
                webLink.setState(0);
            }else if (state==0){
                webLink.setState(1);
            }
            serviceOfWebLink.save(webLink);
            flag = "状态切换成功";
        }else {
            flag = "状态切换失败";
        }
        return flag;
    }

    //delWeblink
    @RequestMapping(value = "/delWeblink" , method = RequestMethod.POST)
    private String delWeblink(@RequestParam Map<String, String> reqMap) {
        Integer guid= Integer.valueOf(reqMap.get("guid"));
        String flag = "";
        //首先检查是否有该产品，如果有则返回已有
        List<WebLink> list = serviceOfWebLink.findByGuid(guid);
        if(list.size()==1){
            WebLink webLink = list.get(0);
            serviceOfWebLink.delete(webLink);
            flag = "删除成功";
        }else {
            flag = "删除失败";
        }
        return flag;
    }

    @ResponseBody
    @RequestMapping(value = "/findPVUV", method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    private String findPVUV(@RequestParam Map<String, String> reqMap) {
        String begin =reqMap.get("begin");
        String end =reqMap.get("end");
        List<Object> pvuv = serviceOfVisitHistory.findPVUV(begin, end);
        // 将获取的json数据封装一层，然后在给返回
        JSONObject result = new JSONObject();
        result.put("msg", "success");
        result.put("method", "post");
        result.put("data", pvuv);
        return result.toJSONString();
    }
}
