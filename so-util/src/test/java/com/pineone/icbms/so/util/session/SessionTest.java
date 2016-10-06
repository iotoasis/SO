package com.pineone.icbms.so.util.session;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by existmaster on 2016. 10. 5..
 */
public class SessionTest {

    private Session firstSession;
    private Session secondSession;

    @Before
    public void setUp() throws Exception {
        firstSession = new DefaultSession();
        secondSession = new DefaultSession();
    }

    @Test
    public void uniqueUUIDNewSession(){
        Assert.assertNotEquals(firstSession.getId(), secondSession.getId());
    }

    @Test
    public void sessionInputAndOutput(){
        firstSession.insertSessionData("foo","bar");
        Assert.assertEquals(firstSession.findSessionData("foo"), "bar");
    }

}
