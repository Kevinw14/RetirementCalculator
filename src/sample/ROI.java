/**
 * ROI class encapsulates data used to calculate return on investments
 * from 0% - 14% and stores those calculations and shows those returns with
 * age to show your returns over the years until you reach 72.
 *
 * @author Kevin Wood
 * @version 1.0
 */
package sample;

import java.io.Serializable;

public class ROI implements Serializable {

    private final Integer[] investmentReturns;
    private final int annualContribution;

    /**
     * Takes in information needed to accurately calculate the return on investments for
     * 0% - 14%.
     *
     * If it's their first year every column will be how much they're starting with (retirementSavings).
     * Every row after that will take in return on investment from the previous year to calculate
     * the return for this year.
     *
     * @param lastYearReturn Last years return on investment
     * @param age The age you will be and the return on investments for that age.
     * @param annualContribution The amount of money that you invest every year
     * @param retirementSavings How much you already have saved before beginning.
     */
    public ROI(ROI lastYearReturn, int age, int annualContribution, int retirementSavings) {
        this.annualContribution = annualContribution;

        int COLUMN_SIZE = 16;
        Integer[] investmentReturns = new Integer[COLUMN_SIZE];

        investmentReturns[0] = age;

        if (lastYearReturn == null) {
            for (int i = 1; i < COLUMN_SIZE; i++) {
                investmentReturns[i] = retirementSavings;
            }
        } else {
            for (int i = 1; i < COLUMN_SIZE; i++) {
                investmentReturns[i] = calculateReturn(i, lastYearReturn.investmentReturns[i]);
            }
        }

        this.investmentReturns = investmentReturns;
    }

    /**
     * Formula used to calculate how much money you will make
     * at a certain interest rate percentage from the contribution
     * made this year and the amount you made last year under the same interest rate.
     *
     * @param percentRate The interest rate that is used
     * @param yearBeforeReturn The return on investment value from the year before
     * @return An int from the calulations from the interest rate and the returns from the year before
     */
    private int calculateReturn(int percentRate, int yearBeforeReturn) {
        double convertedPercent = (double)percentRate / 100;
        return (int)((yearBeforeReturn + annualContribution) * (1 + convertedPercent));
    }

    public Integer[] getInvestmentReturns() {
        return investmentReturns;
    }
}
