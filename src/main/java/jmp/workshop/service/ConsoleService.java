package jmp.workshop.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Author: Bakhodirjon_Marupov
 * Date: 24/07/2022
 */
public class ConsoleService {

    public String readTemplate() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public Map<String, String> readPlaceholders() {
        Map<String, String> placeholders = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (input != null && input.length() > 0) {
            for (String s : input.split(";")) {
                String[] elements = s.split(">");
                if (elements.length == 2) {
                    placeholders.put(elements[0], elements[1]);
                }
            }
        }
        return placeholders;
    }

}
