/**
 * Datasource interface that handles with updating Tableview
 * with titles, places values in cells and setting styles to
 * cells.
 *
 * @author Kevin Wood
 * @version 1.0
 */

package sample;

import javafx.scene.control.TableCell;

public interface TableViewDatasource<T> {
    void updateCell(TableCell<T, ?> tableCell, Object object, int columnIndex);
    int minWidthForCell(int columnIndex);
    String[] titlesForColumns();
}
