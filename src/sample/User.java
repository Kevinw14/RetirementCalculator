package sample;

import java.io.Serializable;

/**
 * User class used to encapsulate data about their retirement.
 * Takes user's age, their amount already saved,
 * their yearly contribution, and target they want to hit before 72.
 *
 * User class will calculate the yearly return on investments
 * with the data provided until the age of 72.
 *
 * @author Kevin Wood
 * @version 1.0
 */
public class User implements Serializable {

    private final int age;
    private final int retirementSavings;
    private final int annualRetirementInvestment;
    private final int targetSavingForRetirement;
    private final ROI[] rois;

    /**
     * Takes in the data needed to initialize a User object then calculates
     * the year investment returns from the given data.
     *
     * @param age Current age of the user.
     * @param retirementSavings How much the user already has in their retirement savings.
     * @param annualRetirementInvestment How much the user is willing to invest every year.
     * @param targetSavingForRetirement How much the user wants in their account when they reach
     *                                  the retirement age of 72
     */
    public User(int age, int retirementSavings, int annualRetirementInvestment, int targetSavingForRetirement) {
        this.age = age;
        this.retirementSavings = retirementSavings;
        this.annualRetirementInvestment = annualRetirementInvestment;
        this.targetSavingForRetirement = targetSavingForRetirement;
        this.rois = calculateROIS();
    }

    /**
     * Creates n number of ROI's from their age until 72
     * and adds it to the users overall ROI's.
     *
     * @return An array of ROI's that show their return on investments until the age of 72
     */
    private ROI[] calculateROIS() {

        int lifetimeAccountAge = getLifetimeAccountAge();

        ROI[] lifetimeInvestmentReturns = new ROI[lifetimeAccountAge];

        for (int accountAge = 0; accountAge < lifetimeAccountAge; accountAge++) {

            int currentAge = age + accountAge;

            ROI roi;
            if (accountAge == 0) {
                roi = new ROI(null, currentAge, annualRetirementInvestment, retirementSavings);
            } else {
                roi = new ROI(lifetimeInvestmentReturns[accountAge - 1], currentAge, annualRetirementInvestment, retirementSavings);
            }
            lifetimeInvestmentReturns[accountAge] = roi;
        }

        return lifetimeInvestmentReturns;
    }

    /**
     * Returns an int that is the amount of time in years from the age of the user until the age of 72.
     * This int is then used to create n number ROI's needed to show their investment returns.
     *
     * @return An int of the amount of time in years until 72 from the user's given age.
     */
    private int getLifetimeAccountAge() {
        return 73 - age;
    }

    /**
     *Determines if the amount given from a return on investment is within 10% of the target goal
     * but less than the target.
     *
     * This is used to determine if the cell background should be yellow or white.
     *
     * @param investmentReturn The amount that was made that year.
     * @return returns true if the given number is within 10% the target goal.
     * Example
     * Target: $1,000,000
     * Within 10%: $900,000+
     */
    public boolean isWithinTen(Number investmentReturn) {
        double tenPercent = targetSavingForRetirement / 10.0;
        double bareMinimum = targetSavingForRetirement - tenPercent;

        return investmentReturn.intValue() >= bareMinimum && investmentReturn.intValue() < targetSavingForRetirement;
    }

    /**
     * Determines if the amount given from a return on investment is at the target goal or over.
     * This is used to determine if the cell background should be green or white.
     *
     * @param investmentReturn The amount that was made that year.
     * @return returns true if the given number is at or over the target goal.
     */
    
    public boolean isOver(Number investmentReturn) {
        return investmentReturn.intValue() >= targetSavingForRetirement;
    }
    public int getAge() { return age; }
    public int getRetirementSavings() { return retirementSavings; }
    public int getAnnualRetirementInvestment() { return annualRetirementInvestment; }
    public int getTargetSavingsForRetirement() { return targetSavingForRetirement; }
    public ROI[] getReturnOnInvestments() { return rois; }
}
