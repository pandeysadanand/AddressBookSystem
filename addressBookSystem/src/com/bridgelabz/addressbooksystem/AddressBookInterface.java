package com.bridgelabz.addressbooksystem;

import java.util.List;

public interface AddressBookInterface {

	public void operation();

	public void addContact();

	public void displayContents();

	public void editPerson();

	public void addPersonToCity(Contact contact);

	public void addPersonToState(Contact contact);
	}