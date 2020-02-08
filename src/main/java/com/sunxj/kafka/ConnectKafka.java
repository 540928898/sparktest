package com.sunxj.kafka;


import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
//import org.apache.kafka.*;

public class ConnectKafka {

    public final static String Group_ID = "test-consumer-group";
    public final static String TOPIC = "test1";
    public final static String MY_TOPIC = "test1";
    public final static int BUFFER_SIZE = 64 * 1024;
    public final static int TIMEOUT = 20000;
    public final static int INTERVAL = 10000;
    public final static String BROKER_LIST = "localhost:9092";
    public final static int GET_MEG_INTERVAL = 1000;

}
