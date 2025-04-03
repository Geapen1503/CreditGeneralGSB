package cgb.transfert;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import cgb.transfert.controller.TransferController;
import cgb.transfert.entity.Account;
import cgb.transfert.service.TransferService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(TransferController.class) 
public class TransferControllerTest {

    @Autowired
    private MockMvc mockMvc; 

    @MockBean
    private TransferService transferService; 

    @InjectMocks
    private TransferController transferController; 

    private ObjectMapper objectMapper = new ObjectMapper(); 

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(transferController).build();
    }

    @Test
    void testApiFonctionne() throws Exception {
        mockMvc.perform(get("/api/transfers/test"))
                .andExpect(status().isOk())
                .andExpect(content().string("L'API fonctionne !"));
    }

    @Test
    void testCreateTransfer_Success() throws Exception {
        
    }

	@Test
	public void testObtenirUtilisateur() throws Exception {
		int id = 1;
		mockMvc.perform(get("/test/{id}", id)).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
				.andExpect(content().string("Recu : " + id));
	}

	@Test
	public void testCreateTransfer() throws Exception {
	    String transferRequestJson = """
	        {
	            "sourceAccountNumber": "123456789",
	            "destinationAccountNumber": "987654321",
	            "amount": 100.0,
	            "transferDate": "2025-03-13",
	            "description": "Virement de test"
	        }
	        """;
	    String expectedResponseJson = """
	        {
	            "sourceAccountNumber": "123456789",
	            "destinationAccountNumber": "987654321",
	            "amount": 100.0,
	            "transferDate": "2025-03-13",
	            "description": "Virement de test"
	        }
	        """;
	    mockMvc.perform(post("/api/transfers")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(transferRequestJson))
	        .andExpect(status().isOk())
	        .andExpect(content().json(expectedResponseJson));
	}

    
    @Test
    void testGetAllAccounts_Success() throws Exception {
        List<Account> accounts = Arrays.asList(new Account(), new Account());

        when(transferService.getAllAccounts()).thenReturn(accounts);

        mockMvc.perform(get("/api/transfers/accounts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }
}
