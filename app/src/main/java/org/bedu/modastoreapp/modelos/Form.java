package org.bedu.modastoreapp.modelos;

import java.security.SecureRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Form {
    private static final SecureRandom random = new SecureRandom();
    private static final String DICTIONARY = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789._/";

    public static String passwordGenerator() {
        StringBuilder password = new StringBuilder();
        char dictionaryChar;
        int count = 0;
        while (count < 8) {
            int index = random.nextInt(DICTIONARY.length());
            dictionaryChar = DICTIONARY.charAt(index);
            if (!password.toString().contains(String.valueOf(dictionaryChar))){
                password.append(dictionaryChar);
                count ++;
            }
        }
        return password.toString();
    }

    public static boolean validateUsername(String username) {
        String regex = "^[a-zA-Z][[a-zA-Z]+[0-9]*]{5,}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(username);
        return matcher.matches();
    }

    public static boolean validateEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean validatePassword(String password) {
        String regex = "^[[a-zA-Z]+[0-9]*[._/]*]{8,}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
