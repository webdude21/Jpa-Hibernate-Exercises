package com.hospital.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "patients")
public class Patient extends BaseEntity {

	@Column(name = "fist_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "address")
	private String address;

	@Column(name = "email")
	private String email;

	@Column(name = "date_of_birth")
	private Date birthDate;

	@Column(name = "picture", columnDefinition = "BLOB")
	private byte[] picture;

	@Column(name = "has_medical_insurance")
	private boolean hasMedicalInsurance;

	@OneToMany(mappedBy = "patient", targetEntity = Visitation.class)
	private Set<Visitation> visitations;

	@OneToMany(mappedBy = "patient", targetEntity = Diagnose.class)
	private Set<Diagnose> diagnoses;

	@ManyToMany(mappedBy = "patients", targetEntity = Medicament.class)
	private Set<Medicament> medicaments;

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public byte[] getPicture() {
		return this.picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	public Set<Visitation> getVisitations() {
		return this.visitations;
	}

	public void setVisitations(Set<Visitation> visitations) {
		this.visitations = visitations;
	}

	public Set<Diagnose> getDiagnoses() {
		return this.diagnoses;
	}

	public void setDiagnoses(Set<Diagnose> diagnoses) {
		this.diagnoses = diagnoses;
	}

	public Set<Medicament> getMedicaments() {
		return this.medicaments;
	}

	public void setMedicaments(Set<Medicament> medicaments) {
		this.medicaments = medicaments;
	}

	public boolean getMedicalInsurance() {
		return this.hasMedicalInsurance;
	}

	public void setMedicalInsurance(boolean hasMedicalInsurance) {
		this.hasMedicalInsurance = hasMedicalInsurance;
	}
}
