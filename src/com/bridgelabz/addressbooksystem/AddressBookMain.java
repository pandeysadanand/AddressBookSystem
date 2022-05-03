package com.bridgelabz.addressbooksystem;
/*
 * @author : Sadanand pandey
 * This program is for adding multiple addressBook and restricting to add duplicate
 * contacts to address book.
 */
public class AddressBookMain {

	public static void main(String[] args) {
		System.out.println("-----------------Welcome to address book Program --------------------");
		System.out.println();
		AddressBookDirectory addressBookDirectory = new AddressBookDirectory();
		addressBookDirectory.displayMenu();
		System.out.println("Operation successful.");
	}
}