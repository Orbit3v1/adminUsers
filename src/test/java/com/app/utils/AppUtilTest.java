package com.app.utils;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class AppUtilTest {

    @Test
    public void isNumericWithNumber(){
        String input = "12345";
        boolean result = AppUtil.isNumeric(input);
        assertTrue(result);
    }

    @Test
    public void isNumericWithText1(){
        String input = "12345a";
        boolean result = AppUtil.isNumeric(input);
        assertFalse(result);
    }

    @Test
    public void isNumericWithText2(){
        String input = "s5-a";
        boolean result = AppUtil.isNumeric(input);
        assertFalse(result);
    }

    @Test
    public void trimTimeAM(){
        Calendar inputC = new GregorianCalendar(2016, Calendar.MAY, 17, 10, 5, 1);
        Date input = inputC.getTime();

        Calendar expectedC = new GregorianCalendar(2016, Calendar.MAY, 17);
        Date expected = expectedC.getTime();

        Date result = AppUtil.trimTime(input);
        assertEquals(result, expected);
    }

    @Test
    public void trimTimePM(){
        Calendar inputC = new GregorianCalendar(2016, Calendar.MAY, 17, 22, 5, 1);
        Date input = inputC.getTime();

        Calendar expectedC = new GregorianCalendar(2016, Calendar.MAY, 17);
        Date expected = expectedC.getTime();

        Date result = AppUtil.trimTime(input);
        assertEquals(result, expected);
    }

    @Test
    public void isInRangeNullBounds(){
        Calendar inputC = new GregorianCalendar(2016, Calendar.MAY, 17, 22, 5, 1);
        Date input = inputC.getTime();

        Date begin = null;
        Date end = null;

        boolean result = AppUtil.isInRange(input, begin, end);

        assertTrue(result);
    }

    @Test
    public void isInRangeNullValue(){
        Date input = null;

        Calendar beginC = new GregorianCalendar(2016, Calendar.MAY, 18);
        Date begin = beginC.getTime();
        Calendar endC = new GregorianCalendar(2016, Calendar.MAY, 19);
        Date end = endC.getTime();

        boolean result = AppUtil.isInRange(input, begin, end);

        assertFalse(result);
    }

    @Test
    public void isInRangeNullAll(){
        Date input = null;

        Date begin = null;
        Date end = null;

        boolean result = AppUtil.isInRange(input, begin, end);

        assertTrue(result);
    }

    @Test
    public void isInRangeToInfTrue(){
        Calendar inputC = new GregorianCalendar(2016, Calendar.MAY, 17, 22, 5, 1);
        Date input = inputC.getTime();

        Calendar beginC = new GregorianCalendar(2016, Calendar.MAY, 17);
        Date begin = beginC.getTime();
        Date end = null;

        boolean result = AppUtil.isInRange(input, begin, end);

        assertTrue(result);
    }

    @Test
    public void isInRangeToInfFalse(){
        Calendar inputC = new GregorianCalendar(2016, Calendar.MAY, 17, 22, 5, 1);
        Date input = inputC.getTime();

        Calendar beginC = new GregorianCalendar(2016, Calendar.MAY, 19);
        Date begin = beginC.getTime();
        Date end = null;

        boolean result = AppUtil.isInRange(input, begin, end);

        assertFalse(result);
    }

    @Test
    public void isInRangeFromInfTrue(){
        Calendar inputC = new GregorianCalendar(2016, Calendar.MAY, 17, 22, 5, 1);
        Date input = inputC.getTime();

        Date begin = null;
        Calendar endC = new GregorianCalendar(2016, Calendar.MAY, 19);
        Date end = endC.getTime();

        boolean result = AppUtil.isInRange(input, begin, end);

        assertTrue(result);
    }

    @Test
    public void isInRangeFromInfFalse(){
        Calendar inputC = new GregorianCalendar(2016, Calendar.MAY, 17, 22, 5, 1);
        Date input = inputC.getTime();

        Date begin = null;
        Calendar endC = new GregorianCalendar(2016, Calendar.MAY, 16);
        Date end = endC.getTime();

        boolean result = AppUtil.isInRange(input, begin, end);

        assertFalse(result);
    }

    @Test
    public void isInRangeRangeTrue(){
        Calendar inputC = new GregorianCalendar(2016, Calendar.MAY, 17, 22, 5, 1);
        Date input = inputC.getTime();

        Calendar beginC = new GregorianCalendar(2016, Calendar.MAY, 16);
        Date begin = beginC.getTime();
        Calendar endC = new GregorianCalendar(2016, Calendar.MAY, 19);
        Date end = endC.getTime();

        boolean result = AppUtil.isInRange(input, begin, end);

        assertTrue(result);
    }

    @Test
    public void isInRangeRangeSameDay(){
        Calendar inputC = new GregorianCalendar(2016, Calendar.MAY, 17, 22, 5, 1);
        Date input = inputC.getTime();

        Calendar beginC = new GregorianCalendar(2016, Calendar.MAY, 17);
        Date begin = beginC.getTime();
        Calendar endC = new GregorianCalendar(2016, Calendar.MAY, 17);
        Date end = endC.getTime();

        boolean result = AppUtil.isInRange(input, begin, end);

        assertTrue(result);
    }

    @Test
    public void isInRangeRangeFalse(){
        Calendar inputC = new GregorianCalendar(2016, Calendar.MAY, 17, 22, 5, 1);
        Date input = inputC.getTime();

        Calendar beginC = new GregorianCalendar(2016, Calendar.MAY, 18);
        Date begin = beginC.getTime();
        Calendar endC = new GregorianCalendar(2016, Calendar.MAY, 19);
        Date end = endC.getTime();

        boolean result = AppUtil.isInRange(input, begin, end);

        assertFalse(result);
    }

}


