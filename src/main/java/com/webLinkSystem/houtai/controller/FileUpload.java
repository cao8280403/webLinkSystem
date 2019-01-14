package com.webLinkSystem.houtai.controller;

import com.webLinkSystem.houtai.bean.UploadProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController
@CrossOrigin
public class FileUpload {

    @Autowired
    private UploadProperties uploadProperties;

    @RequestMapping(value = "/upload", method = RequestMethod.POST,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)

    public String fileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        //先获取文件的后缀名
        String contentType = file.getContentType();
        String[] split = contentType.split("/");
        String currentTimeMillis = System.currentTimeMillis()+"."+split[1];
        System.out.println(uploadProperties.getImgUrl() + currentTimeMillis);
        File convertFile = new File(uploadProperties.getImgUrl() + currentTimeMillis);
        convertFile.createNewFile();
        FileOutputStream fout = new FileOutputStream(convertFile);
        fout.write(file.getBytes());
        fout.close();
        return currentTimeMillis;
    }
}