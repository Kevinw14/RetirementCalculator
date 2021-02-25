/**
 * Controller class that handles setting up the table view with data from the User object.
 * Conforms to TableViewDatasource and RetirementViewDelegate for the view to communicate
 * to the controller to calculate User's investment returns and update the view.
 *
 * @author Kevin Wood
 * @version 1.0
 */
package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.TableCell;

public class Controller implements TableViewDatasource, RetirementViewDelegate {

    private final RetirementCalculatorView view;
    private User user;

    /**
     * If a user was saved to the file system, it will update
     * the view with the data the user has in their respectable
     * TextFields on load.
     */
    private void setupView() {
        if (user != null) {
            this.view.getAgeTextField().setText("" + this.user.getAge());
            this.view.getAnnualRetirementInvestmentTextField().setText("" + this.user.getAnnualRetirementInvestment());
            this.view.getRetirementSavingsTextField().setText("" + this.user.getRetirementSavings());
            this.view.getTargetSavingForRetirementTextField().setText("" + this.user.getTargetSavingsForRetirement());
        }
        updateTableView();
    }

    /**
     * View is passed in and the calculated button's action gets set
     * with the calculate action. Then the tableview is setup with no data.
     *
     * @param view View that will update with the calculated information
     */
    public Controller(RetirementCalculatorView view) {
        this.view = view;
        this.view.setDelegate(this);
        this.view.getTableView().setDatasource(this);
        setupView();
    }

    /**
     * Clears the table whenever a new calculation is made.
     * gets the users calculated return on investments then adds
     * the return on investments to the tableview
     */
    private void updateTableView() {
        view.getTableView().getItems().clear();
        addDataToTableView();
    }

    /**
     * Determines if the amount given from a return on investment is within 10% of the target goal
     * but less than the target.
     * <p>
     * This is used to determine if the cell background should be yellow or white.
     *
     * @param investmentReturn The amount that was made that year.
     * @return returns true if the given number is within 10% the target goal.
     * Example
     * Target: $1,000,000
     * Within 10%: $900,000+
     */
    private boolean isWithinTen(Number investmentReturn) {
        double tenPercent = user.getTargetSavingsForRetirement() / 10;
        double bareMinimum = user.getTargetSavingsForRetirement() - tenPercent;

        return investmentReturn.intValue() >= bareMinimum && investmentReturn.intValue() < user.getTargetSavingsForRetirement();
    }

    /**
     * Determines if the amount given from a return on investment is at the target goal or over.
     * This is used to determine if the cell background should be green or white.
     *
     * @param investmentReturn The amount that was made that year.
     * @return returns true if the given number is at or over the target goal.
     */
    private boolean isOver(Number investmentReturn) {
        return investmentReturn.intValue() >= user.getTargetSavingsForRetirement();
    }

    /**
     * Handles checking the investment return and deciding if it's within 10% or over the target value.
     * If it's over the target it will change the background color of the cell to green.
     * If it's within 10% of the target it will change the background to yellow.
     *
     * @param tableCell        The tableview cell that will be updated
     * @param investmentReturn The investment return for that particular cell that is
     *                         used to check if it's within 10% or over the target amount.
     * @param columnIndex      The column index passed in is used to determine if the loop is
     *                         int the age column. If it's in any one of the columns except age
     *                         it will add a '$' to the investment return
     */
    private void handleCellColoring(TableCell<int[], Number> tableCell, Number investmentReturn, int columnIndex) {
        // Checks if the investment return is over the target amount. Will be green if true
        if (investmentReturn != null && isOver(investmentReturn)) {
            String aeroBlue = "#D0F4DE";
            tableCell.setStyle("-fx-background-color: " + aeroBlue);
        }

        // Checks if the investment return is with in 10% the target amount. Will be yellow if true
        if (investmentReturn != null && isWithinTen(investmentReturn)) {
            String blond = "#FCF6BD";
            tableCell.setStyle("-fx-background-color: " + blond);
        }

        // Fixes a visual bug that causes cells that doesn't meet the requirements to turn yellow or green when scrolling.
        // Checks to make sure that cells are neither within 10% or over target amount. Will keep their background
        // to white.
        if (investmentReturn != null && !isOver(investmentReturn) && !isWithinTen(investmentReturn)) {
            String white = "#FFFFFF";
            tableCell.setStyle("-fx-background-color: " + white);
        }

        // Fixes a visual bug that would put null in the cell when no value was present.
        if (investmentReturn != null) {
            if (columnIndex == 0) {
                tableCell.setText(String.valueOf(investmentReturn));
            } else {
                tableCell.setText("$" + investmentReturn);
            }
        } else {
            tableCell.setText("");
        }
    }

//    @Override
//    public int numberOfRows() {
//        if (user != null) {
//            return user.getReturnOnInvestments().length;
//        }
//
//        return 0;
//    }
//
//    @Override
//    public Object[] addData(int index) {
//        return new Object[0];
//    }

    /**
     * Checks if there was a user saved and passed into the Controller.
     * If there is one it will get the users saved investment returns and adds
     * them to the table view.
     */
    private void addDataToTableView() {
        if (user != null) {
            ROI[] returnOnInvestments = user.getReturnOnInvestments();
            for (ROI returnOnInvestment : returnOnInvestments) {
                view.getTableView().getItems().add(returnOnInvestment.getInvestmentReturns());
            }
        }
    }

    /**
     * Gets data from all TextFields, creates a new User object with data, and updates
     * the table view with the calculated data. When calculated it will save the User
     * to the file system.
     *
     * @param event Event object that is passed in when the button is pressed.
     */
    @Override
    public void calculateButtonPressed(ActionEvent event) {
        System.out.println("Calculate Button Pressed");
        try {
            int age = Integer.parseInt(view.getAgeTextField().getText());
            int retirementSavings = Integer.parseInt(view.getRetirementSavingsTextField().getText());
            int targetSavings = Integer.parseInt(view.getTargetSavingForRetirementTextField().getText());
            int annualSavings = Integer.parseInt(view.getAnnualRetirementInvestmentTextField().getText());


            this.user = new User(age, retirementSavings, annualSavings, targetSavings);
            updateTableView();

        } catch (NumberFormatException e) {
            System.out.println("Number Format Error");
        }
    }

    /**
     * Called for each cell in the table view. Can perform styling to table cell,
     * and add values to the cell.;
     *
     * @param tableCell tableCell that is currently being passed in.
     * @param object object that is in that cell
     * @param columnIndex Index the column is in currently
     */
    @Override
    public void updateCell(TableCell tableCell, Object object, int columnIndex) {
        Number number = (Number)object;
        handleCellColoring(tableCell, number, columnIndex);
    }

    /**
     * Uses the array of Strings to create that many columns, also sets the
     * array of titles to the titles of each column.
     *
     * @return An array of Strings that are the titles of the columns
     */
    @Override
    public String[] titlesForColumns() {
        return new String[] {"Age", "0%", "1%", "2%", "3%", "4%", "5%", "6%", "7%", "8%", "9%", "10%", "11%", "12%", "13%", "14%"};
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        setupView();
    }
}