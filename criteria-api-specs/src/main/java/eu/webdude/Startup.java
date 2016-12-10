package eu.webdude;

import com.querydsl.jpa.impl.JPAQueryFactory;
import eu.webdude.model.QEmployee;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class Startup implements CommandLineRunner {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void run(String... strings) throws Exception {
		System.out.println("Started");
		System.out.println(entityManager.getMetamodel());
		doStuff();
	}

	private void doStuff() {
		JPAQueryFactory query = new JPAQueryFactory(entityManager);
		QEmployee employee = QEmployee.employee;
		long resultEmplCount = query.selectFrom(QEmployee.employee)
			.where(employee.lastName.endsWith("on"))
			.fetchCount();

		System.out.printf("%d with names ending with 'on'%n", resultEmplCount);
	}
}
