package mk.ukim.finki.wp.lab.model.exception;

public class NotFoundInRepositoryException extends RuntimeException{
    public NotFoundInRepositoryException(String message) {
        super(message);
    }
}
