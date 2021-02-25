/**
 * RetirementCalcView class sets up a view that gets input from user about their retirement savings
 * and will display the calculations in a table view. The view will display return on investments from
 * current age until the retiring age of 72.
 * <p>
 * Tableview cells will change depending on how close you were to your target.
 * Yellow - Within 10% of target goal.
 * Green - Over your target goal.
 *
 * @author Kevin Wood
 * @version 1.0
 */
package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;

public class RetirementCalculatorView extends FlowPane {

    private final TextField ageTextField;
    private final TextField retirementSavingsTextField;
    private final TextField annualRetirementInvestmentTextField;
    private final TextField targetSavingForRetirementTextField;

    private final Button calculateButton;
    private final Button saveButton;
    private final Button loadButton;

    private final TableView<int[]> tableView;

    /**
     * Initalizes Labels, TextFields, Button, and Tableview that is needed to display information
     * and take in user input.
     * FlowPanes are initialized and Labels and TextFields are added. FlowPanes are then added to the view itself.
     */
    public RetirementCalculatorView(String[] titles) {

        //Initialization of all the UI elements.
        Label ageLabel = new Label("Age");
        Label retirementSavingsLabel = new Label("Retirement Savings");
        Label annualRetirementInvestmentLabel = new Label("Annual Retirement Investment");
        Label targetSavingForRetirementLabel = new Label("Target Savings For Retirement");
        ageTextField = new TextField();
        retirementSavingsTextField = new TextField();
        annualRetirementInvestmentTextField = new TextField();
        targetSavingForRetirementTextField = new TextField();

        calculateButton = new Button("Calculate");
        saveButton = new Button("Save");
        loadButton = new Button("Load");

        tableView = new TableView<>();
        setupColumns(titles);

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
    }

    /**
     * Creates n number of columns depending on how many titles were given.
     * It then goes through the columns and sets the minimum width and sets the value factory to each cell.
     * It then calls setCellFactory with the columns that was made and handles coloring of the cells;
     */
    private void setupColumns(String[] titles) {
        TableColumn<int[], Number>[] columns = new TableColumn[titles.length];

        //Sets up table views column names and determining what value goes into cells
        for (int i = 0; i < titles.length; i++) {
            TableColumn<int[], Number> column = new TableColumn<>(titles[i]);
            if (i == 0) {
                column.setMinWidth(40);
            } else {
                column.setMinWidth(85);
            }
            final int columnIndex = i;
            column.setCellValueFactory(cellData -> {
                int[] row = cellData.getValue();
                final SimpleIntegerProperty simpleIntegerProperty = new SimpleIntegerProperty(row[columnIndex]);
                return simpleIntegerProperty;
            });

            tableView.getColumns().add(column);
            columns[i] = column;
        }
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

    public Button getCalculateButton() {
        return calculateButton;
    }

    public TableView<int[]> getTableView() {
        return tableView;
    }

    public Button getSaveButton() {
        return saveButton;
    }

    public Button getLoadButton() {
        return loadButton;
    }
}
