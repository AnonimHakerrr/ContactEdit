package com.example.contact;

import java.util.ArrayList;
import java.util.List;

public class ContactDB {
    private static List<Contact> contacts = new ArrayList<>();
    private static ContactDB instance;
    public static List<Contact> getContacts() {

        contacts.add(new Contact("John", "Doe", "1234567890"));
        contacts.add(new Contact("Jane", "Smith", "9876543210"));
        // Добавьте другие контакты
        return contacts;
    }


    public static List<Contact> getAllContacts() {
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
