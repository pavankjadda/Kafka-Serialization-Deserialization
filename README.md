# Kafka Serialization and Deserialization 

## What's this?
This repository demonstrates Kafka Serialization and Deserialization capabilities with Generic JSON serializers/deserializers
## How to run?
1. Download and run kafka from [Confluent](https://docs.confluent.io/current/installation/installing_cp.html#zip-and-tar-archives) or [Apache Kafka website](https://www.tutorialspoint.com/apache_kafka/apache_kafka_installation_steps.htm)
2. Clone the repository and open IDE like IntelliJ or Eclipse
3. Run [KafkaserializationApplication](https://github.com/pavankjadda/Kafka-Serialization-Deserialization/blob/master/src/main/java/com/kafkaserialization/KafkaserializationApplication.java) class
4. Open browser and go to http://localhost:8080/sendorder, this will send order event (object) using KafkaProducer
5. Run [ReceiveEvent](https://github.com/pavankjadda/Kafka-Serialization-Deserialization/blob/master/src/main/java/com/kafkaserialization/service/ReceiveEvent.java) class 
6. ReceiveEvent logs should show you message like `"Event received ConsumerRecord(topic = order, partition = 0, offset = 33, CreateTime = 1523986321835, serialized key size = 14, serialized value size = 158, headers = RecordHeaders(headers = [], isReadOnly = false), key = ORD-1155869325, value = {orderId=ORD-1155869325, customerId=CU1001, orderItemName=Reebok Shoes, orderPlace=NewYork,NY, orderPurchaseTime=Tue Apr 17 13:32:01 EDT 2018})"`
