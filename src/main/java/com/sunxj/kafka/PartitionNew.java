package com.sunxj.kafka;

import kafka.utils.VerifiableProperties;
import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

public class PartitionNew implements Partitioner {

        @Override
        public void configure(Map<String, ?> configs) {

        }

        @Override
        public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
            if (Integer.parseInt((String)key)%3==1)
                return 0;
            else if (Integer.parseInt((String)key)%3==2)
                return 1;
            else return 2;
        }

        @Override
        public void close() {

        }

    }
