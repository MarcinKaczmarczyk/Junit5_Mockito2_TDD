package pl.exercises.account;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AccountServiceTest {
    @Test
    void getAllActiveService(){
        //given
        List<Account>accounts=prepareAccountData();
        AccountRepository accountRepository= mock(AccountRepository.class);
        AccountService accountService=new AccountService(accountRepository);
        when(accountRepository.getAllAccounts()).thenReturn(accounts);
        //when
        List<Account> accountList= accountService.getAllActiveAccounts();

        //then
        assertThat(accountList,hasSize(2));
    }
    private List<Account> prepareAccountData(){
        Account account1=new Account(new Address("street1","4/4"));
        Account account2=new Account(new Address("street2","3/3"));
        Account account3=new Account();
        return Arrays.asList(account1,account2,account3);
    }
}
