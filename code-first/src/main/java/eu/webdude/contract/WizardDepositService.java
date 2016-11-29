package eu.webdude.contract;

import eu.webdude.model.WizardDeposit;

import java.util.List;

public interface WizardDepositService {

	void persist(WizardDeposit wz);

	List<WizardDeposit> findAll();
}
