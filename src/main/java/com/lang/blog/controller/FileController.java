package com.lang.blog.controller;

import com.lang.blog.utils.FastDfsUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.csource.common.MyException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/file")
@Api("FileController")
public class FileController {
    @PostMapping("/upload")
    @ApiOperation("文件上传")
    public String upload(MultipartFile file) throws IOException, MyException {
        //拿到文件的字节数组
        byte[] fileBytes = file.getBytes();
        //拿到文件的后缀名
        String filename = file.getOriginalFilename();
        System.out.println(filename);
        String filenameExtension = StringUtils.getFilenameExtension(filename);
        String path = FastDfsUtil.uploadFile(fileBytes, filenameExtension);
        return path;
    }
}
