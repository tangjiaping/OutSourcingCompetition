package cn.hhuc.controller;

import cn.hhuc.bean.TripVolume;
import cn.hhuc.mapper.ITripVolume;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.WebSocketSession;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.server.ServerEndpoint;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@RestController
public class Test {
    @Autowired
    private ITripVolume tripVolume;

    @RequestMapping("/tripVolume")
    public List<TripVolume> testTripVolume(){
        System.out.println("请求tripVolume");
        return tripVolume.getAreaTripVolumes();
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
