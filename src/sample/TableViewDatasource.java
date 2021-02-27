package sample;

import javafx.scene.control.TableCell;

/**
 * Datasource interface that handles with updating Tableview
 * with titles, number of rows needed to create, adding the
 * data to the tableview and setting styles to cells.
 *
 * @author Kevin Wood
 * @version 1.0
 */
public interface TableViewDatasource<T> {
    /**
     * Perform updates to the cell that is passed in
     *
     * @param tableCell Current cell to perform actions on.
     * @param object Object in the given cell.
     * @param columnIndex Column where that cell is located.
     */
    void updateCell(TableCell<T, ?> tableCell, Object object, int columnIndex);

    /**
     * Adds data to the tableview.
     * @param index the row's index it is currently at.
     * @return Array of objects that is needed to add to the tableview.
     */
    T[] addData(int index);

    /**
     * Gives the tableview the data needed to determine how many rows should be generated.
     * @return Int with the number rows that is needed.
     */
    int numberOfRows();

    /**
     * Give an array of strings that will determine the
     * number of columns needed for the tableview and set the
     * titles as the titles of each column.
     *
     * @return Array of strings that will be the number of columns in the table
     */
    String[] titlesForColumns();
}
