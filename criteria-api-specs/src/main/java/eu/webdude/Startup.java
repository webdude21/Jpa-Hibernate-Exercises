package eu.webdude;

import eu.webdude.queries.ComparingJQPLWithQueryDSL;
import eu.webdude.queries.QueriesExercise;
import eu.webdude.queries.UsingJpaRepositoryWithSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Startup implements CommandLineRunner {

	private ComparingJQPLWithQueryDSL comparingJQPLWithQueryDSL;

	private QueriesExercise queriesExercise;

	private UsingJpaRepositoryWithSpecifications jpaRepositioriesWithSpecifications;

	@Autowired
	Startup(ComparingJQPLWithQueryDSL comparingJQPLWithQueryDSL, QueriesExercise queriesExercise, UsingJpaRepositoryWithSpecifications jpaRepositioriesWithSpecifications) {
		this.comparingJQPLWithQueryDSL = comparingJQPLWithQueryDSL;
		this.queriesExercise = queriesExercise;
		this.jpaRepositioriesWithSpecifications = jpaRepositioriesWithSpecifications;
	}

	@Override
	public void run(String... strings) throws Exception {
		comparingJQPLWithQueryDSL.testQuery();
		queriesExercise.runQueries();
		jpaRepositioriesWithSpecifications.testQuery();
	}
}
