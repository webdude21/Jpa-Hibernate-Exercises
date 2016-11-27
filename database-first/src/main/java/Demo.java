import entity.Address;
import entity.Department;
import entity.Employee;
import entity.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

public class Demo {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("university");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		printTowns(em);

		createSomeObjects(em);

		em.getTransaction().commit();
		em.close();
		emf.close();
	}

	private static void createSomeObjects(EntityManager em) {
		Town burgas = createBurgas(em);
		em.persist(burgas);
		createTrainingDepartment(em);
	}

	private static Department createTrainingDepartment(EntityManager em) {
		Department training = new Department();
		training.setName("Training");
		Employee manager = new Employee("Georgi", "Georgiev", "Ivanov",
			"Manager", new Date(), BigDecimal.valueOf(1000000));
		manager.setAddress(em.find(Address.class, 1));
		manager.setDepartment(em.find(Department.class, 1));
		training.setManager(manager);
		em.persist(manager);
		em.persist(training);
		IntStream.range(0, 5).forEach(index -> createEmployee(index, training, manager));
		return training;
	}

	private static Employee createEmployee(int index, Department training, Employee manager) {
		String name = String.format("Georgi%d", index);
		Employee employee = new Employee(name, name, name, "Employee", new Date(), BigDecimal.valueOf(900000));
		employee.setManager(manager);
		employee.setDepartment(training);
		return employee;
	}

	private static Town createBurgas(EntityManager em) {
		Town burgas = new Town();

		IntStream.range(0, 5).forEach(index -> {
			Address address = new Address();
			address.setTown(burgas);
			address.setAddressText(String.format("Burgas street %d", index + 1));
			em.persist(address);
		});

		burgas.setName("Burgas");
		return burgas;
	}

	private static void printTowns(EntityManager em) {
		List<Town> resultList = em.createQuery("select t from Town as t").getResultList();
		resultList.forEach(town -> System.out.printf("Town name is: %s%n", town.getName()));
	}
}
