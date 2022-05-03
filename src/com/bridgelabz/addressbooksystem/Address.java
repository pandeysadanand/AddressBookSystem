package com.bridgelabz.addressbooksystem;

/*
 *  This program is for taking details of a person and storing
 *  them in address book
 *  @author : Sadanand Pandey
 */
public class Address {
	private String city, state;
	private long zip;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public long getZip() {
		return zip;
	}

	public void setZip(long zip) {
		this.zip = zip;
	}

	@Override
	public String toString() {
		return "city=" + city + ", state=" + state + ", zip=" + zip;
	}
}
