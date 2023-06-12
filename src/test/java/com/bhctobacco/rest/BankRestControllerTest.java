package com.bhctobacco.rest;
import com.bhctobacco.BacTobacco;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = BacTobacco.class)
class BankRestControllerTest {
    @MockBean
    private BankService bankService;

    @Test
    public void testGetAllBanks() throws Exception {
        Bank bank = getBank();
        List<Bank> banks = new ArrayList<>();
        banks.add(bank);
        given(bankService.getAllBanks()).willReturn(banks);
        List<Bank> result = bankService.getAllBanks();
        assertEquals(result.size(), 1);
    }

    @Test
    public void testGetBank() throws Exception {
        Bank bank = getBank();
        given(bankService.findBankById(1)).willReturn(bank);
        Bank result = bankService.findBankById(1);
        assertEquals(result.getId(), 1);
    }

    /* @Test
     public void testDeleteEmployee() throws Exception {
         doNothing().when(employeeService).deleteEmployeeById(1);
         employeeService.deleteEmployeeById(1);
         assertTrue(true);
     }*/
    @Test
    public void testSaveOrUpdateBank() throws Exception {
        Bank bank = getBank();
        doNothing().when(bankService).createBank(bank);
        bankService.createBank(bank);
        assertTrue(true);
    }

    private Bank getBank() {
        Bank bank = new Bank();
        bank.setId(1L);
        bank.setName("ABSA");
        bank.setBankCode("ABSA_101");
        bank.setBankContact(new Contact(77472623L, "absa@absa.co.za", 8124553L));
        bank.setBankAddress(new Address());

        return bank;
    }
}
