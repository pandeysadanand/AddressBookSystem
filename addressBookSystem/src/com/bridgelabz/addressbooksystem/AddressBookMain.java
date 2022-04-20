package com.bridgelabz.addressbooksystem;

import java.util.Scanner;
/*
 * @Author: Sadanand pandey
 * this program is for writing to multiple address book
 */

public class AddressBookMain {

	public static void main(String[] args) {
		System.out.println("-----------------Welcome to address book Program --------------------");
		System.out.println();
		Scanner sc = new Scanner(System.in);
		AddressBookDirectory addressBookDirectory = new AddressBookDirectory();
		addressBookDirectory.operationDirectory();
		System.out.println("Operation successful.");
	}
}
