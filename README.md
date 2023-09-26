# Kafka Installation Guide with Homebrew

## Steps

1. **Update Homebrew**
    ```bash
    brew update
    ```

2. **Install Kafka**
    ```bash
    brew install kafka
    ```

3. **Start Zookeeper Service**
    ```bash
    brew services start zookeeper
    ```

4. **Start Kafka Service**
    ```bash
    brew services start kafka
    ```

5. **Stop Zookeeper and Kafka Services (Optional)**
    ```bash
    brew services stop kafka
    brew services stop zookeeper
    ```

## Kafka Topic Management
- [Create Kafka Topic](#create-kafka-topic)
- [List Kafka Topics](#list-kafka-topics)
- [Describe Kafka Topics](#describe-kafka-topics)
- [Increase Partitions in a Kafka Topic](#increase-partitions-in-a-kafka-topic)
- [Delete a Kafka Topic](#delete-a-kafka-topic)

---

# Create Kafka Topic

To create a new topic with default values, run the following command:

```bash
kafka-topics.sh --create --topic my_topic_name --bootstrap-server localhost:9092
```

Replace **my_topic_name** with the name you want for your topic.

- partitions=0
- replication-factor=1

To create a new topic with partitions and replications, run the following command:

```bash
kafka-topics.sh --create --topic my_topic_name --bootstrap-server localhost:9092 --partitions 3 --replication-factor 3
```
Replace **my_topic_name** with the name you want for your topic.

- partitions=3
- replication-factor=3

# List Kafka Topics

To list all available topics, run the following command:

```bash
kafka-topics.sh --list --bootstrap-server localhost:9092
```

# Describe Kafka Topics

To describe a specific topic, run the following command:

```bash
kafka-topics.sh --describe --topic my_topic_name --bootstrap-server localhost:9092
```
Replace **my_topic_name** with the name of the topic you want to describe.

# Increase Partitions in a Kafka Topic

To increase the number of partitions for a specific topic, run the following command:

```shell
kafka-topics.sh --alter --topic my_topic_name --bootstrap-server localhost:9092 --partitions new_partition_count
```
Replace **my_topic_name** with the name of the topic, and **new_partition_count** with the new number of partitions.

# Delete a Kafka Topic

To delete a topic, run the following command:

```shell
kafka-topics.sh --delete --topic my_topic_name --bootstrap-server localhost:9092
```
Replace **my_topic_name** with the name of the topic you wish to delete.