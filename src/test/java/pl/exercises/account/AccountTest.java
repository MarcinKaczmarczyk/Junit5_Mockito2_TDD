package pl.exercises.account;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumingThat;


class AccountTest {

    @Test
    void newlyCreatedAccountShouldNotBeActive() {
        Account newAccount = new Account();
        assertFalse(newAccount.isActive(), "check if new account is not active");
        //hamcrest
//        assertThat(newAccount.isActive(), equalTo(false));
//        assertThat(newAccount.isActive(), is(false));
        //assertJ
//        assertThat(newAccount.isActive()).isFalse();
    }

    @Test
    void activatedAccountShouldHaveActiveFlagSet() {
        //given
        Account newAccount = new Account();
        //when
        newAccount.activate();
        //then
        assertTrue(newAccount.isActive());
        //hamcrest
//        assertThat(newAccount.isActive(), equalTo(true));
        //assertJ
//        assertThat(newAccount.isActive()).isTrue();

    }

    @Test
    void newlyCreatedAccountShouldNotHaveDeliveryAddressSet() {
        //given
        Account account = new Account();
        //when
        Address address = account.getDefaultDeliveryAddress();
        //then
        assertNull(address);
        //hamcrest
//        assertThat(address, nullValue());
        //assertJ
//        assertThat(address).isNull();
    }

    @Test
    void defaultDeliveryAddressShouldNotBeNullAfterBeingSet() {
        //given
        Address address = new Address("krakowska", "12c");
        Account account = new Account();
        account.setDefaultDeliveryAddress(address);
        //when
        Address defaultAddress = account.getDefaultDeliveryAddress();
        //then
        assertNotNull(defaultAddress);
        //hamcrest
//        assertThat(defaultAddress, notNullValue());
//        assertThat(defaultAddress, is(notNullValue()));
        //assertJ
//        assertThat(defaultAddress).isNotNull();
    }

    @Test
    void newAccountWithNotNullAddressShouldBeActive() {
        //given
        Address address = new Address("zielińskiego", "43/4");
        Account account = new Account(address);
        assumingThat(address != null, () -> {
            assertTrue(account.isActive());
        });
    }

}
