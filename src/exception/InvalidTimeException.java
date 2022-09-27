package exception;

public class InvalidTimeException extends Exception{
    public InvalidTimeException(){
        super("INVALID_TIME_FORMAT");
    }
}
