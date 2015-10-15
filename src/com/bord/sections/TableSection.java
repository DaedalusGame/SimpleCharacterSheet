package com.bord.sections;

import com.bord.BasicTable;
import com.bord.SheetSection;

import javax.swing.*;
import java.awt.*;

/**
 * Created by TAMANDL on 15.10.2015.
 */
public class TableSection extends SheetSection {
    public TableSection(String identifier, String name, int page, BasicTable table)
    {
        super(identifier, name, page);
        JTable cooltable = new JTable(table);

        JScrollPane scrollpane = new JScrollPane(cooltable,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollpane.setPreferredSize(new Dimension(500, 200));
        Component = scrollpane;
    }
}
