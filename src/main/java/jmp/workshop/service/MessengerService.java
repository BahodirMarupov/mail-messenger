package jmp.workshop.service;

import java.util.Map;

/**
 * Author: Bakhodirjon_Marupov
 * Date: 24/07/2022
 */
public class MessengerService {

    private final ConsoleService consoleService;
    private final FileService fileService;
    private final TemplateService templateService;

    public MessengerService(ConsoleService consoleService, FileService fileService, TemplateService templateService) {
        this.consoleService = consoleService;
        this.fileService = fileService;
        this.templateService = templateService;
    }

    public void start(String[] paths) {
        if (paths != null) {
            switch (paths.length) {
                case 0:
                    tryWithConsole();
                    return;
                case 2:
                    tryWithFile(paths[0], paths[1]);
                    return;
            }
        }
        throw new IllegalArgumentException("Invalid parameters");
    }

    private void tryWithConsole() {
        String template = consoleService.readTemplate();
        Map<String, String> placeholders = consoleService.readPlaceholders();
        String message = templateService.prepareMessage(template, placeholders);
        System.out.println(message);
    }

    private void tryWithFile(String inputFilePath, String outputFilePath) {
        String template = fileService.readTemplate(inputFilePath);
        Map<String, String> placeholders = consoleService.readPlaceholders();
        String message = templateService.prepareMessage(template, placeholders);
        fileService.writeMessage(outputFilePath, message);
    }
}
