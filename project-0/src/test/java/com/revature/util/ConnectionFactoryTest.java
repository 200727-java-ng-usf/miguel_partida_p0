package com.revature.util;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;

public class ConnectionFactoryTest extends TestCase {

    private ConnectionFactory sut;

    @Before
    public void setUp(){
        sut = ConnectionFactory.getInstance();
    }
    @After
    public void tearDown(){
        sut = null;
    }
@Test
    public void testGetInstanceandMakeSureItIsSingelton() {
        ConnectionFactory c1 = ConnectionFactory.getInstance();
        ConnectionFactory c2 = ConnectionFactory.getInstance();

        assertSame(c1,c2);
    }
@Test
    public void testGetConnection() {
        Connection conn = ConnectionFactory.getInstance().getConnection();
                assertNotNull(conn);

    }
}