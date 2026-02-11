import org.example.PhoneBook;
import org.junit.Test;

import java.util.List;

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

    @Test
    public void findByName() {
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.add("Jim", 8800);
        Integer number = phoneBook.findByName("Jim");
        assertEquals(Integer.valueOf(8800), number);
    }

    @Test
    public void printAllNamesReturnsOneName() {
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.add("Jim", 8800);
        List<String> names = phoneBook.printAllNames();
        assertEquals(List.of("Jim"), names);
    }
}
