package com.hospital.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "diagnoses")
public class Diagnose extends BaseEntity {

	@Column(name = "name")
	private String name;

	@ManyToOne
	@JoinColumn(name = "patient_id", referencedColumnName = "id")
	private Patient patient;

	@OneToMany(mappedBy = "diagnose", targetEntity = Comment.class)
	private List<Comment> comments;

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Comment> getComments() {
		return this.comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Patient getPatient() {
		return this.patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}
}
