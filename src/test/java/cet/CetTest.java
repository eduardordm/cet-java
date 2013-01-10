package cet;

import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import static org.junit.Assert.assertTrue;

public class CetTest {

    @Test
    public void testMensal() throws Exception {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        double cet = Cet.mensal(940.0, 205.73, 24, df.parse("01/01/2012"), df.parse("01/02/2012"));

        assertTrue(String.format("%.2f", cet).equals("21.69"));
    }

    @Test
    public void testAnual() throws Exception {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        double cet = Cet.anual(940.0, 205.73, 24, df.parse("01/01/2012"), df.parse("01/02/2012"));

        assertTrue(String.format("%.2f", cet).equals("955.13"));
    }
}
