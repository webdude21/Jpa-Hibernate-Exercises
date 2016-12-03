package com.hospital.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "visitations")
public class Visitation extends BaseEntity {

	@Column(name = "date")
	private Date date;

	@ManyToOne
	@JoinColumn(name = "patient_id", referencedColumnName = "id")
	private Patient patient;

	@OneToMany(mappedBy = "visitation", targetEntity = Comment.class)
	private List<Comment> comments;

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Patient getPatient() {
		return this.patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public List<Comment> getComments() {
		return this.comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
}
