package com.nrs.pointservice.restfulwebservices.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {
	
	private static final String CA_PEM_LOCATION =
	        "C:/Users/kumar/OneDrive/Desktop/CustPoint/restfulwebservices/src/main/resources/kafka/ca.pem";

	private static final String SVC_PEM_LOCATION =
	        "C:/Users/kumar/OneDrive/Desktop/CustPoint/restfulwebservices/src/main/resources/kafka/svc.pem";

//	private static final String KAFKA_KEY_PATH =
//	        "C:/Users/kumar/OneDrive/Desktop/CustPoint/restfulwebservices/src/main/resources/kafka/service.key";

	@Bean
	public ConsumerFactory<String, String> consumerFactory() throws Exception {
		
		
	    ClassPathResource resource =
	            new ClassPathResource("kafka/ca.pem");

	    Map<String, Object> props = new HashMap<>();

	    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
	            "kafka-30de129b-singhshubhang1920-0843.j.aivencloud.com:15220");

	    props.put(ConsumerConfig.GROUP_ID_CONFIG,
	            "orders-quickstart-consumer");

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

@Bean
    public ConcurrentKafkaListenerContainerFactory<String, String>
    kafkaListenerContainerFactory() throws Exception {

        ConcurrentKafkaListenerContainerFactory<String, String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();

        factory.setConsumerFactory(consumerFactory());

        return factory;
    }
}


//package com.nrs.pointservice.restfulwebservices.kafka;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.nrs.pointservice.restfulwebservices.dto.OrderRequestDto;
//import com.nrs.pointservice.restfulwebservices.service.OrderService;
//
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Service;
//
//@Service
//public class OrderKafkaConsumer {
//
//    private final ObjectMapper objectMapper;
//    private final OrderService orderService;
//
//    public OrderKafkaConsumer(ObjectMapper objectMapper,
//                              OrderService orderService) {
//        this.objectMapper = objectMapper;
//        this.orderService = orderService;
//    }
//
//    @KafkaListener(
//        topics = "orders",
//        groupId = "orders-quickstart-consumer"
//    )
//    public void consume(String message) {
//
//        try {
//            OrderRequestDto dto =
//                    objectMapper.readValue(message, OrderRequestDto.class);
//
//            orderService.saveOrderFromKafka(dto);
//
//        } catch (Exception e) {
//            throw new RuntimeException("Failed to consume order JSON", e);
//        }
//    }
//}
