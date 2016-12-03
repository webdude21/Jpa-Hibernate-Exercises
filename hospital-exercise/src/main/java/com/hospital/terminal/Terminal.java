package com.hospital.terminal;

import com.hospital.model.*;
import com.hospital.service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashSet;

@Component
public class Terminal implements CommandLineRunner {

	private final EntityService<Comment> commentService;

	private final EntityService<Diagnose> diagnoseService;

	private final EntityService<Medicament> medicamentService;

	private final EntityService<Patient> patientService;

	private final EntityService<Visitation> visitationService;

	@Autowired
	Terminal(EntityService<Comment> commentService, EntityService<Diagnose> diagnoseService,
			 EntityService<Medicament> medicamentService, EntityService<Patient> patientService,
			 EntityService<Visitation> visitationService) {

		this.commentService = commentService;
		this.diagnoseService = diagnoseService;
		this.medicamentService = medicamentService;
		this.patientService = patientService;
		this.visitationService = visitationService;
	}

	@Override
	public void run(String... strings) throws Exception {

		Patient patient = new Patient();
		patient.setFirstName("John");
		patient.setLastName("Smith");
		patient.setAddress("Sofia");
		patient.setEmail("john@em.com");
		patient.setMedicalInsurance(true);
		patient.setBirthDate(new Date());
		this.patientService.create(patient);

		Visitation visitation = new Visitation();
		visitation.setDate(new Date());
		visitation.setPatient(patient);
		this.visitationService.create(visitation);

		Diagnose diagnose = new Diagnose();
		diagnose.setName("Hrema");
		diagnose.setPatient(patient);
		this.diagnoseService.create(diagnose);


		Comment comment = new Comment();
		comment.setText("Cool comment");
		comment.setVisitation(visitation);
		this.commentService.create(comment);

		Medicament medicament = new Medicament();
		medicament.setName("Aspirin");
		medicament.setPatients(new HashSet<>());
		medicament.getPatients().add(patient);
		this.medicamentService.create(medicament);

		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		String line;
		while (!(line = bufferedReader.readLine()).equals("Stop")) {
			long id = Long.parseLong(line);
			Patient p = this.patientService.retrieve(id);
			System.out.println(p.toString());
		}
	}
}
