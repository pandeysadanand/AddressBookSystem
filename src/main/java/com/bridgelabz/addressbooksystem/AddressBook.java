package com.bridgelabz.addressbooksystem;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

import netscape.javascript.JSObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
/*import java.text.ParseException;*/
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AddressBook {

    Scanner scanner = new Scanner(System.in);
    public Map<String, Contact> contactList = new HashMap<String, Contact>();
    public JSONArray contactList2 = new JSONArray();
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

    public void displayMenu() throws IOException, ParseException {
        boolean change = true;
        do {
            System.out.println("\n Select the operation you want to perform : ");
            System.out.println(
                    "1.Add To Address Book\n2.Edit Existing Entry\n3.Delete Contact\n4.Display Address book\n5.Sort address book." +
                            "\n6.Write to file.\n7. Read data from file\n8.Write to csv file "
                            + "\n9.Read data from file \n10.Write Data To JSONFile \n11.Read Data From JSON File\n12.Exit Address book System");
            switch (scanner.nextInt()) {
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
                    sortAddressBook();
                case 6:
                    writeDataToFile();
                    break;
                case 7:
                    readDataFromFile();
                    break;
                case 8:
                    writeDataToCSVFile();
                    break;
                case 9:
                    readDataFromCSVFile();
                    break;
                case 10:
                    writeDataToJSONFile();
                    break;
                case 11:
                    readDataFromJSONFile();
                    break;
                default:
                    change = false;
                    System.out.println("Exiting Address Book: " + this.getAddressBookName() + " !");
            }
        } while (change);
    }

    public void addContact() {
        Contact person = new Contact();

        System.out.println("Enter first name: ");
        String firstName = scanner.next();
        contactList.entrySet().stream().forEach(entry -> {
            if (entry.getKey().equals(firstName.toLowerCase())) {
                System.out.println("Contact already exist.");
                isPresent = true;
                return;
            }
        });
        if (isPresent == false) {
            System.out.println("Enter last name : ");
            String lastName = scanner.next();
            System.out.println("Enter phone number :");
            long phoneNumber = scanner.nextLong();
            System.out.println("Enter email: ");
            String email = scanner.next();
            System.out.println("Enter city :");
            String city = scanner.next();
            System.out.println("enter state: ");
            String state = scanner.next();
            System.out.println("Enter zip code: ");
            long zip = scanner.nextLong();

            person.setFirstName(firstName);
            person.setLastName(lastName);
            person.setPhoneNumber(phoneNumber);
            person.setEmail(email);
            person.setCity(city);
            person.setState(state);
            person.setZip(zip);
            addPersonToCity(person);
            addPersonToState(person);
            contactList.put(firstName.toLowerCase(), person);
            //contactList.add(person);
            contactList2.add(person);

        }
    }

    public void displayContents() {
        System.out.println("----- Contents of the Address Book " + this.getAddressBookName() + " -----");
        for (String eachContact : contactList.keySet()) {
            Contact contact = contactList.get(eachContact);
            System.out.println(contact);
        }
        System.out.println("----------------------------------------------------");
    }

    void editPerson() {
        Contact person = new Contact();
        System.out.println("Enter first name : ");
        String firstName = scanner.next();
        if (contactList.containsKey(firstName)) {
            person = contactList.get(firstName);
            System.out.println("Choose you want to change : ");
            System.out.println("1.Last Name\n2.Phone Number\n3.Email\n4.City\n5.State\n6.ZipCode");
            switch (scanner.nextInt()) {
                case 1:
                    System.out.println("Enter the correct Last Name :");
                    String lastName = scanner.next();
                    person.setLastName(lastName);
                    break;
                case 2:
                    System.out.println("Enter the correct Phone Number :");
                    long phoneNumber = scanner.nextLong();
                    person.setPhoneNumber(phoneNumber);
                    break;
                case 3:
                    System.out.println("Enter the correct Email Address :");
                    String email = scanner.next();
                    person.setEmail(email);
                    break;
                case 4:
                    System.out.println("Enter the correct City :");
                    String city = scanner.next();
                    person.setCity(city);
                    break;
                case 5:
                    System.out.println("Enter the correct State :");
                    String state = scanner.next();
                    person.setState(state);
                    break;
                case 6:
                    System.out.println("Enter the correct ZipCode :");
                    long zip = scanner.nextLong();
                    person.setZip(zip);
                    break;
            }

        } else {
            System.out.println(" Name does not exist.");
        }
    }

    public void deletePerson() {
        System.out.println("Enter first name of person to delete : ");
        String firstName = scanner.next();
        if (contactList.containsKey(firstName)) {
            contactList.remove(firstName);
            System.out.println("Successfully deleted.");
        } else {
            System.out.println("Contact not found.");
        }
    }

    public void addPersonToCity(Contact contact) {
        if (city.containsKey(contact.getCity())) {
            city.get(contact.getCity()).add(contact);
        } else {
            ArrayList<Contact> cityList = new ArrayList<Contact>();
            cityList.add(contact);
            city.put(contact.getCity(), cityList);
        }
    }

    public void addPersonToState(Contact contact) {
        if (state.containsKey(contact.getState())) {
            state.get(contact.getState()).add(contact);
        } else {
            ArrayList<Contact> stateList = new ArrayList<Contact>();
            stateList.add(contact);
            state.put(contact.getState(), stateList);
        }
    }

    public void printSortedList(List<Contact> sortedContactList) {
        System.out.println("------Sorted address book " + this.getAddressBookName() + "-------- ");
        Iterator iterator = sortedContactList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
            System.out.println();
        }
        System.out.println("=======================================");
    }

    public void sortAddressBook() {
        List<Contact> sortedContactList;
        System.out.println("On what basis you want to sort address book..");
        System.out.println("1. First name\n2. City\n3. State\n4. Zip Code");
        int sortingChoice = scanner.nextInt();
        switch (sortingChoice) {
            case 1:
                sortedContactList = contactList.values().stream().sorted(
                                (firstPerson, secondPerson) -> firstPerson.getFirstName().compareTo(secondPerson.getFirstName()))
                        .collect(Collectors.toList());
                printSortedList(sortedContactList);
                break;
            case 2:
                sortedContactList = contactList.values().stream()
                        .sorted((firstPerson, secondPerson) -> firstPerson.getCity().compareTo(secondPerson.getCity()))
                        .collect(Collectors.toList());
                printSortedList(sortedContactList);
                break;
            case 3:
                sortedContactList = contactList.values().stream()
                        .sorted((firstPerson, secondPerson) -> firstPerson.getState().compareTo(secondPerson.getState()))
                        .collect(Collectors.toList());
                printSortedList(sortedContactList);
                break;
            case 4:
                sortedContactList = contactList.values().stream().sorted((firstPerson, secondPerson) -> Long
                                .valueOf(firstPerson.getZip()).compareTo(Long.valueOf(secondPerson.getZip())))
                        .collect(Collectors.toList());
                printSortedList(sortedContactList);
                break;

        }
    }

    public void writeDataToFile() {
        String bookName = this.getAddressBookName();
        String fileName = bookName + ".txt";

        StringBuffer addressBookBuffer = new StringBuffer();
        contactList.values().stream().forEach(contact -> {
            String personData = contact.toString().concat("\n");
            addressBookBuffer.append(personData);
        });

        try {
            Files.write(Paths.get(fileName), addressBookBuffer.toString().getBytes(), StandardOpenOption.APPEND);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> readDataFromFile() {
        List<String> addressBookList = new ArrayList<String>();
        String bookName = this.getAddressBookName();
        String fileName = bookName + ".txt";
        System.out.println("Reading from file " + fileName + "\n");

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

    public void writeDataToCSVFile() {
        try (Writer writer = Files.newBufferedWriter(Paths.get("Contacts.csv"));) {
            StatefulBeanToCsvBuilder<Contact> contactsStatefulBeanToCsvBuilder = new StatefulBeanToCsvBuilder<>(writer);
            StatefulBeanToCsv<Contact> contactStatefulBeanToCsv = contactsStatefulBeanToCsvBuilder.build();
            contactStatefulBeanToCsv.write(getContact());
            System.out.println("Successfully written to CSV file");
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readDataFromCSVFile() {
        try (Reader reader = Files.newBufferedReader(Paths.get("Contact.csv"));
             CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();) {
            String[] record;
            while ((record = csvReader.readNext()) != null) {
                System.out.println("First name" + record[2]);
                System.out.println("Last name" + record[3]);
                System.out.println("Email" + record[4]);
                System.out.println("Phone number" + record[5]);
                System.out.println("City" + record[6]);
                System.out.println("State" + record[7]);
                System.out.println("Zip" + record[8]);
            }
            System.out.println("Successfully read from CSV file");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Write JSON file
    public void writeDataToJSONFile() {
        try (FileWriter file = new FileWriter("Contact.json")) {
            //We can write any JSONArray or JSONObject instance to the file
            file.write(contactList2.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void readDataFromJSONFile() throws IOException, ParseException {
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("Contact.json")) {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray contactList = (JSONArray) obj;
            System.out.println(contactList);

            //Iterate over employee array
            contactList.forEach(per -> parseEmployeeObject((JSONObject) per));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void parseEmployeeObject(JSONObject contact) {
        //Get contact object within list
        JSONObject employeeObject = (JSONObject) contact.get("contact");
        String firstName = (String) employeeObject.get("firstName");
        System.out.println(firstName);

        String lastName = (String) employeeObject.get("lastName");
        System.out.println(lastName);

        int phoneNumber = (Integer) employeeObject.get("phoneNumber");
        System.out.println(phoneNumber);

        String email = (String) employeeObject.get("email");
        System.out.println(lastName);

        String city = (String) employeeObject.get("city");
        System.out.println(city);

        String state = (String) employeeObject.get("state");
        System.out.println(lastName);

        int zip = (Integer) employeeObject.get("zip");
        System.out.println(zip);
    }
}

