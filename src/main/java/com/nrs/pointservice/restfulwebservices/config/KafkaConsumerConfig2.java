package com.nrs.pointservice.restfulwebservices.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import com.fasterxml.jackson.databind.deser.std.StringDeserializer;

public class KafkaConsumerConfig2 {
	
	private static final String CA_PEM_LOCATION =
	        "C:/Users/kumar/OneDrive/Desktop/CustPoint/restfulwebservices/src/main/resources/kafka/ca.pem";

	private static final String SVC_PEM_LOCATION =
	        "C:/Users/kumar/OneDrive/Desktop/CustPoint/restfulwebservices/src/main/resources/kafka/svc.pem";
	
	//@Bean
	public ConsumerFactory<String, String> consumerFactory() throws Exception {
		
		
	    ClassPathResource resource =
	            new ClassPathResource("kafka/ca.pem");

	    Map<String, Object> props = new HashMap<>();

	    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
	            "kafka-30de129b-singhshubhang1920-0843.j.aivencloud.com:15220");

	    props.put(ConsumerConfig.GROUP_ID_CONFIG,
	            "orders-status-quickstart-consumer");

	    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
	            StringDeserializer.class);

	    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
	            StringDeserializer.class);

	    props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

	    props.put("security.protocol", "SSL");
	    
	    
	    props.put("security.protocol", "SSL");
        props.put("ssl.truststore.location", CA_PEM_LOCATION);
        props.put("ssl.keystore.type", "PEM");
        props.put("ssl.truststore.type", "PEM");
        props.put("ssl.keystore.location", SVC_PEM_LOCATION);

	    return new DefaultKafkaConsumerFactory<>(props);
	}

    //@Bean
    public ConcurrentKafkaListenerContainerFactory<String, String>
    kafkaListenerContainerFactory() throws Exception {

        ConcurrentKafkaListenerContainerFactory<String, String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();

        factory.setConsumerFactory(consumerFactory());

        return factory;
    }
}



