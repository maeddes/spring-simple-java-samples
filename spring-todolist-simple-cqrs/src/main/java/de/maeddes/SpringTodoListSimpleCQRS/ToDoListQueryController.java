package de.maeddes.SpringTodoListSimpleCQRS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/")
public class ToDoListQueryController {

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