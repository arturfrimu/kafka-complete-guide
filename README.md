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
- [Produce a Message Without Keys](#produce-a-message-without-keys)
- [Produce a Message With Keys](#produce-a-message-with-keys)
- [Produce a Message With Acknowledgements](#produce-a-message-with-acknowledgements)
- [Consume from the beginning of the Topic](#consume-from-the-beginning-of-the-topic)
- [Show both key and values in the output](#show-both-key-and-values-in-the-output)

---

# Create Kafka Topic

To create a new topic with default values, run the following command:

```bash
kafka-topics.sh --create --topic my_topic_name --bootstrap-server localhost:9092
```

Replace <span style="color:green">**my_topic_name**</span> with the name you want for your topic.

- partitions=0
- replication-factor=1

To create a new topic with partitions and replications, run the following command:

```bash
kafka-topics.sh --create --topic my_topic_name --bootstrap-server localhost:9092 --partitions 3 --replication-factor 3
```
Replace <span style="color:green">**my_topic_name**</span> with the name you want for your topic.

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
Replace <span style="color:green">**my_topic_name**</span> with the name of the topic you want to describe.

# Increase Partitions in a Kafka Topic

To increase the number of partitions for a specific topic, run the following command:

```shell
kafka-topics.sh --alter --topic my_topic_name --bootstrap-server localhost:9092 --partitions new_partition_count
```
Replace <span style="color:green">**my_topic_name**</span> with the name of the topic, and <span style="color:green">**new_partition_count**</span> with the new number of partitions.

# Delete a Kafka Topic

To delete a topic, run the following command:

```shell
kafka-topics.sh --delete --topic my_topic_name --bootstrap-server localhost:9092
```
Replace <span style="color:green">**my_topic_name**</span> with the name of the topic you wish to delete.

# Produce a Message Without Keys

When producing messages without specifying a key, the messages will be sent in a round-robin fashion to all available partitions in the topic.
To produce a message without a key, use the following command:

```bash
echo "This is my message" | kafka-console-producer.sh --broker-list localhost:9092 --topic my_topic_name
```

Replace <span style="color:green">my_topic_name</span> with the name of your Kafka topic.

# Produce a Message With Keys

If you want to produce messages with keys, you need to provide the key separator, which is usually a tab character. Here's how you can do it:

```shell
echo -e "my_key\tThis is my message with a key" | kafka-console-producer.sh --broker-list localhost:9092 --topic my_topic_name --property "parse.key=true" --property "key.separator=\t"
```

Replace <span style="color:green">my_topic_name</span> with the name of your Kafka topic and <span style="color:green">my_key</span> with the key you want to use.

## Produce a Message With Acknowledgements

Kafka allows you to specify the level of acknowledgement you desire from the broker for produced messages. You can use the `--acks` flag to set this level when producing messages. The available options are:

- `0`: No acknowledgements. The producer takes no responsibility for message loss.
- `1`: Only the leader broker will acknowledge.
- `all` or `-1`: The leader and all its replicas will acknowledge.

Here is an example:

```bash
echo "This is a message with acks" | kafka-console-producer.sh --broker-list localhost:9092 --topic my_topic_name --acks 1
```

Replace <span style="color:green">my_topic_name</span> with the name of your Kafka topic

In this example, replace <span style="color:green">my_topic_name</span> with the name of your Kafka topic. 
The --acks 1 ensures that only the leader broker will acknowledge the message.

To require acknowledgements from all in-sync replicas, you can use --acks all like so:

```shell
echo "This is a message with acks from all in-sync replicas" | kafka-console-producer.sh --broker-list localhost:9092 --topic my_topic_name --acks all
```

Replace <span style="color:green">my_topic_name</span> with the name of your Kafka topic and <span style="color:green">all</span> with the acknowledgement you want to use.

# Consume from the beginning of the Topic

To consume messages from the beginning of the topic, you can run:

```shell
kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic my_topic_name --from-beginning --property print.key=true --property print.partition=true --property key.separator="  :  "
```

Replace <span style="color:green">my_topic_name</span> with the name of your Kafka topic

# Show both key and values in the output

```shell
kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic my_topic_name --property print.key=true --property print.partition=true --property key.separator="  :  "
```

Replace <span style="color:green">my_topic_name</span> with the name of your Kafka topic