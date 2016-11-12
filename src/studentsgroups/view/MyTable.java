package studentsgroups.view;

import javax.swing.*;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

/**
 * Created by molish on 11.11.2016.
 */
public class MyTable extends JTable {
    public MyTable(String[][] model, String[] headers){
        super(model, headers);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
