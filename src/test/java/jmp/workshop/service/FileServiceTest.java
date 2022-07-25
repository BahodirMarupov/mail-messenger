package jmp.workshop.service;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Author: Bakhodirjon_Marupov
 * Date: 25/07/2022
 */
class FileServiceTest {

    private Path inputFilePath;
    private Path outputFilePath;

    private final static String TEMPLATE = "Hello #{name}, please check following link out: #{link}. " +
        "The deadline is #{deadline} EOD. Sincerely, #{team} team";

    private final static String MESSAGE = "test_message";

    @TempDir
    private Path tempDir;

    public final FileService fileService = new FileService();

    @BeforeEach
    public void sutUp() throws IOException {
        inputFilePath = tempDir.resolve("inputFile.txt");
        outputFilePath = tempDir.resolve("outputFile.txt");
        Files.writeString(inputFilePath, TEMPLATE);
    }

    @Test
    public void shouldReadTemplate() {
        String actualTemplate = fileService.readTemplate(inputFilePath.toString());
        assertEquals(TEMPLATE, actualTemplate);
    }

    @Test
    public void readTemplate_InvalidPath_ShouldThrowException() {
        Exception exception = assertThrows(RuntimeException.class,
            () -> fileService.readTemplate(""));

        assertEquals("Exception when reading from file", exception.getMessage());
    }

    @Test
    public void shouldWriteMessage() throws IOException {
        fileService.writeMessage(outputFilePath.toString(), MESSAGE);
        assertEquals(MESSAGE, Files.readString(outputFilePath));
    }

    @Test
    public void writeMessage_InvalidPath_ShouldThrowException() {
        Exception exception = assertThrows(RuntimeException.class,
            () -> fileService.writeMessage("", MESSAGE));

        assertEquals("Exception when writing to file", exception.getMessage());
    }

}