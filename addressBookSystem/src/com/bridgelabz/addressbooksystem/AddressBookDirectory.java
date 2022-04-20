package com.bridgelabz.addressbooksystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AddressBookDirectory implements AddressBookDirectoryInterface {

	public AddressBook addressBook;
	Scanner sc = new Scanner(System.in);
	Map<String, AddressBook> addressBookDirectory = new HashMap<String, AddressBook>();

	public void operationDirectory() {

		boolean moreChanges = true;
		do {

			System.out.println("\nChoose the operation on the Directory you want to perform");
			System.out.println(
					"1.Add an Address Book\n2.Edit Existing Address Book");

			switch (sc.nextInt()) {
			case 1:
				addAddressBook();
				break;
			case 2:
				editAddressBook();
				break;

			default:
				moreChanges = false;
				System.out.println("Exiting Address Book Directory !");
			}

		} while (moreChanges);
	}

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
			addressBook.operation();
		}

	}

	public void editAddressBook() {

		System.out.println("Enter the Name of the Address Book which you want to edit:");
		String addressBookToEdit = sc.next();

		if (addressBookDirectory.containsKey(addressBookToEdit)) {
			addressBook = addressBookDirectory.get(addressBookToEdit);
			addressBook.operation();
		} else {
			System.out.println("Book Does Not Exist");
		}
	}
}