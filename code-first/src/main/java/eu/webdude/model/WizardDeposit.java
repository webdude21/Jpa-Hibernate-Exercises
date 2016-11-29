package eu.webdude.model;

import javax.persistence.*;
import java.time.LocalDate;

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

	@Column(name = "age")
	private int age;

	@Column(name = "magic_wand_creator", length = 100)
	private String magicWandCreator;

	@Column(name = "magic_wand_size")
	private int magicWantSize;

	@Column(name = "deposit_group")
	private String depositGroup;

	@Column(name = "deposit_start_date")
	private LocalDate depositStartDate;

	@Column(name = "deposit_amount")
	private float depositAmount;

	@Column(name = "deposit_interest")
	private float depositInterest;

	@Column(name = "deposit_charge")
	private float depositCharge;

	@Column(name = "deposit_expiration_date")
	private float depositExpirationDate;

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
		this.age = age;
	}

	public String getMagicWandCreator() {
		return magicWandCreator;
	}

	public void setMagicWandCreator(String magicWandCreator) {
		this.magicWandCreator = magicWandCreator;
	}

	public int getMagicWantSize() {
		return magicWantSize;
	}

	public void setMagicWantSize(int magicWantSize) {
		this.magicWantSize = magicWantSize;
	}

	public String getDepositGroup() {
		return depositGroup;
	}

	public void setDepositGroup(String depositGroup) {
		this.depositGroup = depositGroup;
	}

	public LocalDate getDepositStartDate() {
		return depositStartDate;
	}

	public void setDepositStartDate(LocalDate depositStartDate) {
		this.depositStartDate = depositStartDate;
	}

	public float getDepositAmount() {
		return depositAmount;
	}

	public void setDepositAmount(float depositAmount) {
		this.depositAmount = depositAmount;
	}

	public float getDepositInterest() {
		return depositInterest;
	}

	public void setDepositInterest(float depositInterest) {
		this.depositInterest = depositInterest;
	}

	public float getDepositCharge() {
		return depositCharge;
	}

	public void setDepositCharge(float depositCharge) {
		this.depositCharge = depositCharge;
	}

	public float getDepositExpirationDate() {
		return depositExpirationDate;
	}

	public void setDepositExpirationDate(float depositExpirationDate) {
		this.depositExpirationDate = depositExpirationDate;
	}

	public boolean isDepositExpired() {
		return isDepositExpired;
	}

	public void setDepositExpired(boolean depositExpired) {
		isDepositExpired = depositExpired;
	}
}

