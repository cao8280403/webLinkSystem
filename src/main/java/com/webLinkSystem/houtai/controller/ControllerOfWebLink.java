package com.webLinkSystem.houtai.controller;

import com.webLinkSystem.houtai.bean.WebLink;
import com.webLinkSystem.houtai.service.ServiceOfWebLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/findAllweblink")
    private List<WebLink> findAllweblink() {
        List<WebLink> list = serviceOfWebLink.findAllOrderByCreateTimeDesc();
        return list;
    }
    @RequestMapping(value = "/findWeblinkByGiud" , method = RequestMethod.POST)
    private String findWeblinkByGiud(@RequestParam Map<String, String> reqMap) {
        String guid = reqMap.get("guid");
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
        String prudoctName = reqMap.get("prudoctName");
        String weblink = reqMap.get("weblink");
        String flag = "";
        //首先检查是否有该产品，如果有则返回已有
        List<WebLink> list = serviceOfWebLink.findByProductName(prudoctName);
        if (list.size() == 0) {
            WebLink webLink = new WebLink();
            webLink.setGuid(UUID.randomUUID().toString());
            webLink.setCreateTime(new Timestamp(System.currentTimeMillis()));
            webLink.setProductName(prudoctName);
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
        String guid= reqMap.get("guid");
        String weblink= reqMap.get("weblink");
        String state= reqMap.get("state");
        String flag = "";
        //首先检查是否有该产品，如果有则返回已有
        List<WebLink> list = serviceOfWebLink.findByGuid(guid);
        if(list.size()==1){
            WebLink webLink = list.get(0);
            webLink.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            webLink.setState(Integer.valueOf(state));
            webLink.setSourceWebLink(weblink);
            serviceOfWebLink.save(webLink);
            flag = "修改成功";
        }else {
            flag = "修改失败";
        }
        return flag;
    }

}
