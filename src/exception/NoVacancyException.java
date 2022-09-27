package exception;

public class NoVacancyException extends Exception{
    public NoVacancyException(){
        super("CARRIAGE_HAS_NO_VACANCY");
    }
}
