package com.bridgelabz.addressbooksystem;

import java.util.ArrayList;
/*
 * @author: Sadanand Pandey
 */
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AddressBookDirectory implements AddressBookDirectoryInterface {
	public AddressBook addressBook;
	Scanner sc = new Scanner(System.in);
	Map<String, AddressBook> addressBookDirectory = new HashMap<String, AddressBook>();

	/*
	 * creating switch case to call each method by option
	 */
	public void displayMenu() {
		boolean moreChanges = true;
		do {
			System.out.println("\nChoose the operation on the Directory you want to perform");
			System.out.println(
					"1.Add an Address Book\n2.Edit Existing Address Book\n3.Display Address book Directory\n4.Search Person By Regionn\n5.View People By Region\n6.Count People By Region\n7.Exit Address book System");

			switch (sc.nextInt()) {
			case 1:
				addAddressBook();
				break;
			case 2:
				editAddressBook();
				break;
			case 3:
				displayDirectoryContents();
				break;
			case 4:
				System.out.println("Enter \n1.Search By City\n2.Search By State");
				int searChoice = sc.nextInt();
				if (searChoice == 1)
					searchByCity();
				else
					searchByState();
				break;
			case 5:
				System.out.println("Enter \n1.Display By City\n2.Display By State");
				int displayChoice = sc.nextInt();
				if (displayChoice == 1)
					displayPeopleByRegion(AddressBook.city);
				else
					displayPeopleByRegion(AddressBook.state);
				break;
			case 6:
				System.out.println("Enter \n1.Display By City\n2.Display By State");
				int countChoice = sc.nextInt();
				if(countChoice==1)
					countPeopleByRegion(AddressBook.city);
				else 
					countPeopleByRegion(AddressBook.state);
				break;
			default:
				moreChanges = false;
				System.out.println("Exiting Address Book Directory !");
			}

		} while (moreChanges);

	}

	/*
	 * Adding new address book to by checking existing book is available or not
	 */
	public void addAddressBook() {

		System.out.println("Enter the name of the Address Book you want to add");
		String bookNameToAdd = sc.next();

		if (addressBookDirectory.containsKey(bookNameToAdd)) {
			System.out.println("Book Name Already Exists");
		} else {
			AddressBook addressBook = new AddressBook();
			addressBook.setAddressBookName(bookNameToAdd);
			addressBookDirectory.put(bookNameToAdd, addressBook);
			System.out.println("Address book added successfully.");
			addressBook.displayMenu();
		}
	}

	/*
	 * in this method.. calling existing address book and editing them.
	 */
	public void editAddressBook() {

		System.out.println("Enter the Name of the Address Book which you want to edit:");
		String addressBookToEdit = sc.next();

		if (addressBookDirectory.containsKey(addressBookToEdit)) {
			addressBook = addressBookDirectory.get(addressBookToEdit);
			addressBook.displayMenu();
		} else {
			System.out.println("Book Does Not Exist");
		}
	}

	/*
	 * searching person by his/her city name
	 */
	public void searchByCity() {

		System.out.println("Enter the name of the City where the Person resides : ");
		String cityName = sc.next();
		System.out.println("Enter the name of the Person : ");
		String personName = sc.next();

		for (AddressBook addressBook : addressBookDirectory.values()) {
			ArrayList<Contact> contactList = addressBook.getContact();
			contactList.stream()
					.filter(person -> person.getFirstName().equals(personName)
							&& person.getAddress().getCity().equals(cityName))
					.forEach(person -> System.out.println(person));
		}
	}

	/*
	 * searching person by his/her state name
	 */
	public void searchByState() {

		System.out.println("Enter the name of the State where the Person resides : ");
		String stateName = sc.next();
		System.out.println("Enter the name of the Person : ");
		String personName = sc.next();

		for (AddressBook addressBook : addressBookDirectory.values()) {
			ArrayList<Contact> contactList = ((AddressBook) addressBook).getContact();
			contactList.stream()
					.filter(person -> person.getFirstName().equals(personName)
							&& person.getAddress().getState().equals(stateName))
					.forEach(person -> System.out.println(person));
		}

	}

	/*
	 * Displaying person by his/her city or state
	 */
	public void displayPeopleByRegion(HashMap<String, ArrayList<Contact>> listToDisplay) {

		System.out.println("Enter the name of the region :");
		String regionName = sc.next();

		listToDisplay.values().stream()
				.map(region -> region.stream()
						.filter(person -> person.getAddress().getState().equals(regionName)
								|| person.getAddress().getCity().equals(regionName)))
				.forEach(person -> person.forEach(personDetails -> System.out.println(personDetails)));
	}

	/*
	 * counting persons by City or State
	 */
	public void countPeopleByRegion(HashMap<String, ArrayList<Contact>> listToDisplay) {

		System.out.println("Enter the name of the region :");
		String regionName = sc.next();

		long countPeople = listToDisplay.values().stream()
				.map(region -> region.stream().filter(person -> person.getAddress().getState().equals(regionName)
						|| person.getAddress().getCity().equals(regionName)))
				.count();

		System.out.println("Number of People residing in " + regionName + " are: " + countPeople + "\n");
	}

	/*
	 * in this method displaying addressBook name
	 */
	public void displayDirectoryContents() {

		System.out.println("----- Contents of the Address Book Directory-----");
		for (String eachBookName : addressBookDirectory.keySet()) {

			System.out.println(eachBookName);
		}
		System.out.println("-----------------------------------------");
	}
}
