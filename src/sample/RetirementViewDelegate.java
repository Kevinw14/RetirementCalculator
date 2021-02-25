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
package sample;

import javafx.event.ActionEvent;

public interface RetirementViewDelegate {
    void calculateButtonPressed(ActionEvent event);
}
