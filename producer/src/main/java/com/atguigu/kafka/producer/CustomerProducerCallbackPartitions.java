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
 * @CreateTime: 2023/3/8 19:04
 * @Description: 生产者分区策略
 * 生产环境一般将表名作为key(分区名称)，可以将一个表的数发送到同一分区
 */
public class CustomerProducerCallbackPartitions {
    public static void main(String[] args) {
        // 1. 创建kafka生产者对象
        // 配置
        Properties p = new Properties();
        // 连接集群
        p.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        // 指定对应的key和value序列化类型
        p.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        p.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // 关联自定义分区器
        p.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, "com.atguigu.kafka.producer.MyPartitioner");

        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(p);
        // 2. 发送数据
        for (int i = 0; i < 5; i++) {
            kafkaProducer.send(new ProducerRecord<String, String>("test", "a", "atguigu" + i), (metadata, e) -> {
                if (e == null) {
                    // 正常
                    System.out.println("主题：" + metadata.topic() + " 分区：" + metadata.partition());
                } else {
                    // 异常
                    e.printStackTrace();
                }
            });
        }

        // 3. 关闭资源
        kafkaProducer.close();
    }
}
