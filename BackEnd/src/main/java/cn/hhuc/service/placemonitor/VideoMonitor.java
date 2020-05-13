package cn.hhuc.service.placemonitor;



import cn.hhuc.mapper.MonitorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
public class VideoMonitor {

    @Autowired
    public MonitorMapper monitorMapper;

    private URLConnection connect(String ip,String number){
        URLConnection connection = null;
        try {
            URL url = new URL("http://" + ip + "/video/" + "viedo-" + number + ".mp4");
            connection = url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return connection;
        }
    }
    public InputStream getInputStream(String placeName) throws IOException {
        Map<String, String> strings = monitorMapper.getMonitorNumberAndIp(placeName);
        URLConnection connection = connect(strings.get("ip"),strings.get("number"));
        if (connection != null)
            return connection.getInputStream();
        return null;
    }
}
