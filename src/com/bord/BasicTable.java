package com.bord;

import com.google.gson.JsonElement;

import javax.swing.table.AbstractTableModel;
import java.util.*;

/**
 * Created by TAMANDL on 15.10.2015.
 */
public class BasicTable<T extends TableModellable> extends AbstractTableModel {
    public ArrayList<T> Values = new ArrayList<>();
    T DefaultValue;

    public BasicTable(T defaultvalue)
    {
        DefaultValue = defaultvalue;
    }

    @Override
    public Object getValueAt(int row, int column) {
        if(Values.size() > 0)
            return Values.get(row).getValue(column);

        return null;
    }

    @Override
    public void setValueAt(Object value,int row, int column) {
        if(Values.size() > 0)
            Values.get(row).setValue(column, value);
    }

    @Override
    public int getRowCount() {
        return Math.max(0,Values.size());
    }

    @Override
    public int getColumnCount() {
        return DefaultValue.getColumns();
    }

    @Override
    public Class getColumnClass(int c) {
        return DefaultValue.getValue(c).getClass();
    }

    public String getColumnName(int col) {
        return DefaultValue.getColumnName(col);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        if(Values.size() > 0)
            return Values.get(row).canEdit(column);

        return false;
    }

    public T getDefaultObject()
    {
        return (T)DefaultValue.getDefaultCopy();
    }
}
