package com.bridgelabz.addressbooksystem;

import java.util.Scanner;

/*
 * Creating contact properties and adding new contact to address book
 * @author: Sadanand Pandey
 */
public class AddressBookMain {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.println("=====================================================================");
		System.out.println("-----------------Welcome to address book Program --------------------");
		System.out.println("=====================================================================");
		System.out.println();
		AddressBookDirectory addressBookDirectory = new AddressBookDirectory();
		addressBookDirectory.operationDirectory();
		System.out.println("Operation successful.");
		
	}
}