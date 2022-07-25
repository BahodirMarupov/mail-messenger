package jmp.workshop;

import jmp.workshop.service.ConsoleService;
import jmp.workshop.service.FileService;
import jmp.workshop.service.MessengerService;
import jmp.workshop.service.TemplateService;

/**
 * Author: Bakhodirjon_Marupov
 * Date: 24/07/2022
 */
public class Main {

    public static void main(String[] args) {
        ConsoleService consoleService = new ConsoleService();
        FileService fileService = new FileService();
        TemplateService templateService = new TemplateService();

        MessengerService messengerService = new MessengerService(consoleService, fileService, templateService);
        messengerService.start(args);
    }
}
