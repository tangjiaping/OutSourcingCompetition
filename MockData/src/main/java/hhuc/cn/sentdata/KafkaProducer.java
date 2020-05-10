package hhuc.cn.sentdata;

import hhuc.cn.mocklngandlat.MockLngLat;
import hhuc.cn.mocktime.MockTime;
import hhuc.cn.mockuser.MockUser;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class KafkaProducer {
    // isStart用来判断模拟数据线程是否结束
    private volatile Boolean isStart = false;
    // kafka生产者
    private org.apache.kafka.clients.producer.KafkaProducer<String,String> kafkaProducer;
    // kafka配置文件
    private Properties properties;


    /**
     * 初始化函数
     * 主要完成配置文件的创建和配置文件信息的添加
     * @return
     */
    public Properties init(){
        properties = new Properties();
        // kafka集群ip:port
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"39.107.46.146:9092");
        // 设置重连接次数
        properties.put(ProducerConfig.RETRIES_CONFIG,1);
        properties.put(ProducerConfig.ACKS_CONFIG,"all");
        // 设置发送时间间隔
        properties.put(ProducerConfig.LINGER_MS_CONFIG,1000);
        // 设置发送文件大小
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG,112640);
        // 设置key,value的序列化类型
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringSerializer");
        return properties;

    }

    /**
     * kafka生成者启动函数
     * 该函数主要完成kafka消息的生产和发送
     * @param prop
     * @param topic
     */
    public void start(Properties prop,String topic){
        isStart = true;
        // 给kafka生产者设置配置文件
        kafkaProducer = new org.apache.kafka.clients.producer.KafkaProducer<String,String>(prop);
        // 通过lamda表达式创建一个线程，用来实时生产消息并实时发送
        new Thread(() -> {
            while (isStart){
                try {
                        // 设置每秒的消息随机量
                        int dataNum = (int)(Math.random() * 150);
                        while (dataNum-- > 0){
                            // 将模拟的时间、模拟的用户、模拟的经纬度组成生产者消息
                            String data = MockTime.mockTime() + " " + MockUser.mockUser() + " " + "null" + " " + MockLngLat.mockHotPlaceLngLat();
                            // 将生产的消息封装成ProducerRecord对象，并向指定topic发送
                            ProducerRecord<String, String> record = new ProducerRecord<>(topic, data);
                            kafkaProducer.send(record);
                            System.out.println(data + "---------->发送成功");
                    }
                        Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 该函数用来控制生产者的停止，并处理收尾工作
     */
    public void stop(){
        isStart = false;
        kafkaProducer.close();
    }

    // 模拟数据
    public static void main(String[] args) throws InterruptedException {
        KafkaProducer producer = new KafkaProducer();
        Properties prop = producer.init();
        producer.start(prop,"mockdata2");
    }
}
