package cn.hhuc.controller.mock.video;


import cn.hhuc.service.placemonitor.VideoMonitor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

@RestController
public class MonitorController {

    @Autowired
    public VideoMonitor monitor;

    /**
     * 请求接入监控
     * @param placeName
     * @param request
     * @param response
     */
    @RequestMapping("/getvideo")
    public void getVideo(@RequestParam String placeName, HttpServletRequest request, HttpServletResponse response){
        InputStream inputStream = null;
        try {
            // 通过位置名字获得监控的ip，然后获得输入流
            inputStream = monitor.getInputStream(placeName);
            response.setHeader("Content-Type","video/mp4;charset=utf-8");

            // 将监控画面通过response输出到前端
            IOUtils.copy(inputStream,response.getOutputStream());
            response.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
