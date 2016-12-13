package eu.webdude;

import eu.webdude.queries.ComparingJQPLWithQueryDSL;
import eu.webdude.queries.QueriesExercise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Startup implements CommandLineRunner {

	private ComparingJQPLWithQueryDSL comparingJQPLWithQueryDSL;

	private QueriesExercise queriesExercise;

	@Autowired
	Startup(ComparingJQPLWithQueryDSL comparingJQPLWithQueryDSL, QueriesExercise queriesExercise) {
		this.comparingJQPLWithQueryDSL = comparingJQPLWithQueryDSL;
		this.queriesExercise = queriesExercise;
	}

	@Override
	public void run(String... strings) throws Exception {
		comparingJQPLWithQueryDSL.testQuery();
		queriesExercise.runQueries();
	}
}
