package sample;

import javafx.event.ActionEvent;
import javafx.stage.Stage;

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

    /**
     * Sends a delegate message to the class that conforms to RetirementViewDelegate
     * when the save button is pressed.
     *
     * @param event The event that is passed in from the event handler
     * @param stage The overall container that houses the JavaFX view
     */
    void saveButtonPressed(ActionEvent event, Stage stage);

    /**
     * Sends a delegate message to the class that conforms to RetirementViewDelegate
     * when the load button is pressed.
     *
     * @param event The event that is passed in from the event handler
     * @param stage The overall container that houses the JavaFX view
     */
    void loadButtonPressed(ActionEvent event, Stage stage);
}
