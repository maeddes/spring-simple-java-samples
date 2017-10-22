package de.maeddes.springamqp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@SpringBootApplication
@RestController
public class SpringAmqpApplication {

    @Autowired
    AMQPSender sender;

    @Autowired
    AMQPReceiver receiver;

    @RequestMapping("/send")
    public String send() {

        String message = String.format("Test String %s", UUID.randomUUID());
        sender.send(message);

        return "Just sent: "+message;

    }

    @RequestMapping("/receive")
    public String receive(){

        String returnString = receiver.getMessages();
        receiver.clearMessages();

        return returnString;

    }

	public static void main(String[] args) {
		SpringApplication.run(SpringAmqpApplication.class, args);
	}
}
