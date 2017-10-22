package de.maeddes.springamqp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringAmqpApplicationTests {

	@Autowired
	AMQPSender sender;

	@Autowired
	AMQPReceiver receiver;


	@Test
	public void contextLoads() {

		System.out.println(" ### Test executed!");
		assertThat(sender).isNotNull();
		assertThat(receiver).isNotNull();
		assertThat(sender).isNull();
	}

}
