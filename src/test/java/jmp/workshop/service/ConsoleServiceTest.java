package jmp.workshop.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Map;

/**
 * Author: Bakhodirjon_Marupov
 * Date: 24/07/2022
 */
public class ConsoleServiceTest {

    private final static String TEMPLATE = "Hello #{name}, please check following link out: #{link}. " +
        "The deadline is #{deadline} EOD. Sincerely, #{team} team";
    private final static String PLACEHOLDERS = "name>Liam;link>https://www.test.com/;deadline>12.05.2022;team>Support";

    private ConsoleService consoleService = new ConsoleService();

    @Test
    public void readTemplate() {
        System.setIn(new ByteArrayInputStream(TEMPLATE.getBytes()));

        String actual = consoleService.readTemplate();

        assertEquals(TEMPLATE, actual);
    }

    @Test
    public void readPlaceholders() {
        Map<String, String> expectedPlaceholder = Map.of(
            "name", "Liam",
            "link", "https://www.test.com/",
            "deadline", "12.05.2022",
            "team", "Support");
        System.setIn(new ByteArrayInputStream(PLACEHOLDERS.getBytes()));

        Map<String, String> actualPlaceholders = consoleService.readPlaceholders();

        assertNotNull(actualPlaceholders);
        assertEquals(expectedPlaceholder.size(), actualPlaceholders.size());
        for (Map.Entry<String, String> entry : expectedPlaceholder.entrySet()) {
            String value = actualPlaceholders.get(entry.getKey());
            assertNotNull(value);
            assertEquals(entry.getValue(), value);
        }
    }
}