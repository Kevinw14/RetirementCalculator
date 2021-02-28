package sample;

import javafx.application.Application;

/**
 * Retirement Calculator application that takes input from the user
 * and calculates the return on investments at different interest rates
 * from their current age until they turn 72.
 *
 * @author Kevin Wood
 * @version 1.0
 */
public class RetirementCalculatorDriver {
    public static void main(String[] args) {
        Application.launch(RetirementCalculatorView.class, args);
    }
}
