package de.maeddes.ToDoList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@SpringBootApplication
@Controller
@RequestMapping("/")
public class ToDoListApplication {

	@Autowired ToDoItemRepository toDoItemRepository;

	@RequestMapping(method = RequestMethod.GET)
	public String getItems(Model model){

		List<ToDoItem> items = toDoItemRepository.findAll();
		if(items != null){
			model.addAttribute("items", items);
		}

		model.addAttribute("name","matthias");
		return "items";

	}

	@RequestMapping(method = RequestMethod.POST)
	public String addItem(ToDoItem toDoItem){

		System.out.println("In addItem: "+toDoItem);
		toDoItemRepository.save(toDoItem);

		return "redirect:/";

	}

	@RequestMapping(value = "/done/{id}", method = RequestMethod.POST)
	public String setItemDone(@PathVariable int id){

		System.out.println("In setItemDone: "+id);
		toDoItemRepository.delete(id);

		return "redirect:/";

	}

	@GetMapping("/add/{description}")
	public void add(@PathVariable String description){

		System.out.println("In add: "+description);
		toDoItemRepository.save(new ToDoItem(description));

		return;
	}


	public static void main(String[] args) {
		SpringApplication.run(ToDoListApplication.class, args);
	}
}

@Entity
class ToDoItem{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer id;

	String description;
	boolean done = false;

	public ToDoItem(){}

	public ToDoItem(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public Integer getId() {
		return id;
	}
}

//@RepositoryRestResource(collectionResourceRel = "todoitems")
interface ToDoItemRepository extends PagingAndSortingRepository<ToDoItem,Integer>{

	List<ToDoItem> findAll();

}