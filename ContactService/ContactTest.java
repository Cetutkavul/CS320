package contacts;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContactTest {

    @Test
    public void testCreateValidContact() {
        Contact contact = new Contact("C001", "Olivia", "Martinez", "2105551234", "1010 Riverwalk Blvd");
        assertEquals("C001", contact.getContactId());
        assertEquals("Olivia", contact.getFirstName());
        assertEquals("Martinez", contact.getLastName());
        assertEquals("2105551234", contact.getPhone());
        assertEquals("1010 Riverwalk Blvd", contact.getAddress());
    }

    @Test
    public void testContactIdTooLong() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("C123456789999", "Ethan", "Lopez", "8307779999", "204 Main Street");
        });
    }

    @Test
    public void testNullAddressThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("C005", "Sophia", "Nguyen", "5123334444", null);
        });
    }

    @Test
    public void testSetLastNameValidUpdate() {
        Contact contact = new Contact("C010", "Mason", "Clark", "4692228888", "900 Oak Ridge Drive");
        contact.setLastName("Walker");
        assertEquals("Walker", contact.getLastName());
    }

    @Test
    public void testSetPhoneInvalidLength() {
        Contact contact = new Contact("C015", "Isabella", "Kim", "2109998888", "550 Pinecrest Loop");
        assertThrows(IllegalArgumentException.class, () -> contact.setPhone("12345"));
    }
}
