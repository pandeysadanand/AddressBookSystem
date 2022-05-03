package com.bridgelabz.addressbooksystem;

import java.util.List;

public interface AddressBookInterface {

    public void displayMenu();

    public void addContact();

    public void displayContents();

    public void editPerson();

    public void deletePerson();

   public void addPersonToCity(Contact contact);

    public void addPersonToState(Contact contact);
}
