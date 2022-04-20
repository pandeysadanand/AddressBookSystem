package com.bridgelabz.addressbooksystem;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AddressBook implements AddressBookInterface {
	Scanner sc = new Scanner(System.in);
	public Map<String, Contact> contactList = new HashMap<String, Contact>();
	public static HashMap<String, ArrayList<Contact>> city = new HashMap<String, ArrayList<Contact>>();
	public static HashMap<String, ArrayList<Contact>> state = new HashMap<String, ArrayList<Contact>>();
	public String addressBookName;
	public boolean isPresent = false;

	public String getAddressBookName() {
		return addressBookName;
	}

	public void setAddressBookName(String addressBookName) {
		this.addressBookName = addressBookName;
	}

	public ArrayList<Contact> getContact() {
		return new ArrayList<Contact>(contactList.values());
	}

	@Override
	public void operation() {
		boolean changes = true;
		do {
			System.out.println("\n Select the operation you want to perform : ");
			System.out.println(
					"1.Add To Address Book\n2.Edit Existing Entry\n3.Delete Contact\n4.Display Address book\n5.Display Sorted Address Book By Custom Criteria\n6.Write To File\n7.Read From File\n8.Exit Address book System");
			switch (sc.nextInt()) {
			case 1:
				addContact();
				break;
			case 2:
				editPerson();
				break;
			case 3:
				deletePerson();
				break;
			case 4:
				displayContents();
				break;
			case 5:
				System.out.println("In which order you want to display addressbook ?");
				System.out.println("1.FirstName\n2.City\n3.State\n4.Zip Code");
				int sortingOrder = sc.nextInt();
				sortAddressBook(sortingOrder);
				break;
			case 6:
				writeToAddressBookFile();
				System.out.println("Written To file");
				break;
			case 7:
				readDataFromFile();
				break;
			default:
				changes = false;
				System.out.println("Exiting Address Book: " + this.getAddressBookName() + " !");
			}
		} while (changes);
	}

	@Override
	public void addContact() {
		Contact person = new Contact();
		Address address = new Address();

		System.out.println("Enter first name: ");
		String firstName = sc.next();
		contactList.entrySet().stream().forEach(entry -> {
			if (entry.getKey().equals(firstName.toLowerCase())) {
				System.out.println("Contact already exist.");
				isPresent = true;
				//return;
			}
		});

		if (isPresent == false) {
			System.out.println("Enter last name : ");
			String lastName = sc.next();
			System.out.println("Enter phone number :");
			long phoneNumber = sc.nextLong();
			System.out.println("Enter email: ");
			String email = sc.next();
			System.out.println("Enter city :");
			String city = sc.next();
			System.out.println("enter state: ");
			String state = sc.next();
			System.out.println("Enter zip code: ");
			long zip = sc.nextLong();

			person.setFirstName(firstName);
			person.setLastName(lastName);
			person.setPhoneNumber(phoneNumber);
			person.setEmail(email);
			address.setCity(city);
			address.setState(state);
			address.setZip(zip);
			person.setAddress(address);
			addPersonToCity(person);
			addPersonToState(person);
			contactList.put(firstName.toLowerCase(), person);
		}
	}

	@Override
	public void displayContents() {
		System.out.println("----- Contents of the Address Book " + this.getAddressBookName() + " -----");
		for (String eachContact : contactList.keySet()) {
			Contact contact = contactList.get(eachContact);
			System.out.println(contact);
		}
		System.out.println("----------------------------------------------------");
	}

	@Override
	public void editPerson() {
		Contact person = new Contact();
		System.out.println("Enter first name : ");
		String firstName = sc.next();
		if (contactList.containsKey(firstName)) {
			person = contactList.get(firstName);
			Address address = person.getAddress();
			System.out.println("Choose you want to change : ");
			System.out.println("1.Last Name\n2.Phone Number\n3.Email\n4.City\n5.State\n6.ZipCode");
			switch (sc.nextInt()) {
			case 1:

				System.out.println("Enter the correct Last Name :");
				String lastName = sc.next();
				person.setLastName(lastName);
				break;
			case 2:
				System.out.println("Enter the correct Phone Number :");
				long phoneNumber = sc.nextLong();
				person.setPhoneNumber(phoneNumber);
				break;
			case 3:
				System.out.println("Enter the correct Email Address :");
				String email = sc.next();
				person.setEmail(email);
				break;
			case 4:
				System.out.println("Enter the correct City :");
				String city = sc.next();
				address.setCity(city);
				break;
			case 5:
				System.out.println("Enter the correct State :");
				String state = sc.next();
				address.setState(state);
				break;
			case 6:
				System.out.println("Enter the correct ZipCode :");
				long zip = sc.nextLong();
				address.setZip(zip);
				break;
			}

		} else {
			System.out.println(" Name does not exist.");
		}
	}

	@Override
	public void deletePerson() {
		System.out.println("Enter first name of person to delete : ");
		String firstName = sc.next();
		if (contactList.containsKey(firstName)) {
			contactList.remove(firstName);
			System.out.println("Successfully deleted.");
		} else {
			System.out.println("Contact not found.");
		}

	}

	@Override
	public void addPersonToCity(Contact contact) {
		if (city.containsKey(contact.getAddress().getCity())) {
			city.get(contact.getAddress().getCity()).add(contact);
		} else {
			ArrayList<Contact> cityList = new ArrayList<Contact>();
			cityList.add(contact);
			city.put(contact.getAddress().getCity(), cityList);
		}
	}

	@Override
	public void addPersonToState(Contact contact) {
		if (state.containsKey(contact.getAddress().getState())) {
			state.get(contact.getAddress().getState()).add(contact);
		} else {
			ArrayList<Contact> stateList = new ArrayList<Contact>();
			stateList.add(contact);
			state.put(contact.getAddress().getState(), stateList);
		}
	}

	@Override
	public void printSortedList(List<Contact> sortedContactList) {
		System.out.println("------ Sorted Address Book " + this.getAddressBookName() + " ------");
		Iterator iterator = sortedContactList.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
			System.out.println();
		}
		System.out.println("-----------------------------------------");

	}

	@Override
	public void sortAddressBook(int sortingChoice) {
		List<Contact> sortedContact;
		switch (sortingChoice) {
		case 1:
			sortedContact = contactList.values().stream().sorted(
					(firstPerson, secondPerson) -> firstPerson.getFirstName().compareTo(secondPerson.getFirstName()))
					.collect(Collectors.toList());
			printSortedList(sortedContact);
			break;
		case 2:
			sortedContact = contactList.values().stream().sorted((firstperson, secondperson) -> firstperson.getAddress()
					.getCity().compareTo(secondperson.getAddress().getCity())).collect(Collectors.toList());
			printSortedList(sortedContact);
			break;
		case 3:
			sortedContact = contactList.values().stream().sorted((firstperson, secondperson) -> firstperson.getAddress()
					.getState().compareTo(secondperson.getAddress().getState())).collect(Collectors.toList());
			printSortedList(sortedContact);
			break;
		case 4:
			sortedContact = contactList.values().stream()
					.sorted((firstperson, secondperson) -> Long.valueOf(firstperson.getAddress().getZip())
							.compareTo(Long.valueOf(secondperson.getAddress().getZip())))
					.collect(Collectors.toList());
			printSortedList(sortedContact);
			break;
		}
	}

	@Override
	public void writeToAddressBookFile() {
		String bookName = this.getAddressBookName();
		String fileName = bookName + ".txt";

		StringBuffer addressBookBuffer = new StringBuffer();
		contactList.values().stream().forEach(contact -> {
			String personDataString = contact.toString().concat("\n");
			addressBookBuffer.append(personDataString);
		});
		try {
			Files.write(Paths.get(fileName), addressBookBuffer.toString().getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<String> readDataFromFile() {
		List<String> addressBookList = new ArrayList<String>();
		String bookName = this.getAddressBookName();
		String fileName = bookName + ".txt";
		System.out.println("Reading from : " + fileName + "\n");
		try {
			Files.lines(new File(fileName).toPath()).map(line -> line.trim()).forEach(employeeDetails -> {
				System.out.println(employeeDetails);
				addressBookList.add(employeeDetails);
			});

		} catch (IOException e) {
			e.printStackTrace();
		}
		return addressBookList;
	}
}
