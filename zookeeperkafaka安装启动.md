# windows 下启动zookeeper/kafka

## zookeeper

1. 下载文件 apache-zookeeper-3.8.1-bin.tar.gz
2. 主目录创建文件夹
data
log
3. 修改配置文件config/zoo.cfg
dataDir=D:\\dev\\zookeeper\\apache-zookeeper-3.8.1-bin\\apache-zookeeper-3.8.1-bin\\data
dataLogDir=D:\\dev\\zookeeper\\apache-zookeeper-3.8.1-bin\\apache-zookeeper-3.8.1-bin\\log
4. 启动zookeeper
bin/zkServer.cmd
bin/zkCli.cmd

默认端口：2181

## kafka

windows 下启动kafka
1. 修改配置
2. 启动kafaka
.\bin\windows\kafka-server-start.bat .\config\server.properties
3. 创建topic
.\bin\windows\kaka-topics.bat --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic test
4. 启动生产者
.\kafka-console-producer.bat --broker-list localhost:9092 --topic test
5. 启动消费者
.\kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic test --from-beginning
