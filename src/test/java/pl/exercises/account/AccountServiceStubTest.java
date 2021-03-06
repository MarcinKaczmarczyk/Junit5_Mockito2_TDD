package pl.exercises.account;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

class AccountServiceStubTest {

    @Test
    void getAllActiveService(){
        //given
        AccountRepositoryStub accountRepositoryStub= new AccountRepositoryStub();
        AccountService accountService=new AccountService(accountRepositoryStub);

        //when
        List<Account> accountList= accountService.getAllActiveAccounts();

        //then
        assertThat(accountList,hasSize(2));
    }

}