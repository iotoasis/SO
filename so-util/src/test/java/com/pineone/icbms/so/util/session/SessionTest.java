package com.pineone.icbms.so.util.session;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

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

    @Test
    public void name() throws Exception {

        DateFormat df = DateFormat.getDateInstance(DateFormat.LONG, Locale.KOREA);
        Calendar cal = Calendar.getInstance(Locale.KOREA);
        System.out.println(df.format(cal.getTime()));

        Date today = new Date();
        System.out.println ( today );

        Calendar calendar = Calendar.getInstance();
        Date currentTime = calendar.getTime();
        System.out.println(currentTime);
    }
}
