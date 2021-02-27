package sample;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;

/**
 * RetirementCalcView class sets up a view that gets input from user about their retirement savings
 * and will display the calculations in a table view. The view will display return on investments from
 * current age until the retiring age of 72.
 *
 * Tableview cells will change depending on how close you were to your target.
 * Yellow - Within 10% of target goal.
 * Green - Over your target goal.
 *
 * @author Kevin Wood
 * @version 1.0
 */
public class RetirementCalculatorView extends FlowPane {

    private final Label errorLabel;

    private final TextField ageTextField;
    private final TextField retirementSavingsTextField;
    private final TextField annualRetirementInvestmentTextField;
    private final TextField targetSavingForRetirementTextField;

    private final Button saveButton;
    private final Button loadButton;

    private final RetirementTableView<Integer> tableView;

    private RetirementViewDelegate delegate; // Delegate used to relay messages to controller when calculated button is pressed.

    /**
     * Initalizes Labels, TextFields, Button, and Tableview that is needed to display information
     * and take in user input.
     * FlowPanes are initialized and Labels and TextFields are added. FlowPanes are then added to the view itself.
     */
    public RetirementCalculatorView() {

        //Initialization of all the UI elements.
        Label ageLabel = new Label("Age");
        Label retirementSavingsLabel = new Label("Retirement Savings");
        Label annualRetirementInvestmentLabel = new Label("Annual Retirement Investment");
        Label targetSavingForRetirementLabel = new Label("Target Savings For Retirement");
        errorLabel = new Label("");
        errorLabel.setTextFill(Color.web("#f25c54"));

        ageTextField = new TextField();
        retirementSavingsTextField = new TextField();
        annualRetirementInvestmentTextField = new TextField();
        targetSavingForRetirementTextField = new TextField();

        Button calculateButton = new Button("Calculate");
        saveButton = new Button("Save");
        loadButton = new Button("Load");

        //Sets the action of the calculate button that calls the delegate method.
        calculateButton.setOnAction(this::calculateButtonPressed);

        tableView = new RetirementTableView<>();

        //Disables the row highlight when a tableview cells was clicked.
        tableView.setSelectionModel(null);

        //Sets centering and Hgap between the elements.
        FlowPane agePane = new FlowPane(ageLabel, ageTextField);
        agePane.setHgap(20);
        agePane.setAlignment(Pos.CENTER);
        FlowPane retirementSavingsPane = new FlowPane(retirementSavingsLabel, retirementSavingsTextField);
        retirementSavingsPane.setHgap(20);
        retirementSavingsPane.setAlignment(Pos.CENTER);
        FlowPane annualRetirementPane = new FlowPane(annualRetirementInvestmentLabel, annualRetirementInvestmentTextField);
        annualRetirementPane.setHgap(20);
        annualRetirementPane.setAlignment(Pos.CENTER);
        FlowPane targetSavingsPane = new FlowPane(targetSavingForRetirementLabel, targetSavingForRetirementTextField);
        targetSavingsPane.setHgap(20);
        targetSavingsPane.setAlignment(Pos.CENTER);
        FlowPane buttonPane = new FlowPane(calculateButton);
        buttonPane.setAlignment(Pos.CENTER);
        FlowPane fileManagementPane = new FlowPane(saveButton, loadButton);
        fileManagementPane.setHgap(20);
        fileManagementPane.setAlignment(Pos.CENTER);
        FlowPane errorPane = new FlowPane(errorLabel);
        errorPane.setAlignment(Pos.CENTER);

        //Adds FlowPanes to itself.
        this.setAlignment(Pos.TOP_CENTER);
        this.setVgap(10);
        this.setStyle("-fx-padding: 20 0;");
        this.getChildren().add(fileManagementPane);
        this.getChildren().add(agePane);
        this.getChildren().add(retirementSavingsPane);
        this.getChildren().add(annualRetirementPane);
        this.getChildren().add(targetSavingsPane);
        this.getChildren().add(buttonPane);
        this.getChildren().add(tableView);
        this.getChildren().add(errorPane);
    }

    /**
     * Sends a delegate message to the controller when the calculated
     * button is pressed.
     *
     * @param event Event object that is passed in when the button is pressed.
     */
    private void calculateButtonPressed(ActionEvent event) {
        delegate.calculateButtonPressed(event);
    }

    public TextField getRetirementSavingsTextField() {
        return retirementSavingsTextField;
    }
    public TextField getAgeTextField() {
        return ageTextField;
    }
    public TextField getAnnualRetirementInvestmentTextField() {
        return annualRetirementInvestmentTextField;
    }
    public TextField getTargetSavingForRetirementTextField() {
        return targetSavingForRetirementTextField;
    }
    public RetirementTableView<Integer> getTableView() {
        return tableView;
    }
    public Button getSaveButton() {
        return saveButton;
    }
    public Button getLoadButton() {
        return loadButton;
    }
    public Label getErrorLabel() { return errorLabel; }
    public void setDelegate(RetirementViewDelegate delegate) {
        this.delegate = delegate;
    }
}
