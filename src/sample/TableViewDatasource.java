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
    void updateCell(TableCell<T, ?> tableCell, Object object, int columnIndex);
    T[] addData(int index);
    int numberOfRows();
    String[] titlesForColumns();
}
