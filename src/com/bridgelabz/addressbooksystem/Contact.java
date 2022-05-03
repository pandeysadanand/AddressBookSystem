package com.bridgelabz.addressbooksystem;

public class Contact {
    private String firstName, lastName, email;
    private long phoneNumber;
    Address address;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Contact [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", phoneNumber="
                + phoneNumber + " , "+ address + "]";
    }
}
