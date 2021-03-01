package sample;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.application.Application;
import javafx.stage.Stage;

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
public class RetirementCalculatorView extends Application {

    private final Label errorLabel;
    private final Label ageLabel;
    private final Label retirementSavingsLabel;
    private final Label annualRetirementInvestmentLabel;
    private final Label targetSavingForRetirementLabel;

    private final Button calculateButton;
    private final Button saveButton;
    private final Button loadButton;

    private final TextField ageTextField;
    private final TextField retirementSavingsTextField;
    private final TextField annualRetirementInvestmentTextField;
    private final TextField targetSavingForRetirementTextField;

    private final RetirementTableView<Integer> tableView;

    private RetirementViewDelegate delegate; // Delegate used to relay messages to controller when calculated button is pressed.

    private final FlowPane view;

    private Stage stage;

    /**
     * Initalizes Labels, TextFields, Button, and Tableview that is needed to display information
     * and take in user input.
     * FlowPanes are initialized and Labels and TextFields are added. FlowPanes are then added to the view itself.
     */
    public RetirementCalculatorView() {
        errorLabel = new Label("");
        ageLabel = new Label("Age");
        retirementSavingsLabel = new Label("Retirement Savings");
        annualRetirementInvestmentLabel = new Label("Annual Retirement Investment");
        targetSavingForRetirementLabel = new Label("Target Savings For Retirement");

        calculateButton = new Button("Calculate");
        saveButton = new Button("Save");
        loadButton = new Button("Load");

        ageTextField = new TextField();
        retirementSavingsTextField = new TextField();
        annualRetirementInvestmentTextField = new TextField();
        targetSavingForRetirementTextField = new TextField();

        errorLabel.setTextFill(Color.web("#f25c54"));

        //Sets the action of the save button that calls the delegate method.
        saveButton.setOnAction(this::save);

        //Sets the action of the load button that calls the delegate method.
        loadButton.setOnAction(this::load);

        //Sets the action of the calculate button that calls the delegate method.
        calculateButton.setOnAction(this::calculateButtonPressed);
        tableView = new RetirementTableView<>();

        //Disables the row highlight when a tableview cells was clicked.
        tableView.setSelectionModel(null);

        this.view = new FlowPane();
        addToContainer();
    }

    /**
     * Adds UI components to their FlowPanes and then adds the flowpanes
     * to the overall container that will be displayed.
     */
    private void addToContainer() {
        FlowPane agePane = new FlowPane(ageLabel, ageTextField);
        agePane.setHgap(20);
        FlowPane retirementSavingsPane = new FlowPane(retirementSavingsLabel, retirementSavingsTextField);
        retirementSavingsPane.setHgap(20);
        FlowPane annualRetirementPane = new FlowPane(annualRetirementInvestmentLabel, annualRetirementInvestmentTextField);
        annualRetirementPane.setHgap(20);
        FlowPane targetSavingsPane = new FlowPane(targetSavingForRetirementLabel, targetSavingForRetirementTextField);
        targetSavingsPane.setHgap(20);
        FlowPane buttonPane = new FlowPane(calculateButton);
        FlowPane fileManagementPane = new FlowPane(saveButton, loadButton);
        fileManagementPane.setHgap(20);
        FlowPane errorPane = new FlowPane(errorLabel);

        view.setAlignment(Pos.TOP_CENTER);
        view.setVgap(10);
        view.setStyle("-fx-padding: 20 0;");
        view.getChildren().add(fileManagementPane);
        view.getChildren().add(agePane);
        view.getChildren().add(retirementSavingsPane);
        view.getChildren().add(annualRetirementPane);
        view.getChildren().add(targetSavingsPane);
        view.getChildren().add(buttonPane);
        view.getChildren().add(tableView);
        view.getChildren().add(errorPane);
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

    /**
     * Sends a delegate message to the controller when the load
     * button is pressed.
     *
     * @param event Event object that is passed in when the button is pressed.
     */
    private void load(ActionEvent event) {
        delegate.loadButtonPressed(event, stage);
    }

    /**
     * Sends a delegate message to the controller when the save
     * button is pressed.
     *
     * @param event Event object that is passed in when the button is pressed.
     */
    private void save(ActionEvent event) {
        delegate.saveButtonPressed(event, stage);
    }

    /**
     * Creates a new Controller and passes this view in.
     * Sets up the overall UI for this application and sets the title.
     *
     * @param primaryStage Stage that used to present the overall UI of the JavaFX application
     */
    @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage;
        new Controller(this);
        primaryStage.setTitle("Retirement Calculator");
        primaryStage.setScene(new Scene(view, 1400, 600));
        primaryStage.show();
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
    public Label getErrorLabel() {
        return errorLabel;
    }
    public void setDelegate(RetirementViewDelegate delegate) {
        this.delegate = delegate;
    }
}
