package cgb.transfert;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.math.BigInteger;
import java.util.regex.Pattern;


public class CGBIbanValidatorTest {

    @Test
    void testIbanLength() {
        String iban = IbanGenerator.generateValidIban();
        assertEquals(27, iban.length(), "L'IBAN doit avoir une longueur de 27 caractères.");
    }

    @Test
    void testIbanStartsWithFR() {
        String iban = IbanGenerator.generateValidIban();
        assertTrue(iban.startsWith("FR"), "L'IBAN doit commencer par 'FR'.");
    }

    @Test
    void testIbanCheckDigitsAreValid() {
        String iban = IbanGenerator.generateValidIban();
        String checkDigits = iban.substring(2, 4); 
        String bban = iban.substring(4); 

        String countryNumeric = "151800"; 
        String ibanNumeric = bban + countryNumeric;

        BigInteger ibanNumber = new BigInteger(ibanNumeric);
        int calculatedCheckDigits = 98 - ibanNumber.mod(BigInteger.valueOf(97)).intValue();

        assertEquals(String.format("%02d", calculatedCheckDigits), checkDigits, 
                "La clé de contrôle IBAN est invalide.");
    }

    @Test
    void testIbanBbanIsNumeric() {
        String iban = IbanGenerator.generateValidIban();
        String bban = iban.substring(4); 

        assertTrue(bban.matches("\\d{23}"), "Le BBAN doit être composé uniquement de chiffres.");
    }

    @Test
    void testIbanFormatIsValid() {
        String iban = IbanGenerator.generateValidIban();
        Pattern ibanPattern = Pattern.compile("^FR\\d{2}\\d{23}$");
        assertTrue(ibanPattern.matcher(iban).matches(), "Le format de l'IBAN est invalide.");
    }
}
