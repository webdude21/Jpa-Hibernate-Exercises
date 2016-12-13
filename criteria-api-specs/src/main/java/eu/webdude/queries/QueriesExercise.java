package eu.webdude.queries;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import eu.webdude.model.QEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;

@Component
public class QueriesExercise {

	private final JPAQueryFactory query;

	private final EntityManager entityManager;

	private QEmployee employee;

	@Autowired
	QueriesExercise(EntityManager entityManager) {
		this.entityManager = entityManager;
		this.employee = QEmployee.employee;
		this.query = new JPAQueryFactory(entityManager);
	}

	public void runQueries() {
		salaryOfThoseTakingTheMinimumSalary();
	}

	/**
	 * Write a query to find the names and salaries of the employees that take the minimal salary in the company.
	 * Use a nested SELECT statement.
	 */
	void salaryOfThoseTakingTheMinimumSalary() {
		List<Tuple> result = query
			.select(employee.firstName, employee.lastName)
			.from(employee)
			.where(employee.salary.eq(query.select(employee.salary.min()).from(employee).fetchOne()))
			.fetch();

		result.forEach(res -> System.out.printf("Name: %s %s%n",
			res.get(employee.firstName),
			res.get(employee.lastName)));
	}

}
