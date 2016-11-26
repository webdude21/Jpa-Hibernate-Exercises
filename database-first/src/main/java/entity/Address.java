package entity;

import javax.persistence.*;

@Entity
@Table(name = "addresses")
public class Address {

	@Id
	@Column(name = "address_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int addressId;

	public int getAddressId() {
		return addressId;
	}

	public String getAddressText() {
		return addressText;
	}

	public void setAddressText(String addressText) {
		this.addressText = addressText;
	}

	public Town getTown() {
		return town;
	}

	public void setTown(Town town) {
		this.town = town;
	}

	@Column(name = "address_text")
	private String addressText;

	@ManyToOne
	@JoinColumn(name = "town_id")
	private Town town;
}
