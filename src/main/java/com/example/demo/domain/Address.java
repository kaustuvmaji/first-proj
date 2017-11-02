package com.example.demo.domain;

import java.io.Serializable;

import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "addresses")
public class Address implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1337518167651269728L;
	private String addressLine1;
	private String addressLine2;
	private String addressLine3;
	private String Street;
	private String city;
	private String district;
	private String country;
	private GeoJsonPoint location;

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getAddressLine3() {
		return addressLine3;
	}

	public void setAddressLine3(String addressLine3) {
		this.addressLine3 = addressLine3;
	}

	public String getStreet() {
		return Street;
	}

	public void setStreet(String street) {
		Street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public GeoJsonPoint getLocation() {
		return location;
	}

	public void setLocation(GeoJsonPoint location) {
		this.location = location;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Street == null) ? 0 : Street.hashCode());
		result = prime * result + ((addressLine1 == null) ? 0 : addressLine1.hashCode());
		result = prime * result + ((addressLine2 == null) ? 0 : addressLine2.hashCode());
		result = prime * result + ((addressLine3 == null) ? 0 : addressLine3.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((district == null) ? 0 : district.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		if (Street == null) {
			if (other.Street != null)
				return false;
		} else if (!Street.equals(other.Street))
			return false;
		if (addressLine1 == null) {
			if (other.addressLine1 != null)
				return false;
		} else if (!addressLine1.equals(other.addressLine1))
			return false;
		if (addressLine2 == null) {
			if (other.addressLine2 != null)
				return false;
		} else if (!addressLine2.equals(other.addressLine2))
			return false;
		if (addressLine3 == null) {
			if (other.addressLine3 != null)
				return false;
		} else if (!addressLine3.equals(other.addressLine3))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (district == null) {
			if (other.district != null)
				return false;
		} else if (!district.equals(other.district))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		return true;
	}

	public Address() {
	}

	public Address(String addressLine1, String addressLine2, String addressLine3, String street, String city,
			String district, String country, GeoJsonPoint location) {
		super();
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.addressLine3 = addressLine3;
		Street = street;
		this.city = city;
		this.district = district;
		this.country = country;
		this.location = location;
	}

	@Override
	public String toString() {
		return "Address [" + (addressLine1 != null ? "addressLine1=" + addressLine1 + ", " : "")
				+ (addressLine2 != null ? "addressLine2=" + addressLine2 + ", " : "")
				+ (addressLine3 != null ? "addressLine3=" + addressLine3 + ", " : "")
				+ (Street != null ? "Street=" + Street + ", " : "") + (city != null ? "city=" + city + ", " : "")
				+ (district != null ? "district=" + district + ", " : "")
				+ (country != null ? "country=" + country + ", " : "")
				+ (location != null ? "location=" + location : "") + "]";
	}

}
