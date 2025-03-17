package cgb.transfert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "cgb.transfert")
public class ServerTransferApp {

	public static void main(String[] args) {
		SpringApplication.run(ServerTransferApp.class, args);    	
		// TODO Auto-generated method stub	

		
		String ibanTest = "FR9511816487956180425047131";
		
		CGBIbanValidator cgbIbanValidator = CGBIbanValidator.getInstanceValidator();
		
		
		System.out.println(cgbIbanValidator.isIbanStructureValide(ibanTest)); 
		System.out.println(cgbIbanValidator.isIbanValide(ibanTest)); 
		System.out.println(cgbIbanValidator.getCodePays(ibanTest)); 
		System.out.println(cgbIbanValidator.getChiffreControle(ibanTest)); 
		System.out.println(cgbIbanValidator.getBBAN(ibanTest)); 
	}
}

