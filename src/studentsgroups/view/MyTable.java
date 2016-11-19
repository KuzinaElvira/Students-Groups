package studentsgroups.view;

import javax.swing.*;

/**
 * Created by molish on 17.11.2016.
 */
public class MyTable extends JTable{

    private int[] blockedColumn;

    public MyTable(String[][] model, String[] headers, int ...blockedColumn){
        super(model, headers);
        this.blockedColumn = blockedColumn;
        getTableHeader().setReorderingAllowed(false);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        for(int blockedCol : blockedColumn)
            if(column == blockedCol)
                return false;
        return super.isCellEditable(row, column);
    }
}
