package cn.hhuc.controller;

import cn.hhuc.service.heatmap.HeadMapService;
import cn.hhuc.service.heatmap.WebSocketService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@Controller
public class HeadMapController {

    private static final Logger logger = Logger.getLogger(HeadMapController.class);

    @Autowired
    private WebSocketService webSocketService;

    /**
     * 每个5s向前端推送一个信息
     */
    @Scheduled(fixedRate = 5000)
    public void sendHeadMapDataToFront(){
        logger.info("发送热力图数据");
        webSocketService.sendHeadMapData("map","/heatmap");
    }
}
