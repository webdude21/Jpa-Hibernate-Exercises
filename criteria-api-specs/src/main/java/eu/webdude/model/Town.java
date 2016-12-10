package eu.webdude.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "towns")
public class Town {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "town_id")
	private int townId;

	private String name;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "address_id")
	private List<Address> addresses;

	public int getTownId() {
		return townId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}
}
