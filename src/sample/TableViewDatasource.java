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

public interface TableViewDatasource {
    void updateCell(TableCell<int[], Number> tableCell, Number number, int columnIndex);
    int minWidthForCell(int columnIndex);
    String[] titlesForColumns();
}
