package com.bridgelabz.addressbooksystem;
import java.util.ArrayList;
import java.util.HashMap;

public interface AddressBookDirectoryInterface {

	public void addAddressBook();

	public void displayMenu();

	public void displayDirectoryContents();

	public void editAddressBook();

	public void searchByCity();

	public void searchByState();

	public void displayPeopleByRegion(HashMap<String, ArrayList<Contact>> listToDisplay);
}