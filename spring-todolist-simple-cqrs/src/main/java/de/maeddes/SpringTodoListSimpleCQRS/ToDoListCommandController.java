package de.maeddes.SpringTodoListSimpleCQRS;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class ToDoListCommandController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Queue queue;

    public void send(String message) {
        this.rabbitTemplate.convertAndSend(queue.getName(), message);
        System.out.println(" [x] Sent '" + message + "'");
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addItem(ToDoItem toDoItem){

        System.out.println("In addItem: "+toDoItem);
        this.send(toDoItem.getDescription());

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "redirect:/";

    }

    @RequestMapping(value = "/done/{id}", method = RequestMethod.POST)
    public String setItemDone(@PathVariable int id){

        System.out.println("In setItemDone: "+id);
        this.send("done:"+id);

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "redirect:/";

    }
}

