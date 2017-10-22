package de.maeddes.SpringTodoListSimpleCQRS;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

@RabbitListener(queues = "my-queue")
public class ToDoListCommandReceiver {

    @Autowired
    ToDoItemRepository toDoItemRepository;

    @RabbitHandler
    public void receive(String in) {
        System.out.println("Received '" + in + "'");
        this.toDoItemRepository.save(new ToDoItem(in));
    }

}
