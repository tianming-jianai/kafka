package com.atguigu.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * @BelongsProject: kafka
 * @BelongsPackage: com.atguigu.kafka.producer
 * @author: 张世罡
 * @CreateTime: 2023/3/8 19:04
 * @Description: 同步发送
 */
public class CustomerProducerSync {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 1. 创建kafka生产者对象
        // 配置
        Properties p = new Properties();
        // 连接集群
        p.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        // 指定对应的key和value序列化类型
        p.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        p.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(p);
        // 2. 发送数据
        for (int i = 0; i < 5; i++) {
            kafkaProducer.send(new ProducerRecord<String, String>("test", "atguigu " + i)).get();
        }
        // 3. 关闭资源
        kafkaProducer.close();
    }
}
