package cn.hhuc.controller.heatmap;

import cn.hhuc.service.heatmap.WebSocketService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

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
        webSocketService.sendHeadMapData("map","/heatmap");
    }
}
