package sample;

import javafx.event.ActionEvent;

/**
 * Delegate interface used to communicate from the
 * RetirementCalculatorView to the controller.
 *
 * Sends a message to the controller when the buttons
 * are pressed.
 *
 * @author Kevin Wood
 * @version 1.0
 */
public interface RetirementViewDelegate {

    /**
     * Sends a delegate message to the class that conforms to RetirementViewDelegate
     * when the calculated button is pressed.
     * 
     * @param event The event that is passed in from the event handler
     */
    void calculateButtonPressed(ActionEvent event);
}
