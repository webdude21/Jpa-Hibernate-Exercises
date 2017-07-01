package eu.webdude.queries;

import eu.webdude.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsingJpaRepositoryWithSpecifications {

	private EmployeeRepository employeeRepository;

	@Autowired
	public UsingJpaRepositoryWithSpecifications(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	public void testQuery() {
		long result = employeeRepository.count((root, criteriaQuery, cb) -> cb.between(root.get("salary"), 100, 100_000));
		System.out.println(result);
	}
}
