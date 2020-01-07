package com.huaducloud.nginxtest.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huanglishun
 * @date 2019/12/12 0012 15:43
 */
@RestController
public class HelloDockerController {
    @Value("${server.port}")
    private String port;

    @RequestMapping("/")
    public String say(){
        return "<!DOCTYPE html>" +
                "<html>" +
                    "<head>" +
                    "<title>花都云(www.huaducloud.cn)</title> " +
                    "<link rel='icon' href='https://www.huaducloud.cn/file/2019-12/aaeac252374140cbb83b9af89ec7495e.ico' type='images/x-ico' />" +
                        "<style>" +
                            "body { background: url('https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=2314097829,2732812264&fm=175&app=25&f=JPEG?w=640&h=400&s=230AB444D4017545643B857A0300001B')no-repeat;height:100%;width:100%;overflow: hidden;background-size:100%; } " +
                        "</style> " +
                    "</head> " +
                    "<body>" +
                        "<p style = 'text-align:center;font-size:30px;font-weight:bold'>花都云(<a href = '/swagger-ui.html'>www.huaducloud.cn</a>)</p>" +
                            "<div style = 'text-align:center;font-size:10px;margin-top: 38%;'>"+
                                "<span >" +
                                "© 2019-2019 &nbsp;" +
                                "<a rel='nofollow' href='http://beian.miit.gov.cn' target='_blank'>蜀ICP备20000139</a>" +
                                "</span>" +
                            "</div>"+
                    "</body>" +
                "</html>";
    }
}
