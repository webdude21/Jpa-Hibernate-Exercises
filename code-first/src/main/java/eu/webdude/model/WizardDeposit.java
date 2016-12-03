package eu.webdude.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "wizard_deposits")
public class WizardDeposit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "first_name", length = 50)
	private String firstName;

	@Column(name = "last_name", length = 60, nullable = false)
	private String lastName;

	@Column(name = "notes", length = 1000)
	private String notes;

	@Column(name = "age", columnDefinition = "INT UNSIGNED")
	private int age;

	@Column(name = "magic_wand_creator", length = 100)
	private String magicWandCreator;

	@Column(name = "magic_wand_size")
	private int magicWandSize;

	@Column(name = "deposit_group")
	private String depositGroup;

	@Column(name = "deposit_start_date")
	private Date depositStartDate;

	@Column(name = "deposit_amount")
	private double depositAmount;

	@Column(name = "deposit_interest")
	private double depositInterest;

	@Column(name = "deposit_charge")
	private double depositCharge;

	@Column(name = "deposit_expiration_date")
	private Date depositExpirationDate;

	@Column(name = "is_deposit_expired")
	private boolean isDepositExpired;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		if (age < 0 || age > 120) {
			throw new IllegalArgumentException("Age must be between 0 and 120 years");
		}

		this.age = age;
	}

	public String getMagicWandCreator() {
		return magicWandCreator;
	}

	public void setMagicWandCreator(String magicWandCreator) {
		this.magicWandCreator = magicWandCreator;
	}

	public int getMagicWandSize() {
		return magicWandSize;
	}

	public void setMagicWandSize(int magicWandSize) {
		if (magicWandSize <= 0 || magicWandSize > 32_768) {
			throw new IllegalArgumentException("The magic wand size should be between 0 and 32768");
		}

		this.magicWandSize = magicWandSize;
	}

	public String getDepositGroup() {
		return depositGroup;
	}

	public void setDepositGroup(String depositGroup) {
		this.depositGroup = depositGroup;
	}

	public Date getDepositStartDate() {
		return depositStartDate;
	}

	public void setDepositStartDate(Date depositStartDate) {
		this.depositStartDate = depositStartDate;
	}

	public double getDepositAmount() {
		return depositAmount;
	}

	public void setDepositAmount(double depositAmount) {
		this.depositAmount = depositAmount;
	}

	public double getDepositInterest() {
		return depositInterest;
	}

	public void setDepositInterest(double depositInterest) {
		this.depositInterest = depositInterest;
	}

	public double getDepositCharge() {
		return depositCharge;
	}

	public void setDepositCharge(double depositCharge) {
		this.depositCharge = depositCharge;
	}

	public Date getDepositExpirationDate() {
		return depositExpirationDate;
	}

	public void setDepositExpirationDate(Date depositExpirationDate) {
		this.depositExpirationDate = depositExpirationDate;
	}

	public boolean isDepositExpired() {
		return isDepositExpired;
	}

	public void setDepositExpired(boolean depositExpired) {
		isDepositExpired = depositExpired;
	}
}

