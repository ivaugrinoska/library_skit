package mk.ukim.finki.library_vp.model.exceptions;

public class InvalidUserCredentialsException extends RuntimeException{
    public InvalidUserCredentialsException() {
        super("Invalid credentials");
    }
}
