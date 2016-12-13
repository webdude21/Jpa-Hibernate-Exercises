package eu.webdude;

import eu.webdude.queries.ComparingJQPLWithQueryDSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Startup implements CommandLineRunner {

	private ComparingJQPLWithQueryDSL comparingJQPLWithQueryDSL;

	@Autowired
	Startup(ComparingJQPLWithQueryDSL comparingJQPLWithQueryDSL) {
		this.comparingJQPLWithQueryDSL = comparingJQPLWithQueryDSL;
	}

	@Override
	public void run(String... strings) throws Exception {
		comparingJQPLWithQueryDSL.testQuery();
	}
}
