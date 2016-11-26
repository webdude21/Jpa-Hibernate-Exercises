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

		em.getTransaction().begin();

		Query query = em.createQuery("select t from	Town as t where t.name like '%ary'");

		List<Town> towns = query.setMaxResults(5).getResultList();

		towns.forEach(x -> System.out.printf("Town name is: %s%n", x.getName()));

		em.close();
		emf.close();
	}
}
