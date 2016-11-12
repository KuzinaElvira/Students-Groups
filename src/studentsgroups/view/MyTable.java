package studentsgroups.view;

import javax.swing.*;

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
