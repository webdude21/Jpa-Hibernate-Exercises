package eu.webdude;

import eu.webdude.contract.WizardDepositService;
import eu.webdude.model.WizardDeposit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class Startup implements CommandLineRunner {

	private final WizardDepositService wizardDepositService;

	@Autowired
	public Startup(WizardDepositService wizardDepositService) {
		this.wizardDepositService = wizardDepositService;
	}

	@Override
	public void run(String... strings) throws Exception {
		System.out.println("Started");

		WizardDeposit wz = new WizardDeposit();
		wz.setFirstName("Teo");
		wz.setLastName("Dimitrov");
		wz.setAge(26);
		wz.setMagicWandCreator("Tatko");
		wz.setMagicWandSize(26);
		wz.setDepositAmount(0.0);
		wz.setDepositCharge(0.0);
		wz.setDepositExpirationDate(new Date());
		Calendar calendar = Calendar.getInstance();
		calendar.set(2010, 11, 21);
		wz.setDepositStartDate(calendar.getTime());
		wz.setDepositExpired(true);

		WizardDeposit newTeo = new WizardDeposit();
		newTeo.setFirstName("newTeo");
		newTeo.setLastName("Dimitrov");
		newTeo.setAge(26);
		newTeo.setMagicWandCreator("Tatko");
		newTeo.setMagicWandSize(26);
		newTeo.setDepositAmount(0.0);
		newTeo.setDepositCharge(0.0);
		newTeo.setDepositExpirationDate(new Date());
		calendar = Calendar.getInstance();
		calendar.set(2010, 11, 21);
		newTeo.setDepositStartDate(calendar.getTime());
		newTeo.setDepositExpired(false);

		this.wizardDepositService.persist(wz);
		this.wizardDepositService.persist(newTeo);

		List<WizardDeposit> resutl = this.wizardDepositService.findAll();
		for (WizardDeposit wizardDeposit : resutl) {
			System.out.println(wizardDeposit.getFirstName());
		}
	}
}
