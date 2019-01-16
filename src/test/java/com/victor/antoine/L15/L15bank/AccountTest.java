package com.victor.antoine.L15.L15bank;

import com.victor.antoine.L15.L15bank.model.Account;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.containsString;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountTest {
    
    @Test
    public void contextLoads() {
    }
    
    @Test
    public void  ibanGeneratorTest(){
        String countryCode = "FR76";
        String bankCode = "40712";
        String counterCode = "80364";
        int accountid = 58;
        String accountNumber = String.format("%011d", accountid);
        String iban = countryCode + " " + bankCode + " " + counterCode+ " " + accountNumber + " " + "46";
        assertThat(iban,containsString(Account.ibanGenerator(58)));
    }
    
}

