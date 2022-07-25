package jmp.workshop.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.condition.EnabledForJreRange;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Author: Bakhodirjon_Marupov
 * Date: 25/07/2022
 */
@ExtendWith(MockitoExtension.class)
class MessengerServiceTest {

    @Mock
    private ConsoleService consoleService;
    @Mock
    private FileService fileService;
    @Mock
    private TemplateService templateService;

    @InjectMocks
    private MessengerService messengerService;

    private final static String TEMPLATE = "Hello #{name}, please check following link out: #{link}. " +
        "The deadline is #{deadline} EOD. Sincerely, #{team} team";
    private final static String MESSAGE = "Hello Liam, please check following link out: https://www.test.com/. " +
        "The deadline is 12.05.2022 EOD. Sincerely, Support team";
    private final static Map<String, String> PLACEHOLDERS = Map.of(
        "name", "Liam",
        "link", "https://www.test.com/",
        "deadline", "12.05.2022",
        "team", "Support");
    private final static String INPUT_FILE_PATH = "testInput.txt";
    private final static String OUTPUT_FILE_PATH = "testOutput.txt";

    @Test
    public void start_EmptyParamsGiven_ShouldWorkWithConsole() {
        when(consoleService.readTemplate()).thenReturn(TEMPLATE);
        when(consoleService.readPlaceholders()).thenReturn(PLACEHOLDERS);
        when(templateService.prepareMessage(eq(TEMPLATE), eq(PLACEHOLDERS)))
            .thenReturn(MESSAGE);

        messengerService.start(new String[]{});
    }

    @Test
    public void start_withPartialMocking_EmptyParamsGiven_ShouldWorkWithConsole() {
        when(consoleService.readTemplate()).thenReturn(TEMPLATE);
        when(consoleService.readPlaceholders()).thenReturn(PLACEHOLDERS);
        when(templateService.prepareMessage(eq(TEMPLATE), eq(PLACEHOLDERS)))
            .thenCallRealMethod();

        messengerService.start(new String[]{});
    }

    @Test
    public void start_TwoPathsGiven_ShouldWorkWithFile() {
        when(fileService.readTemplate(eq(INPUT_FILE_PATH))).thenReturn(TEMPLATE);
        when(consoleService.readPlaceholders()).thenReturn(PLACEHOLDERS);
        when(templateService.prepareMessage(eq(TEMPLATE), eq(PLACEHOLDERS)))
            .thenReturn(MESSAGE);
        doNothing().when(fileService).writeMessage(eq(OUTPUT_FILE_PATH), eq(MESSAGE));

        messengerService.start(new String[]{INPUT_FILE_PATH, OUTPUT_FILE_PATH});
    }

    @ParameterizedTest
    @MethodSource
    @EnabledForJreRange(min = JRE.JAVA_8, max = JRE.JAVA_13)
    public void start_VariousInvalidParams_ShouldThrowException(String[] paths) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> messengerService.start(null));

        assertEquals("Invalid parameters", exception.getMessage());
    }

    public static Stream<Arguments> start_VariousInvalidParams_ShouldThrowException() {
        return Stream.of(
            Arguments.of(
                new String[]{"firstTestPath", "secondTestPath", "thirdTestPath"},
                new String[]{"firstTestPath"},
                new String[0],
                null));
    }

    @TestFactory
    @EnabledForJreRange(min = JRE.JAVA_8, max = JRE.JAVA_13)
    public Collection<DynamicTest> start_DynamicTests() {
        return Arrays.asList(
            DynamicTest.dynamicTest("with 3 arguments", () -> {
                Exception exception = assertThrows(IllegalArgumentException.class, () -> messengerService.start(
                    new String[]{"firstTestPath", "secondTestPath", "thirdTestPath"}
                ));
                assertEquals("Invalid parameters", exception.getMessage());
            }),
            DynamicTest.dynamicTest("with 1 arguments", () -> {
                Exception exception = assertThrows(IllegalArgumentException.class, () -> messengerService.start(
                    new String[]{"firstTestPath"}
                ));
                assertEquals("Invalid parameters", exception.getMessage());
            }),
            DynamicTest.dynamicTest("with null", () -> {
                Exception exception = assertThrows(IllegalArgumentException.class, () -> messengerService.start(
                    null
                ));
                assertEquals("Invalid parameters", exception.getMessage());
            })
        );
    }

}