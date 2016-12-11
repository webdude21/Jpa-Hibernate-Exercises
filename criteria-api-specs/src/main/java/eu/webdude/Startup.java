package eu.webdude;

import com.querydsl.core.types.dsl.BooleanExpression;
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

	private QEmployee employee;

	Startup() {
		this.employee = QEmployee.employee;
	}

	@Override
	public void run(String... strings) throws Exception {
		countBy(employee.lastName.endsWithIgnoreCase("on"), employee.firstName.startsWithIgnoreCase("T"));
	}
	private void countBy(BooleanExpression... predicates) {
		JPAQueryFactory query = new JPAQueryFactory(entityManager);
		long resultEmplCount = query.selectFrom(employee)
			.where(predicates)
			.fetchCount();

		System.out.printf("%d employees matching the filter criteria %n", resultEmplCount);
	}
}
