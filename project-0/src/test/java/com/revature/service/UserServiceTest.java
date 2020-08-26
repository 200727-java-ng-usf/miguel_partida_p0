package com.revature.service;

import com.revature.models.Account;
import com.revature.repos.AccountRepository;
import com.revature.repos.UserRepository;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Null;

import java.io.IOException;
import java.io.InputStream;

public class UserServiceTest extends TestCase {
    private UserServices sut;
    InputStream incontent;

    private UserRepository mockrepo = Mockito.mock(UserRepository.class);
    private AccountRepository mockARepo = Mockito.mock((AccountRepository.class));
    @Before
    public void setup(){
        sut = new UserServices(mockrepo);
    }
    Account testaccount = new Account(1,"checking", 1.0d);

    @After
    public void tearDown(){
sut = null;
    }

//@Test(expected = NullPointerException.class)
//public void testFundAccount() throws NullPointerException {
//        String accout_name = "checking";
//        Double balance = 1.0d;
//
//
//    sut.fundAccount(accout_name,balance);
//
//}
}
