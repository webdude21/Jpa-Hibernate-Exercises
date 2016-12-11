package eu.webdude;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import eu.webdude.model.QEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class Startup implements CommandLineRunner {

	private static final String AVERAGE_SALARY_TEXT = "Average salary of employees is %f%n";

	private final JPAQueryFactory query;

	private final EntityManager entityManager;

	private QEmployee employee;

	@Autowired
	Startup(EntityManager entityManager) {
		this.entityManager = entityManager;
		this.employee = QEmployee.employee;
		this.query = new JPAQueryFactory(entityManager);
	}

	private double getAverageSalaryWithJpdl(String whereClause) {
		return (double) entityManager.createQuery("select avg(e.salary) from Employee as e" + whereClause).getSingleResult();
	}

	private Double getAverageSalaryWithQueryDsl(BooleanExpression... predicates) {
		return query
			.select(employee.salary.avg())
			.from(employee)
			.where(predicates)
			.fetchOne();
	}

	@Override
	public void run(String... strings) throws Exception {
		System.out.printf(AVERAGE_SALARY_TEXT, getAverageSalaryWithJpdl(" where e.manager IS NULL"));
		System.out.printf(AVERAGE_SALARY_TEXT, getAverageSalaryWithQueryDsl(employee.manager.isNull()));
		getAverageSalaryWithQueryDsl();
		countBy(employee.lastName.endsWithIgnoreCase("on"), employee.firstName.startsWithIgnoreCase("T"));
	}

	private void countBy(BooleanExpression... predicates) {
		long resultEmplCount = query.selectFrom(employee).select(employee.salary)
			.where(predicates)
			.fetchCount();

		System.out.printf("%d employees matching the filter criteria %n", resultEmplCount);
	}
}
