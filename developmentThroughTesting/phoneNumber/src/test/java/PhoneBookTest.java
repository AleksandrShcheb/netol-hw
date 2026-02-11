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

    @Test
    public void findByNumber(){
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.add("Jim", 8800);
        String res = (String) phoneBook.findByNumber(8800);
        assertEquals("Jim", res);
    }
}
