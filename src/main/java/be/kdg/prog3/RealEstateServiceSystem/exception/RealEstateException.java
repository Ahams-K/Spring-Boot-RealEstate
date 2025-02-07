package be.kdg.prog3.RealEstateServiceSystem.exception;

public class RealEstateException extends RuntimeException{
    public RealEstateException(String message) {
        super(message);
    }

    public RealEstateException(String message, Throwable cause) {
        super(message, cause);
    }
}
