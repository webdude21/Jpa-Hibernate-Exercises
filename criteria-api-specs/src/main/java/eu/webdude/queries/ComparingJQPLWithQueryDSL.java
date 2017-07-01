package eu.webdude.queries;

import com.querydsl.core.types.Ops;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.BooleanOperation;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import eu.webdude.model.QEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class ComparingJQPLWithQueryDSL {

	private static final String AVERAGE_SALARY_TEXT = "Average salary of employees is %f%n";

	private final JPAQueryFactory query;

	private final EntityManager entityManager;

	private QEmployee employee;

	@Autowired
	ComparingJQPLWithQueryDSL(EntityManager entityManager) {
		this.entityManager = entityManager;
		this.employee = QEmployee.employee;
		this.query = new JPAQueryFactory(entityManager);
	}

	public void testQuery() {
		System.out.printf(AVERAGE_SALARY_TEXT, getAverageSalaryWithJpql(" where e.manager IS NULL"));
		System.out.printf(AVERAGE_SALARY_TEXT, getAverageSalaryWithJpqlAndDynamicFieldName(" where e.manager IS NULL", "salary"));
		System.out.printf(AVERAGE_SALARY_TEXT, getAverageSalaryWithQueryDsl(employee.salary.gt(10_000).and(employee.salary.lt(30_000))));
		countBy(employee.lastName.endsWithIgnoreCase("on"), employee.firstName.startsWithIgnoreCase("T"));

		Path<String> salary = Expressions.path(String.class, QEmployee.employee, "salary");
		BooleanOperation booleanOperation = Expressions.predicate(Ops.BETWEEN, salary, Expressions.constant(10_000), Expressions.constant(30_000));

		System.out.printf(AVERAGE_SALARY_TEXT, getAverageSalaryWithQueryDsl(booleanOperation));
	}

	// doing this of course could get you into trouble with sql injection
	private double getAverageSalaryWithJpql(String whereClause) {
		return (double) entityManager.createQuery("select avg(e.salary) from Employee as e" + whereClause).getSingleResult();
	}

	// doing this of course could get you into trouble with sql injection
	private double getAverageSalaryWithJpqlAndDynamicFieldName(String whereClause, String fieldName) {
		return (double) entityManager.createQuery("select avg(e." + fieldName + ") from Employee as e" + whereClause).getSingleResult();
	}

	private Double getAverageSalaryWithQueryDsl(BooleanExpression... predicates) {
		return query
			.select(employee.salary.avg())
			.from(employee)
			.where(predicates)
			.fetchOne();
	}

	private void countBy(BooleanExpression... predicates) {
		long resultEmplCount = query
			.select(employee.salary)
			.from(employee)
			.where(predicates)
			.fetchCount();

		System.out.printf("%d employees matching the filter criteria %n", resultEmplCount);
	}
}
