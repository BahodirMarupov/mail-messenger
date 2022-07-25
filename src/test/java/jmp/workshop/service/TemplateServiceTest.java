package jmp.workshop.service;

import static org.junit.jupiter.api.Assertions.*;
import jmp.workshop.exception.PlaceholderNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: Bakhodirjon_Marupov
 * Date: 25/07/2022
 */
class TemplateServiceTest {

    private final static String TEMPLATE = "Hello #{name}, please check following link out: #{link}. " +
        "The deadline is #{deadline} EOD. Sincerely, #{team} team";
    private final static String MESSAGE = "Hello Liam, please check following link out: https://www.test.com/. " +
        "The deadline is 12.05.2022 EOD. Sincerely, Support team";
    private Map<String, String> placeholders;

    private final TemplateService templateService = new TemplateService();

    @BeforeEach
    public void setUp() {
        placeholders = new HashMap<>();
        placeholders.put("name", "Liam");
        placeholders.put("link", "https://www.test.com/");
        placeholders.put("deadline", "12.05.2022");
        placeholders.put("team", "Support");
    }

    @Test
    public void shouldPrepareMessage() {
        String actualMessage = templateService.prepareMessage(TEMPLATE, placeholders);

        assertEquals(MESSAGE, actualMessage);
    }

    @Test
    public void shouldPrepareMessage_PlaceholdersMoreThenRequired() {
        placeholders.put("phone", "+998992223334");
        String actualMessage = templateService.prepareMessage(TEMPLATE, placeholders);

        assertEquals(MESSAGE, actualMessage);
    }

    @Test
    public void prepareMessage_NamePlaceholderNotProvided_ShouldThrowException() {
        placeholders.remove("name");
        Exception exception = assertThrows(PlaceholderNotFoundException.class,
            () -> templateService.prepareMessage(TEMPLATE, placeholders));

        assertEquals("Placeholder not found", exception.getMessage());
    }
}