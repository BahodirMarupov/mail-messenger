package jmp.workshop.exception;

/**
 * Author: Bakhodirjon_Marupov
 * Date: 25/07/2022
 */
public class PlaceholderNotFoundException extends RuntimeException {
    public PlaceholderNotFoundException(String message) {
        super(message);
    }
}
