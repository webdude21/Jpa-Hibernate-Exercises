import entity.Address;
import entity.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class Demo {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("university");
		EntityManager em = emf.createEntityManager();

		printTowns(em);

		createSomeObjects(em);

		em.getTransaction().commit();
		em.close();
		emf.close();
	}

	private static void createSomeObjects(EntityManager em) {
		em.persist(createBurgas(em));
	}

	private static Town createBurgas(EntityManager em) {
		Town burgas = new Town();

		for (int i = 0; i < 5; i++) {
			Address address = new Address();
			address.setTown(burgas);
			address.setAddressText(String.format("Bourgas street %d", i + 1));
			em.persist(address);
		}

		burgas.setName("Burgas");

		return burgas;
	}

	private static void printTowns(EntityManager em) {
		em.getTransaction().begin();
		Query query = em.createQuery("select t from	Town as t where t.name like '%ary'");
		List<Town> towns = query.setMaxResults(5).getResultList();
		towns.forEach(x -> System.out.printf("Town name is: %s%n", x.getName()));
	}
}
