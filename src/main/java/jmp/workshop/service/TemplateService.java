package jmp.workshop.service;

import jmp.workshop.exception.PlaceholderNotFoundException;

import java.util.Map;
import java.util.Optional;

/**
 * Author: Bakhodirjon_Marupov
 * Date: 24/07/2022
 */
public class TemplateService {

    public String prepareMessage(String template, Map<String, String> placeholders) {
        StringBuilder messageBuilder = new StringBuilder("");

        char[] chars = template.toCharArray();
        int length = chars.length;

        boolean started = false;
        int startPoint = -1;
        for (int i = 0; i < length; i++) {
            if (started) {
                if (chars[i] == '}') {
                    String key = template.substring(startPoint, i);
                    String value = Optional.ofNullable(placeholders.get(key))
                        .orElseThrow(() -> new PlaceholderNotFoundException("Placeholder not found"));
                    messageBuilder.append(value);
                    started = false;
                }
            } else {
                if (chars[i] == '#' && length > i + 1 && chars[i + 1] == '{') {
                    startPoint = i + 2;
                    started = true;
                } else {
                    messageBuilder.append(chars[i]);
                }
            }
        }
        return messageBuilder.toString();
    }
}
