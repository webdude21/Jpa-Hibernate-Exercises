package eu.webdude.queries;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import eu.webdude.model.QAddress;
import eu.webdude.model.QDepartment;
import eu.webdude.model.QEmployee;
import eu.webdude.model.QTown;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

@Component
public class QueriesExercise {

	private final JPAQueryFactory query;

	private final QDepartment department;

	private final QAddress address;

	private final QTown town;

	private QEmployee employee;

	@Autowired
	QueriesExercise(EntityManager entityManager) {
		this.employee = QEmployee.employee;
		this.department = QDepartment.department;
		this.town = QTown.town;
		this.address = QAddress.address;
		this.query = new JPAQueryFactory(entityManager);
	}

	public void runQueries() {
		salaryOfThoseTakingTheMinimumSalary();
		notAsMinimalSalary();
		employeesWithMinimumSalaryByDepartment();
		findAverageSalaryByDepartment(1);
		findAverageSalaryByDepartmentName("Engineering");
		findEmployeesCountByDepartment("Sales");
		findAllDepartmentsWithTheirAverageSalary();
		findHowManyEmployeesAreInEachDepartmentAndTown();
	}

	/**
	 * Write a query to find the names and salaries of the employees that take the minimal salary in the company.
	 * Use a nested SELECT statement.
	 */
	private void salaryOfThoseTakingTheMinimumSalary() {
		BigDecimal minSalary = query.select(employee.salary.min()).from(employee).fetchOne();

		List<Tuple> result = query
			.select(employee.firstName, employee.lastName)
			.from(employee)
			.where(employee.salary.eq(minSalary != null ? minSalary : BigDecimal.ZERO))
			.fetch();

		printEmployeeNames(result);
	}

	private void printEmployeeNames(List<Tuple> result) {
		result.forEach(res -> System.out.printf("Name: %s %s%n",
			res.get(employee.firstName),
			res.get(employee.lastName)));
	}

	/**
	 * Write a query to find the names and salaries of the employees
	 * that have a salary that is up to 10% higher than the minimal salary for the company.
	 */
	private void notAsMinimalSalary() {
		List<Tuple> result = query
			.select(employee.firstName, employee.lastName)
			.from(employee)
			.where(employee.salary.goe(query
				.select(employee.salary.min())
				.from(employee)
				.fetchOne()
				.multiply(BigDecimal.valueOf(0.1D))))
			.fetch();

		printEmployeeNames(result);
	}

	/**
	 * Write a query to find the full name, salary and department of the employees that
	 * take the minimal salary in their department. Use a nested SELECT statement
	 */
	private void employeesWithMinimumSalaryByDepartment() {
		List<Tuple> result = query
			.select(employee.firstName, employee.lastName, employee.salary, department.name)
			.from(employee)
			.innerJoin(employee.department, department)
			.where(employee.salary.eq(query
				.select(employee.salary.min())
				.from(employee)
				.where(employee.department.eq(department))))
			.orderBy(employee.salary.asc())
			.fetch();

		result.forEach(res -> System.out.printf("%s %s from department '%s' with salary: %f%n",
			res.get(employee.firstName),
			res.get(employee.lastName),
			res.get(department.name),
			res.get(employee.salary)));
	}

	/**
	 * Write a query to find the average salary in the department #1.
	 *
	 * @param departmentId the id of the department
	 */
	private void findAverageSalaryByDepartment(int departmentId) {
		Double result = query
			.select(employee.salary.avg())
			.from(employee)
			.where(employee.department.departmentId.eq(departmentId))
			.fetchOne();

		System.out.printf("The average salary in department #%d is %.2f$%n", departmentId, result);
	}

	/**
	 * Write a query to find the average salary in a given department.
	 *
	 * @param departmentName the name of the department
	 */
	private void findAverageSalaryByDepartmentName(String departmentName) {
		Double result = query
			.select(employee.salary.avg())
			.from(employee)
			.innerJoin(employee.department, department)
			.where(employee.department.name.eq(departmentName))
			.fetchOne();

		System.out.printf("The average salary in department %s is %.2f$%n", departmentName, result);
	}

	/**
	 * Write a query to find the number of employees in the a given department.
	 *
	 * @param departmentName the name of the department
	 */
	private void findEmployeesCountByDepartment(String departmentName) {
		Long result = query
			.select(employee.count())
			.from(employee)
			.innerJoin(employee.department, department)
			.where(employee.department.name.eq(departmentName))
			.fetchOne();

		System.out.printf("Department '%s' has %d employees", departmentName, result);
	}

	/**
	 * Write a query to find all departments and the average salary for each of them.
	 */
	private void findAllDepartmentsWithTheirAverageSalary() {
		List<Tuple> result = query
			.select(employee.salary.avg(), employee.department.name)
			.from(employee)
			.innerJoin(employee.department, department)
			.groupBy(employee.department.departmentId)
			.fetch();

		result.forEach(x ->
			System.out.printf("The average salary for '%s' is %.2f$%n",
				x.get(employee.department.name), x.get(employee.salary.avg())));
	}

	/**
	 * Write a query to find the count of all employees in each department and for each town.
	 */
	private void findHowManyEmployeesAreInEachDepartmentAndTown() {
		List<Tuple> result = query
			.select(employee.count(), employee.department.name, employee.address.town.name)
			.from(employee)
			.innerJoin(employee.department, department)
			.innerJoin(employee.address, address)
			.innerJoin(address.town, town)
			.groupBy(employee.department, employee.address.town)
			.fetch();

		result.forEach(x ->
			System.out.printf("%d employees in '%s' from %s%n", x.get(employee.count()),
				x.get(employee.department.name), x.get(employee.address.town.name)));
	}
}
