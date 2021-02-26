package sample;

import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * TableView class that has been simplified to add titles, customized cells
 * and add data to table view.
 *
 * RetirementTableView has a datasource variable and your controller
 * will have to conform to TableViewDatasource interface to setup the
 * tableview and add data.
 *
 * Don't forget to set the datasource to the controller conforming to the
 * interface.
 *
 * @author Kevin Wood
 * @version 1.0
 */
public class RetirementTableView<T> extends TableView<T[]> {

    private TableViewDatasource<T> datasource; // Datasource variable that will be set by the controller that conforms to TableViewDatasource.

    /**
     * Uses the data given by the controller to set up the table views columns
     * with titles and what type of value will be placed in each cell.
     */
    private void setupColumns() {
        String[] titles = datasource.titlesForColumns();

        //Sets up table views column names and determining what value goes into cells
        for (int i = 0; i < titles.length; i++) {
            TableColumn<T[], ?> column = new TableColumn<>(titles[i]);
            final int columnIndex = i;
            column.setCellValueFactory(cellData -> {
                T[] row = cellData.getValue();
                return new SimpleObjectProperty(row[columnIndex]);
            });

            this.getColumns().add(column);
        }

        setColumnCellFactory();
    }

    /**
     * Gets each cell in each column and calls datasource method
     * to update the cells value, or styling.
     */
    private void setColumnCellFactory() {
        for (int i = 0; i < this.getColumns().size(); i++) {
            TableColumn<T[], ?> column = this.getColumns().get(i);
            final int columnIndex = i;
            column.setCellFactory(tableCell -> new TableCell() {
                @Override
                protected void updateItem(Object object, boolean isEmpty) {
                    super.updateItem(object, isEmpty);
                    datasource.updateCell(this, object, columnIndex);
                }
            });
        }
    }

    /**
     * Updates the table view with the new data that was calculated.
     * It will clear the tableview of data that's currently displayed
     * and will update it with the new data.
     */
    public void update() {
        this.getItems().clear();
        addData();
    }

    /**
     * Adds data returned to the tableview. if data is not null
     * it will add it to the table view.
     */
    private void addData() {
        for (int i = 0; i < datasource.numberOfRows(); i++) {
            if (datasource.addData(i) != null) {
                this.getItems().add(datasource.addData(i));
            }
        }
    }

    public void setDatasource(TableViewDatasource<T> datasource) {
        this.datasource = datasource;
        setupColumns();
    }
}
