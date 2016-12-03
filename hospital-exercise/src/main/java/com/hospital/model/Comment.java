package com.hospital.model;

import javax.persistence.*;

@Entity
@Table(name = "comments")
public class Comment extends BaseEntity {

	@Column(name = "text", columnDefinition = "TEXT")
	private String text;

	@ManyToOne
	@JoinColumn(name = "visitation_id", referencedColumnName = "id")
	private Visitation visitation;

	@ManyToOne
	@JoinColumn(name = "diagnose_id", referencedColumnName = "id")
	private Diagnose diagnose;

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Visitation getVisitation() {
		return this.visitation;
	}

	public void setVisitation(Visitation visitation) {
		this.visitation = visitation;
	}

	public Diagnose getDiagnose() {
		return this.diagnose;
	}

	public void setDiagnose(Diagnose diagnose) {
		this.diagnose = diagnose;
	}
}
