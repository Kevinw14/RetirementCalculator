package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class RetirementTableView<T> extends TableView<T[]> {

    TableViewDatasource datasource;

    public RetirementTableView() {}

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
            column.setMinWidth(datasource.minWidthForCell(i)); // Calls delegate method to set size of cell width
            final int columnIndex = i;
            column.setCellValueFactory(cellData -> {
                T[] row = cellData.getValue();
                return new SimpleObjectProperty(row[columnIndex]);
            });

            this.getColumns().add(column);
            columns[i] = column;
        }
    }

    public void setDatasource(TableViewDatasource datasource) {
        this.datasource = datasource;
        setupColumns();
    }
}
