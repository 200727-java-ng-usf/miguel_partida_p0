package com.revature.service;

import com.revature.repos.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.mockito.Mockito;

public class UserServiceTest {
    private UserServices sut;

    private UserRepository mockrepo = Mockito.mock(UserRepository.class);
    @Before
    public void setup(){
        sut = new UserServices(mockrepo);
    }

    @After
    public void tearDown(){

    }
}
