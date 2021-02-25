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
package sample;

import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class RetirementTableView<T> extends TableView<T[]> {

    private TableViewDatasource<T> datasource; // Datasource variable that will be set by the controller that conforms to TableViewDatasource.

    public RetirementTableView() {
        super();
    }

    /**
     * Creates n number of columns depending on how many titles were given.
     * It then goes through the columns and sets the minimum width and sets the value factory to each cell.
     * It then calls setCellFactory with the columns that was made and handles coloring of the cells;
     */
    private void setupColumns() {
        String[] titles = datasource.titlesForColumns();
        TableColumn<T[], ?>[] columns = new TableColumn[titles.length];

        //Sets up table views column names and determining what value goes into cells
        for (int i = 0; i < titles.length; i++) {
            TableColumn<T[], ?> column = new TableColumn<>(titles[i]);
            final int columnIndex = i;


            column.setCellValueFactory(cellData -> {
                T[] row = cellData.getValue();
                return new SimpleObjectProperty(row[columnIndex]);
            });

            this.getColumns().add(column);
            columns[i] = column;
        }

        setColumnCellFactory();
    }

    /**
     * Gets each cell in each column and updates the coloring of each cell depending
     * on the investment return.
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
     * It will clear the tableview of data thats currently displayed
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

    public void setDatasource(TableViewDatasource datasource) {
        this.datasource = datasource;
        setupColumns();
    }
}
