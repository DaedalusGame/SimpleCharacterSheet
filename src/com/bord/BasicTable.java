package com.bord;

import javax.swing.table.AbstractTableModel;
import java.util.*;

/**
 * Created by TAMANDL on 15.10.2015.
 */
public class BasicTable<T extends TableModellable> extends AbstractTableModel {
    public ArrayList<T> Values = new ArrayList<>();

    @Override
    public Object getValueAt(int row, int column) {
        if(Values.size() > 0)
            return Values.get(row);

        return null;
    }

    @Override
    public int getRowCount() {
        return Math.max(1,Values.size());
    }

    @Override
    public int getColumnCount() {
        if(Values.size() > 0)
            return Values.get(0).getColumns();
        else
            return 1;
    }
}
