package contacts;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ContactServiceTest {

    private ContactService service;
    private Contact contact;

    @BeforeEach
    public void initService() {
        service = new ContactService();
        contact = new Contact("Z001", "Anna", "Brian", "1246559870", "1900 Mordor Field");
        service.addContact(contact);
    }

    @Test
    public void testAddContactWithDuplicateId() {
        Contact duplicate = new Contact("Z001", "Elsa", "Tikal", "1323123193", "4 Witcher Road");
        assertThrows(IllegalArgumentException.class, () -> service.addContact(duplicate));
    }

    @Test
    public void testDeleteExistingContact() {
        service.deleteContact("Z001");
        assertThrows(IllegalArgumentException.class, () -> service.deleteContact("Z001"));
    }

    @Test
    public void testUpdateMultipleFields() {
        service.updateFirstName("Z001", "Claudia");
        service.updateLastName("Z001", "Fenir");
        service.updatePhone("Z001", "2139498678");
        service.updateAddress("Z001", "268 Misty Drive");

        assertEquals("Claudia", contact.getFirstName());
        assertEquals("Fenir", contact.getLastName());
        assertEquals("2139498678", contact.getPhone());
        assertEquals("268 Misty Drive", contact.getAddress());
    }

    @Test
    public void testUpdateNonexistentContact() {
        assertThrows(IllegalArgumentException.class, () -> service.updateFirstName("XYZ123", "NewName"));
    }
}
