package com.atguigu.kafka.producer;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * @BelongsProject: kafka
 * @BelongsPackage: com.atguigu.kafka.producer
 * @author: 张世罡
 * @CreateTime: 2023/3/8 20:40
 * @Description:
 */
public class CustomerProducerParameters {
    public static void main(String[] args) {
        // 配置
        Properties p = new Properties();
        // 连接kafka集群
        p.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        // 序列化
        p.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        p.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        // 缓冲区大小
        p.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
        // 批次大小
        p.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
        // linger.ms
        p.put(ProducerConfig.LINGER_MS_CONFIG, 1);
        // 压缩
        p.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, "snappy");
        // 1. 创建生产者
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(p);
        // 2. 发送数据
        for (int i = 0; i < 5; i++) {
            kafkaProducer.send(new ProducerRecord<>("test", "atguigu" + i));
        }
        // 3. 关闭资源
        kafkaProducer.close();
    }
}
