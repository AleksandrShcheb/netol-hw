import org.example.PhoneBook;
import org.junit.Test;

import static org.junit.Assert.*;

public class PhoneBookTest {

    @Test
    public void returnOne() {
        PhoneBook phoneBook = new PhoneBook();
        int res = phoneBook.add("Jim", 8800);
        assertEquals(1, res);
    }
}
