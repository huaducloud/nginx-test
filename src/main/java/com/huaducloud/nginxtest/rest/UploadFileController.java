package com.huaducloud.nginxtest.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author huanglishun
 * @date 2019-12-31 14:43
 */
@RestController
@RequestMapping("/api/uploadFile")
@Api(tags = "文件上传接口")
public class UploadFileController {

    @Value("${image.path}")
    private String imagePath;

    @Value("${image.size}")
    private Integer size;

    @ApiOperation("本地文件上传")
    @PostMapping(value = "/upload",consumes = "multipart/*",headers = "content-type=multipart/form-date")
    public Map<String, String> uploadImage(@RequestParam(value = "file") @ApiParam(value = "请选择文件",required = true) MultipartFile file) {
        long fileSize = file.getSize();
        if (file.isEmpty()) {
            throw new IllegalArgumentException("文件为空");
        } else if (fileSize > (size * 1024 * 1024)) {
            throw new IllegalArgumentException("文件太大，请上传小于"+size+"MB的图片");
        } else {
            //获取原始文件名
            String filename = file.getOriginalFilename();
            //保存路径
            String savePath = null;
            //每月生成文件夹
            String month = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM"));

            //重新生成文件名
            String uuid = UUID.randomUUID().toString().replace("-", "");
            //获取扩展名
            String prefix = filename.substring(filename.lastIndexOf(".") + 1);
            //文件名
            String newFileName = uuid + "." + prefix;
            String returnPath = month+"/"+ newFileName;
            //文件路径
            savePath = imagePath +"/"+month+"/"+ newFileName;
            // 上传文件
            File dest = new File(savePath);
            // 检测是否存在目录
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            Map<String, String> map = new HashMap<>(1);
            try {
                //写入服务器
                file.transferTo(dest);
                map.put("url",returnPath);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return map;
        }
    }

    @ApiOperation("七牛云文件上传")
    @PostMapping(value = "/upload-to-qiniuCloud",consumes = "multipart/*",headers = "content-type=multipart/form-date")
    @ResponseStatus(HttpStatus.CREATED)
    public void qiNiuCloudUpload(){

    }
}
