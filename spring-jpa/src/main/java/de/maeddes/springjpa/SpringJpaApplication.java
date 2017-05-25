package de.maeddes.springjpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;
import java.util.stream.Stream;

import static javax.persistence.GenerationType.SEQUENCE;

@SpringBootApplication
@RestController
public class SpringJpaApplication {

    @Autowired
    GreetingRepository greetingRepository;

    @RequestMapping("/initialize")
    String initialize(){

        Stream.of("Hallo,Hello,Howdy,Servus,G'Day".split(","))
                .forEach(greeting -> greetingRepository.save(new Greeting(greeting)));

        return "done";

    }

    @RequestMapping("/clear")
    String clear(){

        greetingRepository.deleteAll();
        return "cleared";
    }

    @RequestMapping("/all")
    String getAll(){

        StringBuilder stringBuilder = new StringBuilder();
        for (Greeting greeting : greetingRepository.findAll()) {

            stringBuilder.append(greeting.toString());
            stringBuilder.append("<br>");

        }

        return stringBuilder.length() == 0 ? "Nothing found" : stringBuilder.toString();

    }

    @RequestMapping("/id/{id}")
    String getById(@PathVariable String id){

        List<Greeting> greetings = greetingRepository.findById(Long.valueOf(id));
        return greetings.isEmpty() ? "Not found" : greetings.get(0).toString();

    }

    @RequestMapping("/add/{greeting}")
    String addGreeting(@PathVariable String greeting){

        greetingRepository.save(new Greeting(greeting));
        return String.format("Should actually be a POST operation. Still saved %s", greeting);

    }

	public static void main(String[] args) {
		SpringApplication.run(SpringJpaApplication.class, args);
	}
}

interface GreetingRepository extends CrudRepository<Greeting, Long> {

    List<Greeting> findById(Long id);

}

@Entity
class Greeting{

    @Id
    @GeneratedValue(strategy= SEQUENCE)
    Long id;
    String greeting;

    protected Greeting(){}

    public Greeting(String greeting) {
        this.greeting = greeting;
    }

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    @Override
    public String toString() {
        return "Greeting{" +
                "id=" + id +
                ", greeting='" + greeting + '\'' +
                '}';
    }

}
