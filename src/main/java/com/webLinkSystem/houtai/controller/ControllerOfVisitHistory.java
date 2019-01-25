package com.webLinkSystem.houtai.controller;

import com.webLinkSystem.houtai.bean.VisitHistory;
import com.webLinkSystem.houtai.service.ServiceOfVisitHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping(value = "/visitHistory")
public class ControllerOfVisitHistory {
    @Autowired
    private ServiceOfVisitHistory serviceOfVisitHistory;

//    @RequestMapping(value = "/findAllVisitHistory", method = RequestMethod.POST)
//    private List<VisitHistory> findAllVisitHistory(@RequestParam Map<String, String> reqMap) {
//        String productName = reqMap.get("productName");
//        productName = "%" + productName + "%";
//        List<VisitHistory> list = serviceOfVisitHistory.findAllOrderByCreateTimeDesc(productName);
//        return list;
//    }


    @RequestMapping(value = "/findVisitHistoryByGiud", method = RequestMethod.POST)
    private String findVisitHistoryByGiud(@RequestParam Map<String, String> reqMap) {
        Integer guid = Integer.valueOf(reqMap.get("guid"));
        String flag = "";
//        List<VisitHistory> list = serviceOfVisitHistory.findByGuid(guid);
//        if(list.size()==0){
//            flag = "此链接不存在";
//        }else{
//            Integer state = list.get(0).getState();
//            String sourceVisitHistory = list.get(0).getSourceVisitHistory();
//            if (state==1){
//                flag = sourceVisitHistory;
//            }else{
//                flag = "此链接已关闭";
//            }
//        }
        return flag;
    }

    @RequestMapping(value = "/insertVisitHistory", method = RequestMethod.POST)
    private String insertVisitHistory(@RequestParam Map<String, String> reqMap) {
        int productId = Integer.parseInt(reqMap.get("productId"));
        String requestId = reqMap.get("requestId");
        String requestIp = reqMap.get("requestIp");
        String requestLocation = reqMap.get("requestLocation");
        String requestMode = reqMap.get("requestMode");
        String requestUrl = reqMap.get("requestUrl");
        String requestUserAgent = reqMap.get("requestUserAgent");
        String flag = "";
        VisitHistory visitHistory = new VisitHistory();
        visitHistory.setGuid(UUID.randomUUID().toString());
        visitHistory.setCreateTime(new Timestamp(System.currentTimeMillis()));
        visitHistory.setProductId(productId);
        visitHistory.setRequestId(requestId);
        visitHistory.setRequestIp(requestIp);
        visitHistory.setRequestLocation(requestLocation);
        visitHistory.setRequestMode(requestMode);
        visitHistory.setRequestUrl(requestUrl);
        visitHistory.setRequestUseragent(requestUserAgent);
        serviceOfVisitHistory.save(visitHistory);
        flag = "添加成功";
        return flag;
    }

}
