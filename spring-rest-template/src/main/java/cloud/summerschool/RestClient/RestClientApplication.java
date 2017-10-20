package cloud.summerschool.RestClient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
public class RestClientApplication {

    @RequestMapping("/testSimple")
    String callService(){

        RestTemplate template = new RestTemplate();
        String url = "http://localhost:8080/getCounter";

        ResponseEntity<String> response = template.getForEntity(url, String.class);

        return response.getBody();


    }

    @RequestMapping("/testFactory")
    String callServiceViaFactory(){



    }

	public static void main(String[] args) {
		SpringApplication.run(RestClientApplication.class, args);
	}
}
