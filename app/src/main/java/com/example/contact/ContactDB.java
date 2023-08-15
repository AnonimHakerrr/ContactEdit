package com.example.contact;

import java.util.ArrayList;
import java.util.List;

public class ContactDB {
    private static List<Contact> contacts  ;
    private static ContactDB instance;
    private ContactDB() {
        contacts = new ArrayList<>();

        contacts.add(new Contact("Іван", "Петров", "123456789"));
        contacts.add(new Contact("Марія", "Сидорова", "987654321"));

    }


    public List<Contact> getContacts() {
        return contacts;
    }

    public static void addContact(Contact contact) {
        contacts.add(contact);
    }

    public static void removeContact(int position) {
        if (position >= 0 && position < contacts.size()) {
            contacts.remove(position);
        }
    }
    public static synchronized ContactDB getInstance() {
        if (instance == null) {
            instance = new ContactDB();
        }
        return instance;
    }

    public void updateContact(int position, Contact updatedContact) {
        if (position >= 0 && position < contacts.size()) {
            contacts.set(position, updatedContact);
        }
    }


}
