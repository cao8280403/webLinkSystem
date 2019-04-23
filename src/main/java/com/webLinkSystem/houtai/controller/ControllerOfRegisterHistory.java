package com.webLinkSystem.houtai.controller;

import com.alibaba.fastjson.JSONObject;
import com.webLinkSystem.houtai.bean.RegisterHistory;
import com.webLinkSystem.houtai.bean.WebLink;
import com.webLinkSystem.houtai.service.ServiceOfRegisterHistory;
import com.webLinkSystem.houtai.service.ServiceOfVisitHistory;
import com.webLinkSystem.houtai.service.ServiceOfWebLink;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
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
    @RequestMapping(value = "/daochu", method = RequestMethod.GET)
    private void daochu(@RequestParam String guid,@RequestParam String year,@RequestParam String month,HttpServletResponse response) throws IOException {
        String guidlike = guid + "%";
        List<RegisterHistory> list = serviceOfRegisterHistory.getDaochuList(guidlike, year, month);
        HSSFWorkbook shengchengexcel = shengchengexcel(list);
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + guid+"-"+year+"-"+month+".xls");
        response.flushBuffer();
        shengchengexcel.write(response.getOutputStream());
    }

    //生成excel表格
    public HSSFWorkbook shengchengexcel(List<RegisterHistory> list) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("登记列表");
        String fileName = "registerHistory" + ".xls";//设置要导出的文件的名字
        //新增数据行，并且设置单元格数据
        int rowNum = 1;
        String[] headers = {"id", "登记时间", "姓名", "手机号码", "产品id", "ip"};
        //headers表示excel表中第一行的表头
        HSSFRow row = sheet.createRow(0);
        //在excel表中添加表头
        for (int i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        if (list.size() > 0) {
            //在表中存放查询到的数据放入对应的列
            for (RegisterHistory registerHistory : list) {
                HSSFRow row1 = sheet.createRow(rowNum);
                row1.createCell(0).setCellValue(registerHistory.getGuid());
                row1.createCell(1).setCellValue(registerHistory.getCreatetime());
                row1.createCell(2).setCellValue(registerHistory.getName());
                row1.createCell(3).setCellValue(registerHistory.getTelephone());
                row1.createCell(4).setCellValue(registerHistory.getProductName());
                row1.createCell(5).setCellValue(registerHistory.getIp());
                rowNum++;
            }
        }
        return workbook;
    }

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
