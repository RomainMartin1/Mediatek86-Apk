package com.example.mediatek86formations.outils;

import junit.framework.TestCase;

import org.junit.Assert;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Classe testant les outils de conversions.
 * @author Romain
 */
public class MesOutilsTest extends TestCase {
    /**
     * Teste la méthode convertStringToDate prenant en paramètre une date au format String et
     * le pattern attendu au format String.
     */
    public void testConvertStringToDate() {
        String date = "10-05-2018 12:15:23";
        String expectedPattern = "dd-MM-yyyy HH:mm:ss";
        Date testDate = MesOutils.convertStringToDate(date, expectedPattern);

        //TEST OK
        Calendar calendar = new GregorianCalendar(2018, Calendar.MAY, 10, 12, 15, 23);
        Date dateTest2 = calendar.getTime();
        Assert.assertEquals(0, testDate.compareTo(dateTest2));

        //TEST PAS OK
        Calendar calendar2 = new GregorianCalendar(2015, Calendar.AUGUST, 10, 12, 15, 23);
        Date dateTest3 = calendar2.getTime();
        Assert.assertNotEquals(0, testDate.compareTo(dateTest3));
    }

    /**
     * Teste la méthode convertStringToDate prenant en paramètre une date au format String
     * (sous la forme "EEE MMM dd hh:mm:ss 'GMT+00:00' yyyy").
     */
    public void testTestConvertStringToDate() {
        String date = "Dim. Mai 08 03:34:45 GMT+00:00 2015";
        Date testDate = MesOutils.convertStringToDate(date);

        //TEST OK
        Calendar calendar = new GregorianCalendar(2015, Calendar.MAY, 8, 3, 34, 45);
        Date dateTest2 = calendar.getTime();
        Assert.assertEquals(0, testDate.compareTo(dateTest2));

        //TEST PAS OK
        Calendar calendar2 = new GregorianCalendar(2015, Calendar.AUGUST, 10, 12, 15, 23);
        Date dateTest3 = calendar2.getTime();
        Assert.assertNotEquals(0, testDate.compareTo(dateTest3));
    }

    /**
     * Teste la méthode convertDateToString.
     */
    public void testConvertDateToString() {
        Calendar calendar = new GregorianCalendar(2015, Calendar.MAY, 15, 3, 34, 45);
        String date = MesOutils.convertDateToString(calendar.getTime());

        Assert.assertEquals("15/05/2015", date);

        Assert.assertNotEquals("25/12/1995", date);
    }
}