package test;

import org.junit.jupiter.api.Test;
import phonebook.PhoneBook;

import static org.junit.jupiter.api.Assertions.*;

class PhoneBookTest {
    PhoneBook phoneBook = new PhoneBook();

    @Test
    void addNewPair() {
        assertTrue(phoneBook.addNewPair("Test", "+359888888888"),
                "The phone number is incorrect!");
    }

    @Test
    void deletePair() {
        phoneBook.addNewPair("Test", "+359888888888");
        assertTrue(phoneBook.deletePair("Test"), "The name you want to delete is not in phonebook list");
    }

    @Test
    void accessPhoneNumber() {
        phoneBook.addNewPair("Test", "+359888888888");
        assertNotEquals(phoneBook.accessPhoneNumber("Test"), null);
    }

    @Test
    void readFromFile() {
        assertTrue(phoneBook.readFromFile("phoneBookTest.txt"), "Check the file name or file path.");
    }
}