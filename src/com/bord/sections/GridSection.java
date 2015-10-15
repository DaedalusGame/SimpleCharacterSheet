package com.bord.sections;

import com.bord.SheetSection;
import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * Created by TAMANDL on 15.10.2015.
 */
public class GridSection extends SheetSection {
    private GridLayout Layout;
    private JPanel Panel;
    private ArrayList<SheetSection> SubSections = new ArrayList<>();
    private int Columns;

    public GridSection(String identifier, String name, int page, int columns) {
        super(identifier, name, page);
        JPanel panel = new JPanel();
        Layout = new GridLayout(1, 1);

        panel.setLayout(Layout);

        Panel = panel;
        Component = panel;
        Columns = columns;
    }

    public void AddEntry(SheetSection section)
    {
        SubSections.add(section);
        Panel.add(section.Component);
        Layout.setColumns(Columns);
        Layout.setRows(SubSections.size()/Columns);
    }
}
