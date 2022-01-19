package pl.exercises.account;

import java.util.Arrays;
import java.util.List;

public class AccountRepositoryStub implements AccountRepository {
    @Override
    public List<Account> getAllAccounts() {
        Account account1=new Account(new Address("street1","4/4"));
        Account account2=new Account(new Address("street2","3/3"));
        Account account3=new Account();
        return Arrays.asList(account1,account2,account3);
    }
}
