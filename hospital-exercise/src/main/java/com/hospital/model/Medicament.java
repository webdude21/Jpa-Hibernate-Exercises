package com.hospital.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "medicaments")
public class Medicament extends BaseEntity {

	@Column(name = "name")
	private String name;

	@ManyToMany
	@JoinTable(name = "medicaments_patients",
			joinColumns = @JoinColumn(name = "medicament_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "patient_id", referencedColumnName = "id"))
	private Set<Patient> patients;

	public Medicament() {
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Patient> getPatients() {
		return this.patients;
	}

	public void setPatients(Set<Patient> patients) {
		this.patients = patients;
	}
}
