package com.bridgelabz.addressbooksystem;

import java.util.Scanner;

/*
 * Creating contact properties
 * @author: Sadanand Pandey
 */
public class AddressBook {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.println("=====================================================================");
		System.out.println("-----------------Welcome to address book Program --------------------");
		System.out.println("=====================================================================");
		System.out.println();
		Address address = new Address();
		System.out.println("Enter first name: ");
		address.setFirstName(sc.next());
		System.out.println("Enter last name: ");
		address.setLastName(sc.next());
		System.out.println("Enter email: ");
		address.setEmail(sc.next());
		System.out.println("Enter phone number: ");
		address.setPhoneNumber(sc.nextLong());
		System.out.println("Enter city: ");
		address.setCity(sc.next());
		System.out.println("Enter state: ");
		address.setState(sc.next());
		System.out.println("Enter zip code : ");
		address.setZip(sc.nextLong());
		
		System.out.println(address.toString());
	}
}