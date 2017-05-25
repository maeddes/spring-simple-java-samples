package de.maeddes.springamqp;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mhs on 24.05.17.
 */


@RabbitListener(queues = "my-queue")
public class AMQPReceiver {

    public static List<String> messageList = new ArrayList<String>();

    @RabbitHandler
    public void receive(String in) {
            System.out.println("Received '" + in + "'");
            messageList.add(in);
    }

    public static String getMessages() {
        return messageList.toString();
    }
}
