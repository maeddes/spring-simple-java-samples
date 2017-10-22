package de.maeddes.SpringTodoListSimpleCQRS;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class SpringTodoListSimpleCqrsApplication {

	@RequestMapping("/hello")
	public String hello(){

		return "Hello";
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringTodoListSimpleCqrsApplication.class, args);
	}
}

@Controller
@RequestMapping("/")
class ToDoListQueryController {

	@Autowired
	ToDoItemRepository toDoItemRepository;

	@RequestMapping("/test")
	public String test(){

		return "Ok";
	}

	@RequestMapping(method = RequestMethod.GET)
	public String getItems(Model model){

		System.out.println("In getItems: ");
		List<ToDoItem> items = toDoItemRepository.findAll();
		System.out.println("In getItems: "+items);
		if(items != null){
			model.addAttribute("items", items);
		}
		System.out.println("In getItems: "+items);

		model.addAttribute("name","matthias");
		return "items";

	}
}


@Controller
@RequestMapping("/")
class ToDoListCommandController {

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

		return "redirect:/";

	}

	@RequestMapping(value = "/done/{id}", method = RequestMethod.POST)
	public String setItemDone(@PathVariable int id){

		System.out.println("In setItemDone: "+id);
		//toDoItemRepository.delete(id);

		return "redirect:/";

	}
}
