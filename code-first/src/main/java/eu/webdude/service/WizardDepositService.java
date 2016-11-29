package eu.webdude.service;

import eu.webdude.contract.WizardDepositService;
import eu.webdude.model.WizardDeposit;
import eu.webdude.repository.WizardDepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
class WizardDepositServiceImpl implements WizardDepositService {

	private final WizardDepositRepository wizardDepositRepository;

	@Autowired
	WizardDepositServiceImpl(WizardDepositRepository wizardDepositRepository) {
		this.wizardDepositRepository = wizardDepositRepository;
	}

	@Override
	public void persist(WizardDeposit wizardDeposit) {
		this.wizardDepositRepository.saveAndFlush(wizardDeposit);
	}

	@Override
	public List<WizardDeposit> findAll() {
		return Collections.unmodifiableList(this.wizardDepositRepository.findAll());
	}
}
