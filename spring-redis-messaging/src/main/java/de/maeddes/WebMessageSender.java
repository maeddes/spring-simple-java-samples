package de.maeddes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@SpringBootApplication
@RestController
public class WebMessageSender {

    @Autowired
    private RedisMessagePublisher redisMessagePublisher;

    @Autowired
    private RedisMessageSubscriber redisMessageSubscriber;

	@RequestMapping("/send")
    public String send() {

	    String message = String.format("Test String %s", UUID.randomUUID());
        redisMessagePublisher.publish(message);
        return "Just sent: "+message;

	}

	@RequestMapping("/receive")
    public String receive(){

	    return redisMessageSubscriber.getMessages();

    }

	public static void main(String[] args) throws InterruptedException {

		SpringApplication.run(Old.class, args);

	}
}
