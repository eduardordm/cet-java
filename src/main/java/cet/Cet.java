package cet;

import java.util.Date;
import java.util.Calendar;

/**
 * Calculador do CET.
 *
 * O calculo abaixo esta de acordo com a formula da Resolucaoo CMN 3.517, de 2007
 * A formula e uma generalizacao e seu calculo por aproximacao deixa o programa
 * com entendimento mais simples
 *
 * Ha uma margem de erro insignificante no calculo.
 *
 */
public class Cet {

    private static final double CET_MAXVALUE = 10000.00;
    private static final double CET_PRECISION = 0.00001;
    private static final double DAY = 1000 * 60 * 60 * 24;

    /**
     * Calculo do custo efetivo total mensal.
     *
     * @param fc0 FC0, valor financiado.
     * @param fcj FCj, valor da parcela fixa
     * @param n N, numero de parcelas mensais
     * @param d0 D0 data do contrato (liberacao de recursos)
     * @param dj0 DJ0 data da liberacao da primeira parcela
     * @return Custo Efetivo Total (CET)
     */
    public static double mensal(double fc0, double fcj, int n, Date d0, Date dj0) {

        double cet = 0.0;

        while(true) {

            double total = 0.0;

            for(int j = 0; j < n; j++) {
                total += fcj / Math.pow(1.0 + cet, j+1);
            }

            cet += CET_PRECISION;

            if(cet >= CET_MAXVALUE) {
                return -1.0;
            }
            if(total - fc0 <= 0) {
                break;
            }
            else {
                cet *= total / fc0;
            }
        }
        return cet * 100.0;
    }

    /**
     * Calculo do custo efetivo total anual.
     *
     * @param fc0 FC0, valor financiado.
     * @param fcj FCj, valor da parcela fixa
     * @param n N, numero de parcelas mensais
     * @param d0 D0 data do contrato (liberacao de recursos)
     * @param dj0 DJ0 data da liberacao da primeira parcela
     * @return Custo Efetivo Total (CET)
     */
    public static double anual(double fc0, double fcj, int n, Date d0, Date dj0) {
        Calendar c = Calendar.getInstance();

        double cet = 0.0;

        while(true) {

            double total = 0.0;

            for(int j = 0; j < n; j++) {
                Date dj = dj0;
                if(j != 0) {
                    c.setTime(dj0);
                    c.add(Calendar.MONTH, j);
                    dj = c.getTime();
                }
                double days = (dj.getTime() - d0.getTime()) / DAY;
                total += fcj / Math.pow(1.0 + cet, days/365.0);
            }

            cet += CET_PRECISION;

            if(cet >= CET_MAXVALUE) {
                return -1.0;
            }
            if(total - fc0 <= 0) {
                break;
            }
            else {
                cet *= total / fc0;
            }
        }
        return cet * 100.0;
    }

}
