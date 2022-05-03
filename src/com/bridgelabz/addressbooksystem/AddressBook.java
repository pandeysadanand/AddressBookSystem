package com.bridgelabz.addressbooksystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AddressBook implements AddressBookInterface {
    Scanner sc = new Scanner(System.in);
    public Map<String, Contact> contactList = new HashMap<String, Contact>();
    public static HashMap<String, ArrayList<Contact>> city = new HashMap<String, ArrayList<Contact>>();
    public static HashMap<String, ArrayList<Contact>> state = new HashMap<String, ArrayList<Contact>>();
    public String addressBookName;
    public boolean isPresent = false;

    public String getAddressBookName(){
        return addressBookName;
    }
    public void setAddressBookName(String addressBookName){
        this.addressBookName = addressBookName;
    }
    public ArrayList<Contact> getContact(){
        return new ArrayList<Contact>(contactList.values());
    }

    @Override
    public void displayMenu(){
        boolean change = true;
        do {
            System.out.println("\n Select the operation you want to perform : ");
            System.out.println(
                    "1.Add To Address Book\n2.Edit Existing Entry\n3.Delete Contact\n4.Display Address book\n5.Exit Address book System");
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
                default:
                    change = false;
                    System.out.println("Exiting Address Book: " + this.getAddressBookName() + " !");
            }
    }while (change);
}

@Override
public void addContact(){
    Contact person = new Contact();
    Address address = new Address();

		System.out.println("Enter first name: ");
        String firstName = sc.next();
		contactList.entrySet().stream().forEach(entry -> {
            if (entry.getKey().equals(firstName.toLowerCase())) {
                System.out.println("Contact already exist.");
                isPresent = true;
                return;
        }
    });
        if (isPresent == false){
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

}