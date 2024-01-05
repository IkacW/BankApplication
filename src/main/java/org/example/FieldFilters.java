package org.example;

import javax.swing.*;

public class FieldFilters {
    static char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    static char[] f_numbers = "1234567890.".toCharArray();

    public static boolean checkNumber(char c) {
        for(char number : f_numbers) {
            if (c == number) {
                return true;
            }
        }

        return false;
    }

    public static boolean checkLetter(char c) {
        for(char character : chars) {
            if(character == Character.toLowerCase(c)) {
                return true;
            }
        }

        return false;
    }

    public static boolean checkField(JTextField field) {
        if(field.getText().equals(""))
            return true;
        return false;
    }
}
