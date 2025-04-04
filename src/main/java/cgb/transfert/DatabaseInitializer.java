package cgb.transfert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import cgb.transfert.entity.Account;
import cgb.transfert.repository.AccountRepository;
import cgb.transfert.IbanGenerator;
import jakarta.annotation.PostConstruct;

import java.util.HashSet;
import java.util.Set;

@Component
public class DatabaseInitializer {

    @Autowired
    private AccountRepository accountRepository;

    private static final int IBAN_COUNT = 20; 
    @PostConstruct
    public void init() {
        insertGeneratedIbans(accountRepository);
    }

    private void insertGeneratedIbans(AccountRepository accountRepository) {
        Set<String> uniqueIbans = new HashSet<>();

        while (uniqueIbans.size() < IBAN_COUNT) {
            String newIban = IbanGenerator.generateValidIban();
            if (!accountRepository.existsByAccountNumber(newIban)) {
                uniqueIbans.add(newIban);
            }
        }

        for (String iban : uniqueIbans) {
            Account account = new Account();
            account.setAccountNumber(iban);
            account.setSolde(1000.00); 
            accountRepository.save(account);
        }

        System.out.println("20 IBAN générés et insérés dans la base.");
    }
}