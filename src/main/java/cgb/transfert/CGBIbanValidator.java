package cgb.transfert;

import org.apache.commons.validator.routines.IBANValidator;


public class CGBIbanValidator {
	private static CGBIbanValidator instance;
   private static IBANValidator ibanValidator = IBANValidator.getInstance();
	
	private CGBIbanValidator() {}
	
	public static CGBIbanValidator getInstanceValidator() {
		if (instance == null) {
			instance = new CGBIbanValidator();
		}
		return instance;
	}
	
	
	public boolean isIbanStructureValide(String iban) {
		 return iban != null && iban.matches("^[A-Z]{2}\\d{2}[A-Z0-9]{14,34}$");
	}
	
	public boolean isIbanValide(String iban) {
		return ibanValidator.isValid(iban);
	}
	
	public String getCodePays(String iban) {
       if (isIbanStructureValide(iban)) {
           return iban.substring(0, 2);
       }
       return "IBAN non valide";
   }
	
	public String getChiffreControle(String iban) {
       if (isIbanStructureValide(iban)) {
           return iban.substring(2, 4);
       }
       return "IBAN non valide";
   }
	
	public String getBBAN(String iban) {
       if (isIbanStructureValide(iban)) {
           return iban.substring(4);
       }
       return "IBAN non valide";
   }
}
