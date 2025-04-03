package cgb.transfert.exception;

public class ExceptionInvalidUnCheckableIban extends ExceptionInvalideIBAN {
    public ExceptionInvalidUnCheckableIban(String message) {
        super("IBAN non vérifiable: " + message);
    }
}
