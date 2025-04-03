package cgb.transfert.controller;
 
 import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cgb.transfert.LotTransferRequest;
import cgb.transfert.TransferRequest;
import cgb.transfert.entity.LotTransfer;
import cgb.transfert.entity.Transfer;
import cgb.transfert.service.LotTransferService;
 
 import java.util.List;
 
 @RestController
 @RequestMapping("/lots")
 public class LotTransferController {
	 
	 
     private final LotTransferService lotTransferService;
 
     public LotTransferController(LotTransferService lotTransferService) {
         this.lotTransferService = lotTransferService;
     }
 
     @GetMapping
     public List<LotTransfer> getAllLots() {
         return lotTransferService.getAllLots();
     }
     
     @PostMapping("/createLotTransfer")
     public ResponseEntity<?> createLotTransfer(@RequestBody LotTransferRequest lotTransferRequest) {
    	 try {
    		 LotTransfer lotTransfer = lotTransferService.createLotTransfer(
    				 	lotTransferRequest.getRefLot(),
    				 	lotTransferRequest.getSourceAccount(),
    				 	lotTransferRequest.getDescriptionLot(),
    				 	lotTransferRequest.getListLotTransfer()    				 	
    				 );

    		 return ResponseEntity.ok(lotTransfer);
         } catch (RuntimeException e) {
             TransferResponse errorResponse = new TransferResponse("FAILURE", e.getMessage());
             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
         }
     }
 }
 
 
 
 