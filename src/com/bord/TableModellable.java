package com.bord;

/**
 * Created by TAMANDL on 15.10.2015.
 */
public interface TableModellable extends ConfigStoreable {
    Object getValue(int i);
    void setValue(int i, Object o);
    int getColumns();
    String getColumnName(int i);
    Object getDefaultCopy();
    boolean canEdit(int i);
}
