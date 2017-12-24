package com.marcuschiu.ampqrabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
//@EnableBinding(Source.class)
@RestController
public class AmpqRabbitmqApplication {

	public static void main(String[] args) {
		SpringApplication.run(AmpqRabbitmqApplication.class, args);
	}

//	@Bean
//	@InboundChannelAdapter(value = Source.OUTPUT, poller = @Poller(fixedDelay = "5000", maxMessagesPerPoll = "1"))
//	public MessageSource<String> orderSource() {
//		return () -> {
//			String str = "hello world";
//			System.out.println("logging");
//			return new GenericMessage<>(str);
//		};
//	}

	@Autowired
	private RabbitTemplate template;

	@Value("${exchange.name}")
	String exchangeName;

	@Value("${routing.key:something}")
	String routingKey;

	@GetMapping("/")
	public String send() {
		String message = "Hello World";
		this.template.convertAndSend(exchangeName, routingKey, message);
		return "sent";
	}
}
