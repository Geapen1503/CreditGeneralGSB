package cgb.transfert.exception;

public class ExceptionInvalidIbanFormat extends ExceptionInvalideIBAN {
    public ExceptionInvalidIbanFormat(String message) {
        super("Format IBAN invalide: " + message);
    }
}
