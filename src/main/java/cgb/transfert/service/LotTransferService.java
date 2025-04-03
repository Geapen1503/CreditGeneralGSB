package cgb.transfert.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cgb.transfert.entity.Account;
import cgb.transfert.entity.LotTransfer;
import cgb.transfert.entity.Transfer;
import cgb.transfert.repository.AccountRepository;
import cgb.transfert.repository.LotTransferRepository;
import cgb.transfert.repository.TransferRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class LotTransferService {
    private final LotTransferRepository lotTransferRepository;
    
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransferRepository transferRepository;

    
    public LotTransferService(LotTransferRepository lotTransferRepository, TransferRepository transferRepository) {
        this.lotTransferRepository = lotTransferRepository;
        this.transferRepository = transferRepository;
    }

    @Transactional
    public LotTransfer createLotTransfer(String refLot, String sourceAccountNumber, String description, List<LotTransfer> listLotTransfer) {
        Account sourceAccount = accountRepository.findById(sourceAccountNumber)
                .orElseThrow(() -> new RuntimeException("Source account not found"));

        LotTransfer lotTransfer = new LotTransfer();
        lotTransfer.setNumeroDeLot(refLot);
        lotTransfer.setSourceAccount(sourceAccountNumber);
        lotTransfer.setDescriptionLot(description);

        List<Transfer> transfers = new ArrayList<>();

        for (LotTransfer lt : listLotTransfer) {
            String destinationIban = lt.getSourceAccount(); 
            Double amount = 100.0; 
            
            Account destinationAccount = accountRepository.findById(destinationIban)
                    .orElseThrow(() -> new RuntimeException("Destination account not found"));

            if (sourceAccount.getSolde().compareTo(amount) < 0) {
                throw new RuntimeException("Insufficient funds for transfer to " + destinationIban);
            }

            sourceAccount.setSolde(sourceAccount.getSolde() - amount);
            destinationAccount.setSolde(destinationAccount.getSolde() + amount);

            accountRepository.save(sourceAccount);
            accountRepository.save(destinationAccount);

            Transfer transfer = new Transfer();
            transfer.setSourceAccountNumber(sourceAccountNumber);
            transfer.setDestinationAccountNumber(destinationIban);
            transfer.setAmount(amount);
            transfer.setTransferDate(LocalDate.now());
            transfer.setDescription("Transfert lié au lot " + refLot);
            transfer.setLotTransfer(lotTransfer);

            transfers.add(transfer);
        }

        lotTransfer.setTransfers(transfers);
        return lotTransferRepository.save(lotTransfer);
    }

   
    public List<LotTransfer> getAllLots() {
        return lotTransferRepository.findAll();
    }
}



