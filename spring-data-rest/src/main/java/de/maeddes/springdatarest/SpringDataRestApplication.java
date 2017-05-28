package de.maeddes.springdatarest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class SpringDataRestApplication {

    @Autowired
    GreetingRepository greetingRepository;

    @RequestMapping("/initialize")
    void initialize(){

        greetingRepository.save(new Greeting("German","Hallo"));
        greetingRepository.save(new Greeting( "English", "Hello"));

    }

    @RequestMapping("/clear")
    void clear(){

        greetingRepository.deleteAll();

    }

    public static void main(String[] args) {
        SpringApplication.run(SpringDataRestApplication.class, args);
    }

}

@Entity
class Greeting{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    String language;
    String greeting;

    protected Greeting() {
    }

    public Greeting(String language, String greeting) {
        this.language = language;
        this.greeting = greeting;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
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
                "language='" + language + '\'' +
                " says Hi with ='" + greeting + '\'' +
                '}';
    }
}

@RepositoryRestResource(collectionResourceRel = "greetings", path = "greetings")
interface GreetingRepository extends PagingAndSortingRepository<Greeting, Long> {

    List<Greeting> findByLanguage(@Param("language") String language);

}




