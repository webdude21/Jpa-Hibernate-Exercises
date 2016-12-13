package eu.webdude.queries;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import eu.webdude.model.QEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

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

	void runQueries() {

	}

	/**
	 * Write a query to find the names and salaries of the employees that take the minimal salary in the company.
	 * Use a nested SELECT statement.
	 */
	void salaryOfThoseTakingTheMinimumSalary() {
		JPAQuery<BigDecimal> result = query
			.select(employee.salary.min())
			.from(employee)
			.fetchAll();
	}

}
