package jmp.workshop.service;

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

    }

}
