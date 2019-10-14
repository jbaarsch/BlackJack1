package Exceptions;

// The purpose of this exception is so that I have my own private 'Oh no!' option.
public class InvalidValueException extends RuntimeException {

    public InvalidValueException(String m) {
        super(m);
    }


}
