package jmp.workshop.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Author: Bakhodirjon_Marupov
 * Date: 24/07/2022
 */
public class FileService {

    public String readTemplate(String inputFilePath) {
        try {
            return Files.readString(Path.of(inputFilePath));
        } catch (IOException e) {
            throw new RuntimeException("Exception when reading from file");
        }
    }

    public void writeMessage(String outputFilePath, String message) {
        try {
            Files.writeString(Path.of(outputFilePath), message);
        } catch (IOException e) {
            throw new RuntimeException("Exception when writing to file");
        }
    }
}
