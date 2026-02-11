package org.example;

public class PhoneBook {

    private String name;
    private int number;

    public PhoneBook(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public PhoneBook() {

    }

    public int add(String name, int number) {
        this.name = name;
        this.number = number;
        return 1;
    }

    public Object findByNumber(int number) {
        if (number == this.number) {
            return this.name;
        }
        return null;
    }

    public Integer findByName(String name) {
        if (name.equals(this.name)) {
            return this.number;
        }
        return null;
    }

}
