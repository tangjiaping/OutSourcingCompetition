package cn.hhuc.controller;

import cn.hhuc.bean.StayVolume;
import cn.hhuc.mapper.IStayVolume;
import cn.hhuc.mapper.MonitorMapper;
import cn.hhuc.service.placemonitor.VideoMonitor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.util.List;
import java.util.Objects;

@RestController
public class Test {
    @Autowired
    private IStayVolume StayVolume;

    @RequestMapping("/StayVolume")
    public List<StayVolume> testStayVolume(){
        System.out.println("请求StayVolume");
        return StayVolume.getAreaStayVolumes();
    }

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @RequestMapping("/sendUser")
    public void sendUser(String token) {
        System.out.println(token);
        simpMessagingTemplate.convertAndSendToUser(token, "/queue/sendUser", "您好");


    }
    @RequestMapping("/sendTopic")
    public void sendTopic() {
        simpMessagingTemplate.convertAndSend("/topic/sendTopic", "888");
    }

    @MessageMapping("/sendServer")
    public void sendServer(String message) {
        System.out.println(message);
    }

    @MessageMapping("/sendAllUser")
    @SendTo("/topic/sendTopic")
    public String sendAllUser(String message) {
        // 也可以采用template方式
        return "定时任务";
    }

    @Autowired
    public VideoMonitor monitor;
    //@RequestMapping("/getvideo")
    public void getVideo(HttpServletRequest request, HttpServletResponse response){
        try {
//            URL url = new URL("http://localhost:8080/video/viedo-03.mp4");
//            URLConnection connection = url.openConnection();
//            InputStream inputStream = connection.getInputStream();
            InputStream inputStream = monitor.getInputStream("八一公园");
            response.setHeader("Content-Type","video/mp4;charset=utf-8");
            IOUtils.copy(inputStream,response.getOutputStream());
            response.flushBuffer();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    class User{
        String name;
        int score;

        public User(String name, int score) {
            this.name = name;
            this.score = score;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof User)) return false;
            User User = (User) o;
            return score == User.score && Objects.equals(name, User.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, score);
        }
    }

}
